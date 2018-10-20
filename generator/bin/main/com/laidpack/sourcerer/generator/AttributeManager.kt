package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.MethodCallExpr
import com.laidpack.sourcerer.generator.flow.getter.GetterFlowInterpreter
import com.laidpack.sourcerer.generator.flow.setter.SetterFlowInterpreter
import com.laidpack.sourcerer.generator.javadoc.JavaDocAttributeToMethodMatcher
import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.laidpack.sourcerer.generator.target.*
import kotlinx.dnq.query.*

class AttributeManager(
        private val classInfo: ClassInfo,
        private val attributesFromXml: Map<String, Attribute>,
        private val classRegistry: ClassRegistry
) {

    private val potentialGetters = classInfo.potentialGetters
    private val potentialGettersForJavaDocAnalysis = potentialGetters.filter{
        it.methodDeclaration.nameAsString.startsWith("get")
        && it.methodDeclaration.parameters.isEmpty()
        && it.javadoc.blockTags.any { it.tagName == "attr" }
    }.associateBy { it.methodDeclaration.nameAsString }
    private val javaDocAttributeNamesPerPotentialGetter = potentialGettersForJavaDocAnalysis.values.associate {
        Pair(
                it.methodDeclaration.nameAsString,
                it.javadoc.blockTags.asSequence().filter { blockTag ->  blockTag.tagName == "attr" }
                        .map { blockTag ->
                            JavaDocAttributeToMethodMatcher.getAttributeNameFromBlockTag(blockTag, classInfo)
                        }.toList()
        )
    }

    fun linkAttributeAndSetter(attribute: Attribute, setter: Setter, parameterIndex: Int) {
        val setterHashCode = setter.hashCode()
        attribute.setterHashCodes.add(setterHashCode)
        setter.attributeToParameter[attribute.name] = parameterIndex
    }

    fun canResolveGetter(
            attribute: Attribute,
            setter: Setter
    ): Boolean {
        return setter.isField
                || analyzeJavaDocToFindGetter(attribute, setter).success
                || bestGuessMatchingGetterByName(attribute, setter).success
                || analyzeSetterFlowToFindGetter(attribute, setter).success
    }

    fun getResolvedGetters(
            attribute: Attribute,
            setter: Setter
    ): List<Getter> {
        return if (setter.isField) {
            listOf(getGetterForSetterField(setter))
        } else {
            getGettersForSetterMethod(attribute, setter)
        }
    }

    private fun getGetterForSetterField(setter: Setter): Getter {
        if (!setter.isField) throw IllegalStateException("Setter is not a field")
        val field = classInfo.fieldDeclarations[setter.name] ?: throw IllegalStateException("Field '${setter.name}' not found in class ${classInfo.targetClassName}")
        return Getter(
                setter.name,
                field.describeType(),
                listOf(),
                field.begin.get().line,
                isField = true
        )
    }

    private fun getGettersForSetterMethod(attribute: Attribute, setter: Setter): List<Getter> {
        var result = analyzeJavaDocToFindGetter(attribute, setter)
        if (!result.success) result = bestGuessMatchingGetterByName(attribute, setter)
        if (!result.success) result = analyzeSetterFlowToFindGetter(attribute, setter)
        if (!result.success) throw IllegalStateException("Could not match getter with setterCall. Setter methods name: '${setter.name}', Attribute: '${attribute.name}'")

        return when {
            result.type == GetterType.METHOD -> {
                val getters = mutableListOf<Getter>()
                val method = result.methods.first()
                val parameters = getParameters(method.methodDeclaration)

                getters.add(Getter(
                        method.methodDeclaration.nameAsString,
                        method.describeReturnType(),
                        parameters,
                        method.line
                ))
                getters
            }
            else /*result.type == GetterType.FIELD*/ -> {
                val field = result.field as FieldDeclaration
                listOf(Getter(
                        field.variables.first().nameAsString,
                        field.describeType(),
                        listOf(),
                        field.begin.get().line,
                        isField = true
                ))
            }
        }
    }

    private fun analyzeSetterFlowToFindGetter(attribute: Attribute, setter: Setter): MatchGetterResult {
        val setterInfo = classInfo.getSetterMethodInfo(setter)
        val setterInterpreter = SetterFlowInterpreter(setterInfo, setter, attribute, classInfo)
        val getterRequirements = setterInterpreter.resolveGetterRequirements()
        if (getterRequirements.fields.isEmpty()) {
            return MatchGetterResult(false)
        }
        val eligibleGetters = mutableMapOf<FieldDeclaration, MethodInfo>()
        val eligibilityResults = mutableMapOf<FieldDeclaration, GetterFlowInterpreter.EligibilityResult>()
        if (getterRequirements.fields.isNotEmpty()) {
            for(field in getterRequirements.fields) {
                val potentialGetters = classInfo.getPotentialGettersForField(field)
                for(potentialGetter in potentialGetters) {
                    val getterInterpreter = GetterFlowInterpreter(
                            potentialGetter,
                            field,
                            getterRequirements.conditions,
                            attribute,
                            classInfo
                    )
                    val result =getterInterpreter.checkEligibility()
                    if (result.eligible) {
                        // replace if more conditions are met
                        when {
                            eligibleGetters.containsKey(field) -> {
                                val previousResult = eligibilityResults[field] as GetterFlowInterpreter.EligibilityResult
                                if (previousResult.fulfilledConditionCount < result.fulfilledConditionCount) {
                                    eligibilityResults[field] = result
                                    eligibleGetters[field] = potentialGetter
                                }
                            }
                            else -> {
                                eligibilityResults[field] = result
                                eligibleGetters[field] = potentialGetter
                            }
                        }
                    }
                }
            }
        }
        if (eligibleGetters.size == getterRequirements.fields.size) {
            return MatchGetterResult(
                    true,
                    GetterType.METHOD,
                    eligibleGetters.values.toList()
            )
        }
        return MatchGetterResult(false)
    }

    private enum class GetterType { METHOD, FIELD }
    private data class MatchGetterResult (
            val success : Boolean,
            val type: GetterType? = null,
            val methods: List<MethodInfo> = listOf(),
            val field: FieldDeclaration? = null
    )
    private fun bestGuessMatchingGetterByName(
            attribute: Attribute,
            setter: Setter
    ): MatchGetterResult {
        val typesForThisSetter = attribute.typesPerSetter[setter.hashCode()] as AttributeTypesForSetter

        // let's do simple name matching first.. getter can be set->get or set->is
        val getterTargetClassNames = if(typesForThisSetter.unassociatedToParameter) {
            typesForThisSetter.attributeCanonicalNames
        } else listOf(typesForThisSetter.setterClassName)

        val targetsBoolean = getterTargetClassNames.any { it.toLowerCase().contains("boolean") }
        val guesses = mutableListOf<String>()
        val setterMethodNameBase = setter.name.substring(setter.name.indexOfFirstCapitalChar())
        guesses.addAll(getGuessesForBase(setterMethodNameBase, targetsBoolean))
        val attrNameBase = if (attribute.name.startsWith("layout_")) {
            attribute.name.substring(7).capitalize()
        } else attribute.name.capitalize()
        guesses.addAll(getGuessesForBase(attrNameBase, targetsBoolean))
        if (!typesForThisSetter.unassociatedToParameter) {
            val parameter = setter.parameters[setter.attributeToParameter[attribute.name] as Int]
            val parameterNameBase = parameter.name.capitalize()
            guesses.addAll(getGuessesForBase(parameterNameBase, targetsBoolean))
        }

        for (classInfo in classRegistry.getClassAndSuperClasses(classInfo)) {
            for (getterNameGuess in guesses) {
                when {
                    classInfo.methodDeclarations.containsKey(getterNameGuess) -> {
                        val methods = classInfo.methodDeclarations[getterNameGuess] as List<MethodInfo>
                        val method = methods.first()
                        if (isEligibleMethod(method, getterTargetClassNames)) {
                            return MatchGetterResult(true, GetterType.METHOD, listOf(method), null)
                        }
                    }
                    classInfo.fieldDeclarations.containsKey(getterNameGuess) -> {
                        val field = classInfo.fieldDeclarations[getterNameGuess] as FieldDeclaration
                        if (isEligibleField(field, getterTargetClassNames)) {
                            return MatchGetterResult(true, GetterType.FIELD, listOf(), field)
                        }
                    }
                }
            }
        }

        return MatchGetterResult(false)
    }

    private fun analyzeJavaDocToFindGetter(attribute: Attribute, setter: Setter): MatchGetterResult {
        var selectedGetter : MethodInfo? = null
        val numberOfDesiredAttributes = setOf(1, setter.attributeToParameter.size)
        for (potentialGetterName in javaDocAttributeNamesPerPotentialGetter.keys) {
            val attributeNames = javaDocAttributeNamesPerPotentialGetter[potentialGetterName] as List<String>
            if (numberOfDesiredAttributes.contains(attributeNames.size)
                    && attributeNames.any { it == attribute.name }) {
                selectedGetter = potentialGettersForJavaDocAnalysis[potentialGetterName]
            }
        }
        return if (selectedGetter != null) {
            MatchGetterResult(true, GetterType.METHOD, listOf(selectedGetter), null)
        } else MatchGetterResult(false)
    }

    private fun isEligibleMethod(method: MethodInfo, getterTargetClassNames: List<String>): Boolean {
        if (ClassInfo.isEligibleMethod(method)) {
            val returnTypeString = method.describeReturnType()
            return getterTargetClassNames.any { isAttributeAssignableToGetter(it, returnTypeString) }
        }
        return false
    }

    private fun isEligibleField(field: FieldDeclaration, getterTargetClassNames: List<String>): Boolean {
        if (ClassInfo.isEligibleField(field)) {
            return getterTargetClassNames.any { isAttributeAssignableToGetter(it, field.describeType()) }
        }
        return false
    }


    private fun getGuessesForBase(base: String, targetsBoolean: Boolean = false): List<String> {
        val guesses = mutableListOf(
                "get$base",
                "is$base",
                base.decapitalize()
        )
        if (targetsBoolean) {
            guesses.add("is${base}Enabled")
            guesses.add("${base.decapitalize()}Enabled")
        }
        val splitBase = base.splitByCapitalChar()
        if (splitBase.size == 2) {
            guesses.add(splitBase[1].decapitalize() + splitBase[0].capitalize())
        }
        if (base.endsWith("s") && splitBase.size == 1) {
            guesses.add("get${base.substring(0, base.lastIndex)}Count")
        }

        return guesses
    }

    fun getSetter(setters: Map<Int, Setter>, setterInfo: MethodInfo, setterCall: MethodCallExpr? = null): Setter {
        // setter already exists?
        val hashCode = Setter.getHashCodeFromMethodInfo(setterInfo)
        if (setters.containsKey(hashCode)) return setters[hashCode] as Setter

        val setterParameters = getParameters(setterInfo.methodDeclaration, setterCall)
        return Setter(
                setterInfo.methodDeclaration.nameAsString,
                setterParameters,
                setterInfo.methodDeclaration.begin.get().line,
                isField = false
        )

    }

    fun getSetter(setters: Map<Int, Setter>, field: FieldDeclaration): Setter {
        // setter already exists?
        val hashCode = Setter.getHashCodeFromField(field)
        if (setters.containsKey(hashCode)) return setters[hashCode] as Setter

        val variableName = field.variables.first().nameAsString
        return Setter(
                variableName,
                listOf(),
                field.begin.get().line,
                isField = true
        )

    }

    fun getResolvedSetterPropertyName(setter: Setter): String? {
        // ensure setter is in right formats
        val setterMethodNameBase = setter.name.substring(3)
        if (!setter.name.startsWith("set")
                || setter.parameters.size != 1
                || setter.parameters.first().isVarArgs
        ) {
            return null
        }
        val methodInfo = classInfo.getSetterMethodInfo(setter)
        val parameter = methodInfo.methodDeclaration.parameters.first()
        val requiresNullableGetter = parameter.annotations.any { it.nameAsString == "Nullable" }

        // in right formats, but check if we have a matching getter for the setter (based on name and type)
        val classAndSuperClasses = mutableListOf(classInfo)
        classAndSuperClasses.addAll(classInfo.superClassNames.map { classRegistry[it] as ClassInfo })

        // check if we have a matching getter for the setter (based on name and type)
        val matchingGetterNames = listOf(
                "get$setterMethodNameBase",
                "is$setterMethodNameBase"
        )

        val isBooleanRequired = setter.parameters.first().format == StyleableAttributeFormat.Boolean
        val setterParameterDescribedType = parameter.describeType()
        val checkNullability = if (requiresNullableGetter) {
            this::hasNullableReturn
        } else this::hasNonNullableReturn
        for (selectedClassInfo in classAndSuperClasses) {
            val matchingGetters = mutableListOf<MethodInfo>()
            if (selectedClassInfo.methodDeclarations.containsKey(matchingGetterNames[0])) {
                matchingGetters.addAll(selectedClassInfo.methodDeclarations[matchingGetterNames[0]] as List<MethodInfo>)
            }
            if (selectedClassInfo.methodDeclarations.containsKey(matchingGetterNames[1])) {
                matchingGetters.addAll(selectedClassInfo.methodDeclarations[matchingGetterNames[1]] as List<MethodInfo>)
            }
            for (matchingGetter in matchingGetters) {
                if (!matchingGetter.methodDeclaration.isStatic && checkNullability(matchingGetter)) {
                    val methodName = matchingGetter.methodDeclaration.nameAsString
                    if (methodName.startsWith("is") && isBooleanRequired) {
                        return "is$setterMethodNameBase"
                    } else if (methodName.startsWith("get")
                        && matchingGetter.describeReturnType() == setterParameterDescribedType
                    ) {
                        return setterMethodNameBase.decapitalize()
                    }
                }
            }
        }
        return null
    }
    private fun hasNullableReturn(method: MethodInfo): Boolean {
        return method.methodDeclaration.annotations.any { it.nameAsString == "Nullable" }
    }
    private fun hasNonNullableReturn(method: MethodInfo): Boolean {
        return method.methodDeclaration.annotations.all { it.nameAsString != "Nullable" }
    }


    fun getParameters(methodDeclaration: MethodDeclaration, methodCallExpr: MethodCallExpr? = null): List<Parameter> {
        val parameters = mutableListOf<Parameter>()
        for (index in 0 until methodDeclaration.parameters.size) {
            val parameter = methodDeclaration.parameters[index]
            val parameterTypeClassName = parameter.describeType()
            var defaultValue = ""
            if (methodCallExpr != null) {
                val argument = methodCallExpr.arguments[index]
                defaultValue = when {
                    argument.isLiteralStringValueExpr -> argument.asLiteralStringValueExpr().value
                    argument.isNullLiteralExpr -> "null"
                    else -> ""
                }
            }

            parameters.add(Parameter(
                    index = index,
                    name = parameter.nameAsString,
                    describedType = parameterTypeClassName,
                    isNullable = parameter.type.annotations.any { annotation ->
                        annotation.nameAsString == "Nullable"
                    },
                    annotations = parameter.annotations.map { it.nameAsString },
                    defaultValue = defaultValue,
                    isVarArgs = parameter.isVarArgs
            ))
        }
        return parameters
    }

    fun getAttributeTypesForSetter(targetClassNames: List<String>, defaultValue: String, parameterIndex: Int, isField: Boolean = false): AttributeTypesForSetter {

        return AttributeTypesForSetter(
                targetClassNames,
                defaultValue,
                parameterIndex == Parameter.UNASSOCIATED_TO_PARAMETER,
                isField
        )
    }

    fun getNameFromResourceId(resourceId: String): String {
        val subject = if (resourceId.contains('.')) {
            resourceId.substring(resourceId.indexOfLast { c -> c == '.' } + 1)
        } else resourceId
        return if (classInfo.classCategory == ClassCategory.LayoutParams) {
            val lpSimpleName = classInfo.targetClassName.simpleName.replace("LayoutParams", "Layout")
            val viewClassName = WidgetRegistry.getWidgetViewClassName(classInfo)
            subject.replace("${viewClassName.simpleName}_${lpSimpleName}_", "")
        } else {
            subject.replace("${classInfo.targetClassName.simpleName}_", "")
        }
    }

    private fun isAttributeAssignableToGetter(parameterClassName: String, getterTypeClassName: String): Boolean {
        return if (parameterClassName == getterTypeClassName) {
            true
        } else {
            try {
                val attributeValueClass = TypePhilosopher.toClass(parameterClassName).java
                val getterReturnClass = TypePhilosopher.toClass(getterTypeClassName).java
                getterReturnClass.isAssignableFrom(attributeValueClass)
            } catch(e: ClassNotFoundException) {
                false
            }
        }
    }

    fun isAttributeDefined(attributeName: String): Boolean {
        return attributesFromXml.containsKey(attributeName)
    }

    fun getAttributeFormats(attributeName: String): List<StyleableAttributeFormat> {
        val attribute = attributesFromXml[attributeName] ?: throw IllegalArgumentException("No attribute known in xml with name '$attributeName'")
        return attribute.formats
    }

    fun canAttributeTypeBeConvertedToType(attributeName: String , setterTypeAsString: String): Boolean {
        val attribute = attributesFromXml[attributeName] ?: throw IllegalArgumentException("No attribute known in xml with name '$attributeName'")
        attribute.formats.forEach { format ->
            try {
                val formatClass = format.toClass()
                when {
                    setterTypeAsString == formatClass.java.canonicalName -> return true
                    setterTypeAsString == formatClass.qualifiedName -> return true
                    attribute.formats.size == 1 && format == StyleableAttributeFormat.Unspecified -> return true
                    TypePhilosopher.isFormatToTypeConversionAvailable(format, TypePhilosopher.toClassName(setterTypeAsString)) -> return true
                    else -> {
                        val setterClass = TypePhilosopher.toClass(setterTypeAsString)
                        if (setterClass.java.isAssignableFrom(formatClass.java)) return true
                    }
                }
            } catch (e: ClassNotFoundException) {

            }
        }
        return false
    }

    fun assignAnyAttributeDeclaredInSuperClass(attribute: Attribute, classInfo: ClassInfo) {
        val xdAttributeDeclaredInSuperClass = getAttributeDeclaredInSuperClass(attribute, classInfo)
        if (xdAttributeDeclaredInSuperClass != null) {
            attribute.mutableAttributeDeclaredInSuperClass = xdAttributeDeclaredInSuperClass.toSnapshot()
            attribute.mutableDeclarationClassName = attribute.attributeDeclaredInSuperClass.className
        }
    }
    fun replaceAttributeFormatFromTo(fromAttribute: Attribute, toAttribute: Attribute) {
        toAttribute.formats.clear()
        toAttribute.formats.addAll(fromAttribute.formats)
        toAttribute.flags.clear()
        toAttribute.flags.addAll(fromAttribute.flags)
        toAttribute.enumValues.clear()
        toAttribute.enumValues.addAll(fromAttribute.enumValues)
    }

    private fun getAttributeDeclaredInSuperClass(attribute: Attribute, classInfo: ClassInfo): XdAttribute? {
        when {
            classInfo.superClassNames.isEmpty() -> return null
            else -> {
                val superClassNamesAsString = classInfo.superClassNames.map { it.canonicalName }
                return Store.transactional {
                    // sort by inheritance order (base class first)
                    val xdSuperClassResults = XdSourcererResult.query(
                            (XdSourcererResult::targetClassCanonicalName.inValues(superClassNamesAsString))

                    ).asSequence().sortedBy {xdResult ->
                        xdResult.targetClass.superClasses.size()
                    }
                    for (xdResult in xdSuperClassResults) {
                        val xdAttributes = XdAttribute.query(
                                (XdAttribute::name eq attribute.name)
                                        and (XdAttribute::classCanonicalName eq xdResult.targetClassCanonicalName)
                        ).toList()
                        when {
                            xdAttributes.size == 1 -> {
                                return@transactional xdAttributes.first()
                            }
                            xdAttributes.size > 1 -> {
                                throw IllegalStateException("Multiple attributes stored with name '${attribute.name}' for class '${xdResult.targetClassCanonicalName}'")
                            }
                        }
                    }
                    return@transactional null
                }
            }
        }
    }

    private fun String.indexOfFirstCapitalChar(): Int {
        for (index in 0 until this.length) {
            if (this[index].isUpperCase()) {
                return index
            }
        }
        return 0
    }

    private fun String.splitByCapitalChar(): List<String> {
        return Regex("[A-Z]+[a-z0-9]*").findAll(this).map {
            it.value
        }.toList()
    }

}