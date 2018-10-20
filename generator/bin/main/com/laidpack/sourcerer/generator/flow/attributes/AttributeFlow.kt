package com.laidpack.sourcerer.generator.flow.attributes

import android.content.res.TypedArray
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.type.Type
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.peeker.TypedArrayInfo
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Parameter
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.target.TransformingCode
import com.squareup.kotlinpoet.ClassName

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

/** essentially state holder for classOrInterfaceDeclarationProvider handlers **/
class AttributeFlow (
        val attributeSetVariableName: SimpleName,
        private val manager: AttributeManager,
        private val interpreter: FlowInMethodInterpreter,
        val classInfo: ClassInfo,
        val typedArrayInfo: TypedArrayInfo,
        val parametersAsVariables: List<Variable>,
        private val classRegistry: ClassRegistry
) {
    // API:
    val memberFieldsToAttributes = mutableMapOf<FieldDeclaration, String>()
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

    private var currentAttributeName = ""

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
        val name = manager.getNameFromResourceId(resourceName)
        val attribute = AttributeInSource(classInfo.targetClassName, name, resourceName)
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
                val name = manager.getNameFromResourceId(resourceNameToParamName.key)
                return attributes[name] as AttributeInSource
            }
        }
        throw IllegalStateException("No attribute can be found by resource name from method call $methodCallExpr")
    }

    fun getAttributeByResourceId(resourceId: String): AttributeInSource {
        val name = manager.getNameFromResourceId(resourceId)
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
        return isConditionalToAttribute && classInfo.isPublicMethodCallFromThisClass(methodCallExpr)
    }

    fun addVariableAsDerivedFromAttribute(variableName: String, methodCallExpr: MethodCallExpr, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) providedAttribute else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val fieldMember = classRegistry.getFieldFromThisClassOrSuperClass(variableName, classInfo)
        if (fieldMember != null) {
            memberFieldsToAttributes[fieldMember] = attribute.name
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
    }

    fun addSetterToAttribute(methodCallExpr: MethodCallExpr, parameterIndex: Int, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        }  else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val setterInfo = classInfo.getMethodInfoByCallExpr(methodCallExpr) as MethodInfo
        val setter = manager.getSetter(setters, setterInfo, methodCallExpr)
        val setterHashCode = setter.hashCode()
        setters[setterHashCode] = setter
        manager.linkAttributeAndSetter(attribute, setter, parameterIndex)
        val typesForThisSetter = manager.getAttributeTypesForSetter(attribute.targetClassNames, attribute.defaultValue, parameterIndex)
        if (attribute.transformingCodes.isNotEmpty()) {
            typesForThisSetter.transformingCode.addAll(attribute.transformingCodes)
            attribute.transformingCodes.clear()

        }
        attribute.typesPerSetter[setterHashCode] = typesForThisSetter

    }

    // setter is field (e.g., width in ViewGroup.LayoutParams)
    fun addSetterToAttribute(fieldDeclaration: FieldDeclaration, providedAttribute: AttributeInSource? = null) {
        val attribute = if (providedAttribute != null) {
            providedAttribute
        } else {
            ensureThereIsConditionalAttribute()
            currentAttribute
        }

        val setter = manager.getSetter(setters, fieldDeclaration)
        val setterHashCode = setter.hashCode()
        setters[setterHashCode] = setter
        manager.linkAttributeAndSetter(attribute, setter, Parameter.UNASSOCIATED_TO_PARAMETER)
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

    fun addSetterByDerivedVariables(methodCallExpr: MethodCallExpr, variablesNames: List<String>) {
        ensureListIsNotEmpty(variablesNames)
        variablesNames.forEachIndexed { parameterIndex, name ->
            variableNameToAttribute[name]?.let { attributeNames ->
                attributeNames.forEach {
                    val attribute = getAttribute(it)
                    val impact = attribute.derivedVariables[name] as VariableImpact
                    when (impact) {
                        VariableImpact.ASSIGNED -> addSetterToAttribute(methodCallExpr, parameterIndex, attribute)
                        VariableImpact.CHAINED -> addSetterToAttribute(methodCallExpr, parameterIndex, attribute)
                        // currently unused -->
                        //VariableImpact.IF_TRUE -> addSetterWithTransformingCode(methodCallExpr, variablesNames, attribute)
                        else -> throw IllegalStateException("Could not convert variable impact '$impact' to a setter")
                    }
                }
            }
        }
    }

    private fun addSetterWithTransformingCode(methodCallExpr: MethodCallExpr, variablesNames: List<String>, attribute: AttributeInSource) {
        ensureListIsNotEmpty(variablesNames)
        variablesNames.forEachIndexed { index, name ->
            val variable = getVariable(name)
            val impactExpression = variable.attributeImpactExpression[attribute.name] as AssignExpr
            val value = impactExpression.value.asNameExpr()
            val resolvedValue = value.resolve() as JavaParserFieldDeclaration
            val initializer = resolvedValue.variableDeclarator.initializer.get()
            val valueLiteral = if (initializer.isLiteralStringValueExpr) {
                "/* ${value.nameAsString} */ ${initializer.asLiteralStringValueExpr().value}"
            } else ""
            val conditionLiteral = "true"
            val operator = impactExpression.operator
            val transformingCode = TransformingCode(
                    Boolean::class.java.canonicalName,
                    variable.type.resolve().describe(),
                    index,
                    conditionLiteral,
                    operator.asString(),
                    operator.toString(),
                    valueLiteral
            )
            attribute.transformingCodes.add(transformingCode)
            attribute.targetClassNames.add(transformingCode.toClassNames)
        }
        addSetterToAttribute(methodCallExpr, Parameter.UNASSOCIATED_TO_PARAMETER, attribute)
    }

    fun addAttributesFromArguments(methodCallExpr: MethodCallExpr, resourceIdArguments: Map<Int, String>) {
        val methodInfo = classInfo.getMethodInfoByCallExpr(methodCallExpr) as MethodInfo
        resourceIdArguments.forEach { paramIndex, resourceName ->
            val parameter = methodInfo.methodDeclaration.parameters[paramIndex]
            resourceNamesToParamNames[resourceName] = parameter.nameAsString
            addAttribute(resourceName)
        }


    }

    fun visitNestedMethodWithTypedArrayParameter(targetExpr: NameExpr? = null, methodInfo: MethodInfo) {
        val oldTypedArrayVariableNames = typedArrayVariableNames.toSet()
        val typedArrayParameter = methodInfo.methodDeclaration.parameters.first { it.type.resolve().describe() == typedArrayCanonicalName }
        typedArrayVariableNames.clear()
        typedArrayVariableNames.add(typedArrayParameter.name)
        withinNestedMethod = true
        nestedMethodName = methodInfo.methodDeclaration.nameAsString
        if (targetExpr != null) variableNameAssignedByNestedMethod = targetExpr.name
        interpreter.interpretNestedMethod(methodInfo)
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

    private fun getVariable(variableName: String): Variable {
        ensureVariableIsAssigned(variableName)
        return variables[variableName] as Variable
    }

    private fun getAttribute(attributeName: String): AttributeInSource {
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
    companion object {
        private val typedArrayCanonicalName = TypedArray::class.java.canonicalName
    }

}