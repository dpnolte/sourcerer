package com.laidpack.sourcerer.generator.flow.attributes

import android.content.res.TypedArray
import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.ast.type.Type
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Parameter
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.target.TransformingCode
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import java.lang.Exception

enum class ConditionStmt {
    SWITCH,
    SWITCH_ENTRY,
    IF,
    NONE
}

data class Variable(val name: SimpleName, val type: Type, val initializer: Expression) {
    var isDerivedFromAttribute = false
    val attributeImpactExpression = mutableMapOf<String, Expression>()
    val conditionExpressions = mutableListOf<Expression>()
    val conditionalImpact = mutableMapOf<String, MutableList<ConditionalImpact>>()
}

enum class ConditionType {
    HasValue,
    IsType,
    IsNotType,
    Boolean,
    Int
}
data class Condition(val type: ConditionType, val value: String)
data class ConditionalImpact(val conditions: Set<Condition>, val valueExpr: Expression)

enum class VariableImpact {
    ASSIGNED,
    CHAINED,
    IF_TRUE,
    IF_TYPE_VALUE
}

class AttributeInSource(className: ClassName, name: String, val resourceName: String) : Attribute(className, name) {
    val derivedVariables = mutableMapOf<String, VariableImpact>()
    val targetClassNames = mutableListOf<String>()
    var defaultValue: String = ""
    val transformingCodes = mutableListOf<TransformingCode>()
}

