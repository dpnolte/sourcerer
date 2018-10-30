package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.flow.getter.GetterFlowInterpreter
import com.laidpack.sourcerer.generator.flow.setter.SetterFlowInterpreter
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.AndroidResourceManager
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.resources.StyleableAttributeManager
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.laidpack.sourcerer.generator.target.*
import kotlinx.dnq.query.*

class AttributeManager(
        private val classInfo: ClassInfo,
        private val attributesFromXml: MutableMap<String, Attribute>,
        private val defaultStylableName: String,
        private val attrsXmlManager: StyleableAttributeManager,
        private val androidResourceManager: AndroidResourceManager
) {

    fun linkAttributeAndSetter(
            attribute: Attribute,
            setter: Setter,
            parameterIndex: Int,
            otherAttributeNames: List<String>
    ) {
        val setterHashCode = setter.hashCode()
        attribute.setterHashCodes.add(setterHashCode)
        setter.addCallSignatureMap(attribute, parameterIndex, otherAttributeNames)
    }
    fun linkAttributesAndSetter(
            attributesToParameters: Map<Attribute, Int /* parameter index */>,
            setter: Setter
    ) {
        val setterHashCode = setter.hashCode()
        for (attribute in attributesToParameters.keys) {
            attribute.setterHashCodes.add(setterHashCode)
        }
        val map = attributesToParameters.mapKeys { it.key.name }.toMutableMap()
        if (!setter.callSignatureMaps.hasCallSignatureMap(map)) {
            setter.addCallSignatureMap(map)
        }
    }

    fun canResolveGetter(
            attribute: Attribute,
            setter: Setter,
            providedParameterIndex: Int? = null
    ): Boolean {
        return setter.isField
                || analyzeJavaDocToFindGetter(attribute, setter).success
                || bestGuessMatchingGetterByName(attribute, setter, providedParameterIndex).success
                || analyzeSetterFlowToFindGetter(attribute, setter, providedParameterIndex).success
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
        val field = classInfo.getFieldDeclarationFromThisClassOrSuperClass(setter.name)
                ?: throw IllegalStateException("Field '${setter.name}' not found in class ${classInfo.xdDeclaredType.targetClassName}")
        return Getter(
                setter.name,
                field.describeType(setter.name),
                listOf(),
                field.begin.get().line,
                isField = true
        )
    }

    private fun getGettersForSetterMethod(attribute: Attribute, setter: Setter): List<Getter> {
        var result = analyzeJavaDocToFindGetter(attribute, setter)
        if (!result.success) result = bestGuessMatchingGetterByName(attribute, setter)
        if (!result.success) result = analyzeSetterFlowToFindGetter(attribute, setter)
        if (!result.success) throw TypePhilosopher.UnmatchedGetterTypeException("Could not match getter with setterCall. Setter methods name: '${setter.name}', Attribute: '${attribute.name}'")

        return when {
            result.type == GetterType.METHOD -> {
                val getters = mutableListOf<Getter>()
                val method = result.methods.first()
                Store.transactional {
                    val parameters = getParameters(
                            classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(method)
                    )

                    getters.add(Getter(
                            method.name,
                            method.describedReturnType,
                            parameters,
                            method.line,
                            isField = false
                    ))
                }
                getters
            }
            else /*result.type == GetterType.FIELD*/ -> {
                val field = result.field as XdField
                return Store.transactional {
                    listOf(Getter(
                            field.name,
                            field.describedType,
                            listOf(),
                            field.line,
                            isField = true
                    ))
                }
            }
        }
    }

    private fun analyzeSetterFlowToFindGetter(
            attribute: Attribute,
            setter: Setter,
            providedParameterIndex: Int? = null
    ): MatchGetterResult {
        val setterMethod = classInfo.getSetterFromThisClassOrSuperClass(setter)
        if (setterMethod is XdField) {
            return MatchGetterResult(false)
        }
        val setterMethodDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(setterMethod as XdMethod)
        val setterInterpreter = SetterFlowInterpreter(setterMethodDeclaration, setter, attribute, classInfo, providedParameterIndex)
        val getterRequirements = setterInterpreter.resolveGetterRequirements()
        if (getterRequirements.fields.isEmpty()) {
            return MatchGetterResult(false)
        }
        val eligibleGetters = mutableMapOf<FieldDeclaration, XdMethod>()
        val eligibilityResults = mutableMapOf<FieldDeclaration, GetterFlowInterpreter.EligibilityResult>()
        if (getterRequirements.fields.isNotEmpty()) {
            for((variableName, field) in getterRequirements.fields) {
                val potentialGetters = classInfo.getPotentialGettersForField(field, variableName)
                for(potentialGetter in potentialGetters) {
                    if (potentialGetter is XdMethod) {
                        val getterDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(potentialGetter)
                        val getterInterpreter = GetterFlowInterpreter(
                                getterDeclaration,
                                variableName,
                                getterRequirements.conditions,
                                classInfo
                        )
                        val result = getterInterpreter.checkEligibility()
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
            val methods: List<XdMethod> = listOf(),
            val field: XdField? = null
    )
    private fun bestGuessMatchingGetterByName(
            attribute: Attribute,
            setter: Setter,
            providedParameterIndex: Int? = null
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
            val parameterIndex = providedParameterIndex ?: setter.callSignatureMaps[attribute.name]
            val parameter = setter.parameters[parameterIndex]
            val parameterNameBase = parameter.name.capitalize()
            guesses.addAll(getGuessesForBase(parameterNameBase, targetsBoolean))
        }

        val potentialGetters = classInfo.getPotentialGettersForAnyOfTheseNames(guesses)
        for (potentialGetter in potentialGetters) {
            when (potentialGetter) {
                is XdMethod  -> {
                    val describedReturnType = Store.transactional { potentialGetter.describedReturnType }
                    if (getterTargetClassNames.any { isAttributeAssignableToGetter(it, describedReturnType) }) {
                        return MatchGetterResult(true, GetterType.METHOD, listOf(potentialGetter), null)
                    }
                }
                is XdField -> {
                    val describedType = Store.transactional { potentialGetter.describedType }
                    if (getterTargetClassNames.any { isAttributeAssignableToGetter(it, describedType) }) {
                        return MatchGetterResult(true, GetterType.FIELD, listOf(), potentialGetter)
                    }
                }
            }
        }

        return MatchGetterResult(false)
    }

    private fun analyzeJavaDocToFindGetter(
            attribute: Attribute,
            setter: Setter
    ): MatchGetterResult {
        val numberOfDesiredAttributes = setOf(
                1,
                if (setter.callSignatureMaps.containsAttribute(attribute.name)) {
                    setter.callSignatureMaps.size(attribute)
                } else 1
        )
        val selectedGetter : XdMember? = classInfo.getAnyGetterByJavaDoc(attribute, numberOfDesiredAttributes)
        return when {
            selectedGetter != null && selectedGetter is XdMethod
                -> MatchGetterResult(true, GetterType.METHOD, listOf(selectedGetter), null)
            selectedGetter != null && selectedGetter is XdField
                -> MatchGetterResult(true, GetterType.FIELD, listOf(), selectedGetter)
            else -> MatchGetterResult(false)
        }
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

    fun getSetter(setters: Map<Int, Setter>, setterMethod: XdMethod, setterCall: MethodCallExpr? = null): Setter {
        // setter already exists?
        val hashCode = Setter.getHashCodeFromMethodInfo(setterMethod)
        if (setters.containsKey(hashCode)) return setters[hashCode] as Setter

        val setterDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(setterMethod)
        val setterParameters = getParameters(setterDeclaration, setterCall)

        return Setter(
                setterDeclaration.nameAsString,
                setterParameters,
                setterDeclaration.begin.get().line,
                isField = false,
                isStaticSetter =  false
        )

    }

    fun getSetter(setters: Map<Int, Setter>, xdField: XdField): Setter {
        // setter already exists?
        val hashCode = Store.transactional { xdField.accessorHashCode }
        if (setters.containsKey(hashCode)) return setters[hashCode] as Setter

        return Store.transactional {
            Setter(
                    xdField.name,
                    listOf(),
                    xdField.line,
                    isField = true,
                    isStaticSetter = false
            )
        }

    }

    fun getStaticSetter(
            setters: Map<Int, Setter>,
            classDeclaration: ClassOrInterfaceDeclaration,
            methodDeclaration: MethodDeclaration,
            setterCall: MethodCallExpr?
    ): Setter {
        // setter already exists?
        val hashCode = Setter.getHashCodeFromMethodDeclaration(methodDeclaration)
        if (setters.containsKey(hashCode)) return setters[hashCode] as Setter

        val setterParameters = getParameters(methodDeclaration, setterCall)
        val cu = classDeclaration.findRootNode() as CompilationUnit
        val className = DeclaredSymbolResolver.determineClassName(classDeclaration, cu.packageDeclaration.get())

        return Setter(
                methodDeclaration.nameAsString,
                setterParameters,
                methodDeclaration.begin.get().line,
                isField = false,
                isStaticSetter =  true,
                scopeClassName = className
        )
    }

    fun getResolvedSetterPropertyName(setter: Setter): String? {
        // ensure setter is in right formats
        val setterMethodNameBase = setter.name.substring(3)
        if (!setter.name.startsWith("set")
                || setter.parameters.size != 1
                || setter.parameters.first().isVarArgs
                || setter.isStaticSetter
        ) {
            return null
        }
        val xdMethod = classInfo.getSetterFromThisClassOrSuperClass(setter) as XdMethod
        // check if we have a matching getter for the setter (based on name and type)
        val matchingGetterNames = listOf(
                "get$setterMethodNameBase",
                "is$setterMethodNameBase"
        )
        val isBooleanRequired = setter.parameters.first().format == StyleableAttributeFormat.Boolean

        val matchingGetters = classInfo.getPotentialGettersForAnyOfTheseNames(matchingGetterNames)

        return Store.transactional {
            val xdParameter = xdMethod.parameters.first()
            val checkNullability = if (xdParameter.nullable) {
                this::hasNullableReturn
            } else this::hasNonNullableReturn

            for (matchingGetter in matchingGetters) {
                if (!matchingGetter.isStatic && checkNullability(matchingGetter)) {
                    if (matchingGetter.name.startsWith("is") && isBooleanRequired) {
                        return@transactional "is$setterMethodNameBase"
                    } else if (matchingGetter is XdMethod && matchingGetter.name.startsWith("get")
                            && matchingGetter.describedReturnType == xdParameter.describedType
                    ) {
                        return@transactional setterMethodNameBase.decapitalize()
                    }
                }
            }
            return@transactional null
        }

    }
    private fun hasNullableReturn(xdMember: XdMember): Boolean {
        return xdMember.nullable
    }
    private fun hasNonNullableReturn(xdMember: XdMember): Boolean {
        return !xdMember.nullable
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
        return if (classInfo.classCategory != null && classInfo.classCategory == ClassCategory.LayoutParams) {
            val lpSimpleName = classInfo.xdDeclaredType.targetClassName.simpleName.replace("LayoutParams", "Layout")
            val viewClassName = WidgetRegistry.getWidgetViewClassName(classInfo)
            subject.replace("${viewClassName.simpleName}_${lpSimpleName}_", "")
        } else {
            subject.replace("${Store.transactional { classInfo.xdDeclaredType.simpleName }}_", "")
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
        return Store.transactional {
            when {
                classInfo.xdDeclaredType.superClasses.isEmpty -> return@transactional null
                else -> {
                    for (xdSuperClass in classInfo.xdDeclaredType.superClasses) {
                        if (xdSuperClass.sourcererResult != null) {
                            val xdResult = xdSuperClass.sourcererResult as XdSourcererResult
                            return@transactional xdResult.attributes.query(
                                    (XdAttribute::name eq attribute.name)
                            ).firstOrNull() ?: continue
                        }
                    }
                    return@transactional null
                }
            }
        }
    }

    fun getAnyNonDefaultAttributeObtained(valueExpressions: List<Expression>): List<Attribute> {
        val attributes = mutableListOf<Attribute>()
        for (valueExpression in valueExpressions) {
            when (valueExpression) {
                is FieldAccessExpr -> {
                    if (valueExpression.nameAsString != defaultStylableName) {
                        val attrs = attrsXmlManager.getAttributesFromXml(valueExpression.nameAsString, classInfo)
                        attributes.addAll(attrs)
                    }
                }
                is IntegerLiteralExpr -> {
                    val resourceId = valueExpression.asInt()
                    val attrName = androidResourceManager.getAttributeResourceNameById(resourceId)
                    val attrs = attrsXmlManager.getAttributesFromXml(attrName, classInfo)
                    attributes.addAll(attrs)
                }
                else -> throw IllegalStateException("Could not convert expression type ${valueExpression::class.java.canonicalName} into attribute. Expression content: $valueExpression")
            }
        }
        return attributes
    }
    fun addNonDefaultAttribute(attribute: Attribute) {
        attributesFromXml[attribute.name] = attribute
    }



}