/** essentially consolidated node handler **/
class AttributeFlow (
        val attributeSetVariableName: SimpleName,
        private val manager: AttributeManager,
        private val interpreter: FlowInMethodInterpreter,
        val classInfo: ClassInfo,
        val typedArrayInfo: TypedArrayInfo,
        val parametersAsVariables: List<Variable>,
        private val attributesDefinedInXml: Boolean
) {
    private val variablesToAttributes = mutableMapOf<String, String>()
    private val methodCallsOnFieldsToAttributes = mutableMapOf<MethodCallExpr, String /* attr name */>()
    val typedArrayVariableNames = mutableSetOf<SimpleName>()
    var typedValueVariableName: SimpleName? = null
    private set
    var resourceIdVariableName: SimpleName? = null
    private set

    var withinNestedMethod = false
    var nestedMethodName = ""
    private var variableNameAssignedByNestedMethod: SimpleName? = null
    private val resourceNamesToParamNames = mutableMapOf<String /* resource name  */, String /* parameter name */>()
    val isAnyParameterDeclaredAsResourceName : Boolean
        get() = resourceNamesToParamNames.isNotEmpty()

    val isConditionalToAttribute: Boolean
        get() {
            if (attributeControlFlowLevel == -1) return false
            val cf = controlFlowBlocks[attributeControlFlowLevel]
            return cf != null && cf.isConditionalToAttribute
        }
    private var attributeControlFlowLevel: Int = -1
    val typedArrayIndexToResourceName = mutableMapOf<Pair<String /* var name */, Int /* index number */>, String /* resource name */>()

    //private var conditionStmt: ConditionStmt = ConditionStmt.NONE
    val isConditionalToAttributeViaSwitchStmt : Boolean
        get() {
            if (attributeControlFlowLevel == -1) return false
            val cf = controlFlowBlocks[attributeControlFlowLevel]
            return isConditionalToAttribute && cf != null && cf.type == ConditionStmt.SWITCH
        }
    val isConditionalToAttributeViaIfStmt : Boolean
        get() {
            if (attributeControlFlowLevel == -1) return false
            val cf = controlFlowBlocks[attributeControlFlowLevel]
            return isConditionalToAttribute && cf != null && cf.type == ConditionStmt.SWITCH
        }

    val withinSwitchResourceIdBlock: Boolean
        get() {
            if (switchResourceIdControlFlowLevel == -1) return false
            val cf = controlFlowBlocks[switchResourceIdControlFlowLevel]
            return cf != null && cf.isResourceIdSwitch
        }
    private var switchResourceIdControlFlowLevel: Int = -1

    private var conditionalToAttributeBeingTrueControlFlowLevel: Int = -1
    private var conditionalToTypeValueControlFlowLevel : Int = -1

    var currentAttributeName = ""
        private set

    private val currentAttribute : AttributeInSource
        get() {
            ensureThereIsConditionalAttribute()
            return attributes[currentAttributeName] as AttributeInSource
        }

    private val attributes = mutableMapOf<String, AttributeInSource>()
    private val setters = mutableMapOf<Int, Setter>()
    private val variables : MutableMap<String, Variable> = parametersAsVariables.associateBy { it.name.asString() }.toMutableMap()

    private val variableNameToAttribute = mutableMapOf<String, MutableSet<String>>()
    private val controlFlowBlocks = mutableMapOf<Int, ControlFlowBlock>()
    var currentLevel = 1

    private data class ControlFlowBlock(val level: Int, val type: ConditionStmt, val isResourceIdSwitch: Boolean = false, val isConditionalToAttribute: Boolean = false, val attributeIsConditionalOfBeingTrue: Boolean = false, val conditionalToTypeValue: Boolean = false)

    fun beforeInvokingHandlers(interpreter: FlowInMethodInterpreter) {
        currentLevel = interpreter.currentLevel
        val closedCFs = controlFlowBlocks.values.filter { it.level > currentLevel }
        closedCFs.forEach { cf ->
            when {
                cf.isConditionalToAttribute -> attributeControlFlowLevel = -1
                cf.isResourceIdSwitch -> switchResourceIdControlFlowLevel = -1
                cf.attributeIsConditionalOfBeingTrue -> conditionalToAttributeBeingTrueControlFlowLevel = -1
                cf.conditionalToTypeValue -> conditionalToTypeValueControlFlowLevel = -1
            }
            controlFlowBlocks.remove(cf.level)
        }
    }

    fun injectAttributes(flowInterpreter: FlowInMethodInterpreter) {
        flowInterpreter.attributes = attributes
    }
    fun injectSetters(flowInterpreter: FlowInMethodInterpreter) {
        flowInterpreter.setters = setters
    }
    fun injectVariableNamesToAttributes(flowInterpreter: FlowInMethodInterpreter) {
        flowInterpreter.variablesToAttributes = variablesToAttributes
    }
    fun injectMethodCallsOnFieldsToAttributes(flowInterpreter: FlowInMethodInterpreter) {
        flowInterpreter.methodCallsOnFieldsToAttributes = methodCallsOnFieldsToAttributes
    }

    fun addVariable(variableDeclarator: VariableDeclarator, initializer: Expression) {
        val variable = Variable(variableDeclarator.name, variableDeclarator.type, initializer)
        variables[variableDeclarator.nameAsString] = variable
    }

    fun addTypedArrayVariable(name: SimpleName) {
        typedArrayVariableNames.add(name)
    }
    fun setTypedValueVariable(name: SimpleName) {
        typedValueVariableName = name
    }

    fun addConditionExpressionsToVariable(variableName: String, conditionExpressions: List<Expression>) {
        val variable = getVariable(variableName)
        variable.conditionExpressions.addAll(conditionExpressions)
    }

    fun addAttribute(resourceName: String) {
        val name = manager.getAttributeNameFromResourceName(resourceName)
        val attribute = AttributeInSource(classInfo.xdDeclaredType.targetClassName, name, resourceName)
        attributes[attribute.name] = attribute
    }

    fun flagAttributeAsConditional (resourceName: String, conditionStmt: ConditionStmt) {
        attributeControlFlowLevel = currentLevel
        controlFlowBlocks[currentLevel] = ControlFlowBlock(currentLevel, conditionStmt, isConditionalToAttribute = true)
        val attribute = getAttributeByResourceName(resourceName)
        currentAttributeName = attribute.name
    }

    fun flagConditionalToAttributeBeingTrue() {
        conditionalToAttributeBeingTrueControlFlowLevel = currentLevel
        controlFlowBlocks[currentLevel] = ControlFlowBlock(currentLevel, ConditionStmt.IF, attributeIsConditionalOfBeingTrue = true)
    }

    fun flagConditionalToTypeValue() {
        conditionalToTypeValueControlFlowLevel = currentLevel
        controlFlowBlocks[currentLevel] = ControlFlowBlock(currentLevel, ConditionStmt.IF, conditionalToTypeValue = true)
    }

    fun isAttributeConditionalToTrue(): Boolean {
        return conditionalToAttributeBeingTrueControlFlowLevel != -1 && currentLevel >= conditionalToAttributeBeingTrueControlFlowLevel
    }

    fun unflagAttributeAsConditional() {
        currentAttributeName = ""
        controlFlowBlocks.remove(attributeControlFlowLevel)
        attributeControlFlowLevel = -1
    }

    fun flagWithinSwitchResourceIdBlock() {
        switchResourceIdControlFlowLevel = currentLevel
        controlFlowBlocks[currentLevel] = ControlFlowBlock(currentLevel, ConditionStmt.SWITCH, isResourceIdSwitch = true)
    }

    fun setResourceIdVariable(name: SimpleName) {
        resourceIdVariableName = name
    }

    fun isAttributeValueRetrievedWithMethodCall(methodCallExpr: MethodCallExpr, checkIfResourceIdIsProvidedAsArgument: Boolean = true): Boolean {
        // 1. check if method name is  known as typed array resource value getter
        if (!typedArrayInfo.isTypedArrayGetter(methodCallExpr)) return false /* 1 */
        val getter = typedArrayInfo.getTypedArrayGetter(methodCallExpr)
        if (!getter.isResourceValueGetter) return false

        // 2. check if method is called on typedarray variable name
        if (!isMethodCalledOnTypedArrayVariable(methodCallExpr)) return false

        // 3. check if resource id is provided as argument
        if(checkIfResourceIdIsProvidedAsArgument && !isResourceIdProvidedAsArgument(methodCallExpr)) return false

        return true
    }

    // do we have attributes.getValue(resourceName, typedValue)?
    fun isTypedValueAssignedWithMethodCall(methodCallExpr: MethodCallExpr): Boolean {
        // is method called on typedarray variable + is method name getValue
        if (isMethodCalledOnTypedArrayVariable(methodCallExpr)
                && methodCallExpr.nameAsString == "getValue"
                && typedArrayInfo.isTypedArrayMethod(methodCallExpr)) {
            // is resource id provided as argument?
            return isResourceIdProvidedAsArgument(methodCallExpr)
        }
        return false
    }

    private fun isMethodCalledOnTypedArrayVariable(methodCallExpr: MethodCallExpr): Boolean {
        if (methodCallExpr.scope.isPresent && methodCallExpr.scope.get() is NameExpr) {
            val scope = methodCallExpr.scope.get() as NameExpr
            if (typedArrayVariableNames.contains(scope.name)) {
                return true
            }
        }
        return false
    }

    private fun isResourceIdProvidedAsArgument(methodCallExpr: MethodCallExpr): Boolean {
        val resourceNames = if (isAnyParameterDeclaredAsResourceName) {
            resourceNamesToParamNames.keys
        } else {
            ensureThereIsConditionalAttribute()
            listOf(currentAttribute.resourceName)
        }
        val parameterNames = when {
            isAnyParameterDeclaredAsResourceName -> resourceNamesToParamNames.values
            resourceIdVariableName != null -> listOf((resourceIdVariableName as SimpleName).asString())
            else -> throw  IllegalStateException("No resource ids are declared as parameters and there is no known resource id variable name")
        }

        return methodCallExpr.arguments.any { arg ->
            (arg is NameExpr && parameterNames.any {it == arg.nameAsString })
            ||
            (arg is FieldAccessExpr && resourceNames.any {it == arg.nameAsString})

        }
    }

    fun getAttributeFromResourceNameArgument(methodCallExpr: MethodCallExpr): AttributeInSource {
        resourceNamesToParamNames.forEach { resourceNameToParamName ->
            val isArgumentInCall = methodCallExpr.arguments.any { arg ->
                arg is NameExpr && arg.nameAsString == resourceNameToParamName.value
            }
            if (isArgumentInCall) {
                val name = manager.getAttributeNameFromResourceName(resourceNameToParamName.key)
                return attributes[name] as AttributeInSource
            }
        }
        throw IllegalStateException("No attribute can be found by resource name from method call $methodCallExpr")
    }

    fun getAttributeByResourceId(resourceId: String): AttributeInSource {
        val name = manager.getAttributeNameFromResourceName(resourceId)
        return attributes[name] as AttributeInSource
    }

    fun isMethodCalledWithTypedArrayAsParameter(methodCallExpr: MethodCallExpr): Boolean {
        return methodCallExpr.arguments.any { it is NameExpr && typedArrayVariableNames.contains(it.name)  }
    }

    fun isVariableAssignedInCurrentMethod(variableName: String): Boolean {
        return variables.containsKey(variableName)
    }

    fun isVariableDerivedFromAttribute(variableName: String, attributeName: String? = null): Boolean {
        val attribute = if (attributeName == null) currentAttribute else getAttribute(attributeName)
        return attribute.derivedVariables.containsKey(variableName)
    }

    fun isVariableDerivedFromAnyAttribute(variableName: String): Boolean {
        return variableNameToAttribute.containsKey(variableName)
    }

    fun isMethodCallConditionalToAttributeAndFromCurrentClass(methodCallExpr: MethodCallExpr): Boolean {
        return isConditionalToAttribute && classInfo.isPublicMethodCallFromThisClassOrSuperClass(methodCallExpr)
    }

    fun addVariableAsDerivedFromAttribute(variableName: String, methodCallExpr: MethodCallExpr, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) providedAttribute else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val fieldMember = classInfo.getFieldDeclarationFromThisClassOrSuperClass(variableName)
        if (fieldMember != null) {
            variablesToAttributes[variableName] = attribute.name
            // for the case, int m1 = m2 = m3 = attr
            // where each m is a private member that will be mapped to its own setter
            if (variablesToAttributes.values.count { it == attribute.name } > 1) {
                attribute.oneFormatRequiresMultipleSetters = true
            }
        } else {
            attribute.derivedVariables[variableName] = VariableImpact.ASSIGNED

            val variable = getVariable(variableName)

            // clear any other attributes as being derived previously from this variable (variable might be re-assigned
            variable.attributeImpactExpression.clear()
            variableNameToAttribute.remove(variableName)

            variable.isDerivedFromAttribute = true
            variable.attributeImpactExpression[attribute.name] = methodCallExpr
            addVariableNameToAttributeEntry(variableName, attribute)
        }
    }

    fun addIndirectVariableAsDerivedFromAttribute(variableName: String, assignExpression: Expression, impact: VariableImpact) {
        ensureThereIsConditionalAttribute()
        if (impact == VariableImpact.ASSIGNED) throw IllegalArgumentException("Variable Impact cannot be 'Assigned' with indirect variables")
        currentAttribute.derivedVariables[variableName] = impact
        val variable = getVariable(variableName)
        variable.isDerivedFromAttribute = true
        variable.attributeImpactExpression[currentAttributeName] = assignExpression
        addVariableNameToAttributeEntry(variableName)
    }

    fun addMethodCallOnFieldAsBeingSetByAttribute(methodCallExpr: MethodCallExpr, providedAttribute: AttributeInSource?) {
        val attribute = if (providedAttribute != null) providedAttribute else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }
        // ensure only one argument
        if (methodCallExpr.arguments.size != 1) throw java.lang.IllegalArgumentException("Only one argument method calls are supported for deriving setters")
        methodCallsOnFieldsToAttributes[methodCallExpr] = attribute.name
    }

    private fun addVariableNameToAttributeEntry(variableName: String, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) providedAttribute else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }
        if (!variableNameToAttribute.containsKey(variableName)) {
            variableNameToAttribute[variableName] = mutableSetOf()
        }
        variableNameToAttribute[variableName]?.add(attribute.name)
    }

    fun setTypedArrayGetterForAttribute(methodCallExpr: MethodCallExpr, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        } else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }
        val typedArrayMethod = typedArrayInfo.getTypedArrayGetter(methodCallExpr)
        attribute.targetClassNames.clear()
        attribute.targetClassNames.add(typedArrayMethod.returnTypeClassName)
        var defaultValue = typedArrayInfo.getDefaultValueFromTypedArrayGetterCall(methodCallExpr)
        if (defaultValue != null) {
            if (typedArrayMethod.returnTypeClassName.toLowerCase().contains("float")
                    && !defaultValue.endsWith("f")) {
                defaultValue += "f"
            }
            attribute.defaultValue = defaultValue
        }
        // if format is unspecified and typed array getter indicates format type, set that format type
        if (typedArrayMethod.returnsGuessedFormat != StyleableAttributeFormat.Unspecified
                && ((attribute.formats.size == 1 && attribute.formats.first() == StyleableAttributeFormat.Unspecified)
                || attribute.formats.isEmpty())
        ) {
            attribute.formats.clear()
            attribute.formats.add(typedArrayMethod.returnsGuessedFormat)
            manager.addNonDefaultAttributeIfNew(attribute)
        }
    }

    fun addSetterToAttribute(
            methodCallExpr: MethodCallExpr,
            parameterIndex: Int,
            otherAttributeNames: List<String>,
            providedAttribute: AttributeInSource? = null
    ) {
        val setterInfo = classInfo.getMethodInfoByCallExpr(methodCallExpr) as XdMethod
        addSetterToAttribute(setterInfo, parameterIndex, otherAttributeNames, providedAttribute, methodCallExpr)
    }

    fun addSetterToAttribute(
            setterInfo: XdMethod,
            parameterIndex: Int,
            otherAttributeNames: List<String>,
            providedAttribute: AttributeInSource? = null,
            methodCallExpr: MethodCallExpr? = null
    ) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        }  else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val setter = manager.getSetter(setters, setterInfo, methodCallExpr)
        val setterHashCode = setter.hashCode()
        setters[setterHashCode] = setter
        manager.linkAttributeAndSetter(attribute, setter, parameterIndex, otherAttributeNames)
        val typesForThisSetter = manager.getAttributeTypesForSetter(attribute.targetClassNames, attribute.defaultValue, parameterIndex)
        attribute.typesPerSetter[setterHashCode] = typesForThisSetter
    }

    // setter is field (e.g., width in ViewGroup.LayoutParams)
    fun addSetterToAttribute(xdField: XdField, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        } else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val setter = manager.getSetter(setters, xdField)
        val setterHashCode = setter.hashCode()
        setters[setterHashCode] = setter
        manager.linkAttributeAndSetter(
                attribute,
                setter,
                Parameter.UNASSOCIATED_TO_PARAMETER,
                listOf(attribute.name)
        )
        val typesForThisSetter = manager.getAttributeTypesForSetter(
                attribute.targetClassNames,
                attribute.defaultValue,
                Parameter.UNASSOCIATED_TO_PARAMETER,
                isField = true
        )
        if (attribute.transformingCodes.isNotEmpty()) {
            typesForThisSetter.transformingCode.addAll(attribute.transformingCodes)
            attribute.transformingCodes.clear()
        }
        attribute.typesPerSetter[setterHashCode] = typesForThisSetter
    }

    // setter is field (e.g., width in ViewGroup.LayoutParams)
    fun addStaticSetterToAttribute(
            classDeclaration: ClassOrInterfaceDeclaration,
            methodDeclaration: MethodDeclaration,
            parameterIndex: Int,
            setterCall: MethodCallExpr? = null,
            providedOtherAttributeNames: List<String>? = null,
            providedAttribute: AttributeInSource? = null
    ) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        } else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }
        val otherAttributeNames = providedOtherAttributeNames ?: listOf(attribute.name)

        val setter = manager.getStaticSetter(setters, classDeclaration, methodDeclaration, setterCall)
        val setterHashCode = setter.hashCode()
        setters[setterHashCode] = setter
        manager.linkAttributeAndSetter(
                attribute,
                setter,
                parameterIndex,
                otherAttributeNames
        )
        val typesForThisSetter = manager.getAttributeTypesForSetter(attribute.targetClassNames, attribute.defaultValue, parameterIndex)
        attribute.typesPerSetter[setterHashCode] = typesForThisSetter
    }

    fun addSetterByDerivedVariables(methodCallExpr: MethodCallExpr, variablesNames: List<String>) {
        ensureListIsNotEmpty(variablesNames)
        val attributes = mutableMapOf<Int, AttributeInSource>()
        variablesNames.forEachIndexed { parameterIndex, name ->
            variableNameToAttribute[name]?.let { attributeNames ->
                attributeNames.forEach {
                    val attribute = getAttribute(it)
                    val impact = attribute.derivedVariables[name] as VariableImpact
                    when (impact) {
                        VariableImpact.ASSIGNED -> attributes[parameterIndex] = attribute
                        VariableImpact.CHAINED -> attributes[parameterIndex] = attribute
                        // currently unused -->
                        //VariableImpact.IF_TRUE -> addSetterWithTransformingCode(methodCallExpr, variablesNames, attribute)
                        else -> throw IllegalStateException("Could not convert variable impact '$impact' to a setter")
                    }
                }
            }
        }
        val attributeNames = attributes.values.map { it.name }
        for ((parameterIndex, attribute) in attributes) {
            addSetterToAttribute(methodCallExpr, parameterIndex, attributeNames, attribute)
        }

        // addSetterToAttribute(methodCallExpr, parameterIndex, attributeNameList, attribute)
        // addSetterToAttribute(methodCallExpr, parameterIndex, attributeNameList, attribute)

    }

    fun isPublicStaticMethod(scope: NameExpr, methodName: String): Boolean {
        val classOrInterfaceDeclaration = getClassDeclarationFromNameExpression(scope) ?: return false
        val methodDeclaration = classOrInterfaceDeclaration.methods.first { it.nameAsString == methodName}
        return methodDeclaration.isPublic
                && methodDeclaration.isStatic
    }

    fun getClassDeclarationFromNameExpression(nameExpr: NameExpr): ClassOrInterfaceDeclaration? {
        val name = nameExpr.nameAsString
        val indexedClasses = DeclaredTypeRegistry.findBySimpleName(name)
        val xdDeclaredType : XdDeclaredType
        xdDeclaredType = if (indexedClasses.size == 1) {
            indexedClasses.first()
        } else {
            val cu = nameExpr.findRootNode() as? CompilationUnit ?: return null
            val importDeclaration =  cu.imports.find { it.name.identifier == name } ?: return null
            val canonicalName = DeclaredSymbolResolver.getCanonicalNameFromImport(importDeclaration)
            indexedClasses.find { c ->
                c.targetClassName.canonicalName == canonicalName
            } ?: return null
        }
        return xdDeclaredType.getClassOrInterfaceDeclaration()
    }

    fun addAttributesFromArguments(methodCallExpr: MethodCallExpr, resourceIdArguments: Map<Int, String>) {
        val method = classInfo.getMethodInfoByCallExpr(methodCallExpr) as XdMethod
        val methodDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(method)
        resourceIdArguments.forEach { paramIndex, resourceName ->
            val parameter = methodDeclaration.parameters[paramIndex]
            resourceNamesToParamNames[resourceName] = parameter.nameAsString
            addAttribute(resourceName)
        }


    }

    fun visitNestedMethodWithTypedArrayParameter(targetExpr: NameExpr? = null, method: XdMethod) {
        val methodDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(method)
        val oldTypedArrayVariableNames = typedArrayVariableNames.toSet()
        val typedArrayParameter = methodDeclaration.parameters.first { it.type.resolve().describe() == typedArrayCanonicalName }
        typedArrayVariableNames.clear()
        typedArrayVariableNames.add(typedArrayParameter.name)
        withinNestedMethod = true
        nestedMethodName = methodDeclaration.nameAsString
        if (targetExpr != null) variableNameAssignedByNestedMethod = targetExpr.name
        interpreter.interpretNestedMethod(methodDeclaration)
        nestedMethodName = ""
        variableNameAssignedByNestedMethod = null
        withinNestedMethod = false
        typedArrayVariableNames.clear()
        typedArrayVariableNames.addAll(oldTypedArrayVariableNames)
        resourceNamesToParamNames.clear()
    }

    fun addConditionalImpactToVariableAssignedByNestedMethod(conditionalImpact: ConditionalImpact) {
        val variable = getVariable(variableNameAssignedByNestedMethod.toString())
        if (!variable.conditionalImpact.containsKey(nestedMethodName)) {
            variable.conditionalImpact[nestedMethodName] = mutableListOf()
        }
        variable.conditionalImpact[nestedMethodName]!!.add(conditionalImpact)
    }


    fun checkIfAnyNonDefaultAttributeIsObtained(methodCallExpr: MethodCallExpr) {
        val scope = methodCallExpr.scope.get()
        val typedArrayProviderVariable = if (scope is NameExpr && this.variables.containsKey(scope.nameAsString)) {
            this.variables[scope.nameAsString] as Variable
        } else null
        val attributeProvidingClass = when {
            scope is NameExpr && typedArrayProviderVariable != null -> {
                getAttributeProvidingClass(typedArrayProviderVariable.type)
            }
            scope is MethodCallExpr && classInfo.isMethodCallFromThisClassOrSuperClass(scope) -> {
                val xdMethod =  classInfo.getMethodInfoByCallExpr(scope) as XdMethod
                getAttributeProvidingClass(xdMethod)
            }
            scope is NameExpr && classInfo.isFieldFromThisClassOrSuperClass(scope.nameAsString) -> {
                val xdField = classInfo.getFieldFromThisClassOrSuperClass(scope.nameAsString) as XdField
                getAttributeProvidingClass(xdField)
            }
            scope is NameExpr -> {
                try {
                    val type = JavaParser.parseClassOrInterfaceType(scope.nameAsString)
                    getAttributeProvidingClass(type)
                } catch (e: Exception) {
                    throw IllegalArgumentException("Could not determine attribute providing class type for scope name ${scope.nameAsString}\nOriginal exception: $e")
                }
            }
            else -> throw IllegalArgumentException("Could not determine attribute providing class type")
        }

        val resourceParamContext = getAttributeResourceIdParameterContext(attributeProvidingClass, methodCallExpr)
        if (resourceParamContext.attributesResourceIdParamIndex != null) {
            val variableDeclarator = methodCallExpr.firstAncestorOfType(VariableDeclarator::class.java)
            val typedArrayVariable = if (variableDeclarator != null) {
                this.variables[variableDeclarator.nameAsString]
            } else null
            val argument = methodCallExpr.arguments[resourceParamContext.attributesResourceIdParamIndex]
            when {
                argument is FieldAccessExpr -> {
                    addNonDefaultAttributeForFieldAccessExpr(argument, typedArrayVariable, methodCallExpr)
                }
                argument is NameExpr && classInfo.isFieldFromThisClassOrSuperClass(argument.nameAsString) -> {
                    val fieldDeclaration = classInfo.getResolvedFieldFromThisClassOrSuperClass(argument.asNameExpr().nameAsString)
                    addNonDefaultAttributeFromFieldArray(fieldDeclaration, argument, typedArrayVariable, methodCallExpr)
                }
                argument is NameExpr && classInfo.isFieldFromParentClass(argument.nameAsString) -> {
                    val fieldDeclaration = classInfo.getResolvedFieldDeclarationFromParentClass(argument.nameAsString)
                    addNonDefaultAttributeFromFieldArray(fieldDeclaration, argument, typedArrayVariable, methodCallExpr)
                }
                else -> throw IllegalStateException("Could not convert argument '$argument' containing attribute resource ids into field access expressions")
            }
        }
    }

    private fun addNonDefaultAttributeFromFieldArray(
            fieldDeclaration: FieldDeclaration,
            argument: NameExpr,
            typedArrayVariable: Variable? = null,
            methodCallExpr: MethodCallExpr? = null
    ) {
        val variableDeclarator = fieldDeclaration.variables.first { it.nameAsString == argument.nameAsString }
        val initializerExpr = variableDeclarator.initializer.get()
        val arrayInitializerExpr = when (initializerExpr) {
            is ArrayCreationExpr -> initializerExpr.initializer.get()
            is ArrayInitializerExpr -> initializerExpr
            else -> throw IllegalStateException("Cannot retrieve attribute value expressions from field ${argument.nameAsString}, class ${fieldDeclaration.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java)?.nameAsString}")
        }
        val attrs = manager.getAnyNonDefaultAttributeObtained(arrayInitializerExpr.values)
        attrs.forEachIndexed { index, attr ->
            typedArrayVariable?.let {
                typedArrayIndexToResourceName[Pair(it.name.asString(), index)] = attr.name
            }
            if (manager.addNonDefaultAttributeIfNew(attr)) {
                val methodCallInfo = if (methodCallExpr != null) {
                    " based on $methodCallExpr call"
                } else ""
                println("\t\t\tY - added attribute ${attr.name} to ${classInfo.xdDeclaredType.targetClassName}$methodCallInfo")
            }
        }
    }

    private fun addNonDefaultAttributeForFieldAccessExpr(
            fieldAccessExpr: FieldAccessExpr,
            typedArrayVariable: Variable? = null,
            methodCallExpr: MethodCallExpr? = null
    ) {
        val attrs = manager.getAnyNonDefaultAttributeObtained(
                listOf(fieldAccessExpr)
        )
        attrs.forEachIndexed { index, attr ->
            typedArrayVariable?.let {
                typedArrayIndexToResourceName[Pair(it.name.asString(), index)] = fieldAccessExpr.nameAsString
            }
            if (manager.addNonDefaultAttributeIfNew(attr)) {
                val methodCallInfo = if (methodCallExpr != null) {
                    " based on $methodCallExpr call"
                } else ""
                println("\t\t\tY - added attribute ${attr.name} to ${classInfo.xdDeclaredType.targetClassName}$methodCallInfo")
            }
        }
    }

    private fun getAttributeProvidingClass(type: Type): ClassInfo {
        val typeAsString = type.asString()
        if (!attributeProvidingClasses.containsKey(typeAsString)) {
            if (type !is ClassOrInterfaceType) throw IllegalArgumentException("$type is not a class, enum or interface type")
            val xdClass = DeclaredSymbolResolver.resolveDeclaredType(type)
            val classDeclaration = xdClass.getClassOrInterfaceDeclaration()
            if (!Store.transactional { xdClass.resolvedBody }) {
                TypeBodyPeeker.peek(classDeclaration, xdClass)
            }
            attributeProvidingClasses[typeAsString] = ClassInfo(xdClass, classDeclaration)
        }
        return attributeProvidingClasses[typeAsString] as ClassInfo
    }
    private fun getAttributeProvidingClass(xdMethod: XdMethod): ClassInfo {
        val canonicalName = xdMethod.describedReturnType
        if (!attributeProvidingClasses.containsKey(canonicalName)) {
            val xdClass = Store.transactional {
                XdDeclaredType.query(
                        XdDeclaredType::canonicalName eq canonicalName
                ).first()
            }
            val classDeclaration = xdClass.getClassOrInterfaceDeclaration()
            if (!Store.transactional { xdClass.resolvedBody }) {
                TypeBodyPeeker.peek(classDeclaration, xdClass)
            }
            attributeProvidingClasses[canonicalName] = ClassInfo(xdClass, classDeclaration)
        }
        return attributeProvidingClasses[canonicalName] as ClassInfo
    }
    private fun getAttributeProvidingClass(xdField: XdField): ClassInfo {
        val canonicalName = xdField.describedType
        if (!attributeProvidingClasses.containsKey(canonicalName)) {
            val xdClass = Store.transactional {
                XdDeclaredType.query(
                        XdDeclaredType::canonicalName eq canonicalName
                ).first()
            }
            val classDeclaration = xdClass.getClassOrInterfaceDeclaration()
            if (!Store.transactional { xdClass.resolvedBody }) {
                TypeBodyPeeker.peek(classDeclaration, xdClass)
            }
            attributeProvidingClasses[canonicalName] = ClassInfo(xdClass, classDeclaration)
        }
        return attributeProvidingClasses[canonicalName] as ClassInfo
    }

    private fun getAttributeResourceIdParameterContext(
            attributeProvidingClass: ClassInfo,
            methodCallExpr: MethodCallExpr
    ): ResourceParamContext {
        val xdMethod = attributeProvidingClass.getMethodInfoByCallExpr(
                methodCallExpr, checkIfMethodIsCalledFromCurrentClass = false
        ) as XdMethod
        val hashCode = Setter.getHashCodeFromMethodInfo(xdMethod)
        if (!attributeResourceIdParameterIndexes.containsKey(hashCode)) {
            val resourceParamContext = Store.transactional {
                // ugh, TintTypedArray doesn't use annotations
                val anyAnnotationUsed = xdMethod.parameters
                        .toList()
                        .any { xdParam ->
                            xdParam.annotationNames.isNotEmpty()
                        }
                return@transactional if (anyAnnotationUsed) {
                    val xdStylableResIdParam = xdMethod.parameters.query(
                            XdConstructorOrMethodParameter::annotationNames contains "StyleRes"
                    ).firstOrNull()
                    val xdAttrResIdsParam = xdMethod.parameters.query(
                            XdConstructorOrMethodParameter::annotationNames contains "StyleableRes"
                    ).firstOrNull()
                    ResourceParamContext(
                            xdStylableResIdParam?.index,
                            xdAttrResIdsParam?.index
                    )
                } else { // TintTypedArray case, fallback to param names
                    val xdStylableResIdParam = xdMethod.parameters.query(
                            XdConstructorOrMethodParameter::name eq "resid"
                    )
                            .toList()
                            .firstOrNull { xdParam -> xdParam.describedType == "int" }
                    val xdAttrResIdsParam = xdMethod.parameters.query(
                            XdConstructorOrMethodParameter::name eq "attrs"
                    )
                            .toList()
                            .firstOrNull {xdParam -> xdParam.describedType == "int[]"}
                    ResourceParamContext(
                            xdStylableResIdParam?.index,
                            xdAttrResIdsParam?.index
                    )
                }
            }
            attributeResourceIdParameterIndexes[hashCode] = resourceParamContext
        }
        return attributeResourceIdParameterIndexes[hashCode] as ResourceParamContext
    }

    private fun getVariable(variableName: String): Variable {
        ensureVariableIsAssigned(variableName)
        return variables[variableName] as Variable
    }

    fun getAttribute(attributeName: String): AttributeInSource {
        ensureAttributeExists(attributeName)
        return attributes[attributeName] as AttributeInSource
    }

    private fun getAttributeByResourceName(resourceName: String): AttributeInSource {
        return attributes.values.find { attr -> attr.resourceName == resourceName }
                ?: throw IllegalArgumentException("No attribute known with resource name $resourceName")
    }

    private fun ensureThereIsConditionalAttribute() {
        if (!isConditionalToAttribute) throw IllegalStateException("Conditional attribute flag must be true before performing method")
        if (currentAttributeName == "") throw IllegalStateException("Conditional Attribute is flagged to true, but no current attribute name is set.. This should nevere happen")
    }

    private fun ensureAttributeExists(attributeName: String) {
        if (!attributes.containsKey(attributeName)) throw IllegalStateException("Attribute '$attributeName' does not exist")
    }

    private fun ensureVariableIsAssigned(variableName: String) {
        if (!variables.containsKey(variableName)) throw IllegalStateException("Variable '$variableName' is not yet assigned")
    }

    private fun ensureListIsNotEmpty(list: List<String>) {
        if (list.isEmpty()) throw IllegalStateException("At least one attributesToParameters item is required")
    }
    private data class ResourceParamContext (
            val styleableResourceIdParamIndex: Int?,
            val attributesResourceIdParamIndex: Int?
    )
    companion object {
        private val typedArrayCanonicalName = TypedArray::class.java.canonicalName
        private val attributeProvidingClasses = mutableMapOf<String /* Type */, ClassInfo>()
        private val attributeResourceIdParameterIndexes =
                mutableMapOf<
                        Int /* accessor hash code */,
                        ResourceParamContext
                >()


    }

}