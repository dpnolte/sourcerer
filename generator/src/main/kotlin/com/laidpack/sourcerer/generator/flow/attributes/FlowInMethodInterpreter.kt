package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.attributes.handlers.*
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.FlowVisitor
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.index.*


class FlowInMethodInterpreter (
        private val relevantConstructorOrMethod: CallableDeclaration<*>,
        private val classInfo: ClassInfo,
        typedArrayInfo: TypedArrayInfo,
        attrManager: AttributeManager,
        attributesDefinedInXml: Boolean
) : BaseFlowInterpreter()  {
    private val flow = AttributeFlow(
            this.getAttributeSetVariableNameFromParam(),
            attrManager,
            this,
            classInfo,
            typedArrayInfo,
            this.getParametersAsVariables(),
            attributesDefinedInXml
    )
    private val visitor = FlowVisitor(this::onNodeIteration) {flow.isConditionalToAttribute}
    lateinit var attributes : MutableMap<String, AttributeInSource>
    lateinit var setters: MutableMap<Int, Setter>
    lateinit var variablesToAttributes : Map<String, String>
    lateinit var methodCallsOnFieldsToAttributes : Map<MethodCallExpr, String>


    override val handlers = listOf(
            VariableDeclarationHandler(flow),
            SwitchEntryStmtHandler(flow),
            SwitchStmtHandler(flow),
            ConditionalToAttrBreakStmtHandler(flow),
            ConditionalToAttrAssignHandler(flow),
            ConditionalToAttrMethodCallHandler(flow),
            ConditionalToAttrIfStmtHandler(flow),
            UnconditionalToAttrMethodCallHandler(flow),
            UnconditionalToAttrAssignHandler(flow),
            IfStmtHandler(flow)
    )

    override fun beforeInvokingHandlers() {
        flow.beforeInvokingHandlers(this)
    }

    fun interpret(): Interpretation {
        visitor.explore(relevantConstructorOrMethod)
        flow.injectVariableNamesToAttributes(this)
        if (variablesToAttributes.isNotEmpty()) {
            // try to resolve by analyze potential setters for that member field
            interpretPotentialSettersForPrivateFields()
        }
        flow.injectMethodCallsOnFieldsToAttributes(this)
        if (methodCallsOnFieldsToAttributes.isNotEmpty()) {
            interpretPotentialSettersForMethodCallsOnFields()
        }
        flow.injectAttributes(this)
        flow.injectSetters(this)

        return Interpretation(attributes, setters)
    }

    fun interpretNestedMethod(methodDeclaration: MethodDeclaration) {
        visitor.explore(methodDeclaration, flow.currentLevel + 1)
    }

    private fun interpretPotentialSettersForPrivateFields() {
        // find setters with matching signatures
        for ((variableName, attrName) in variablesToAttributes) {
            val field = classInfo.getFieldFromThisClassOrSuperClass(variableName) as XdField
            val fieldDescribedType = Store.transactional { field.describedType }
            val potentialSetters = classInfo.getPotentialSettersForType(
                    fieldDescribedType,
                    attrName
            )
            val attribute = flow.getAttribute(attrName)
            if (attribute.setterHashCodes.isEmpty()) {
                for (potentialSetter in potentialSetters) {
                    if (interpretPotentialSetterForPrivateField(
                                    potentialSetter,
                                    variableName,
                                    fieldDescribedType,
                                    attribute
                            )) {
                        break
                    }
                }
            }

        }
    }

    private fun interpretPotentialSetterForPrivateField(
            setterMethod: XdMethod,
            variableName: String,
            variableDescribedType: String,
            attribute: AttributeInSource
    ): Boolean {
        // find if any assignment is done to variable
        val setterDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(setterMethod)
        val assignExpressions = setterDeclaration.descendantsOfType(AssignExpr::class.java)
        val parameterName = setterDeclaration.parameters.first().nameAsString
        for (assignExpression in assignExpressions) {
            val target = assignExpression.target
            val value = assignExpression.value
            if ((target is NameExpr && target.nameAsString == variableName
                    || target is FieldAccessExpr && target.scope is ThisExpr && target.nameAsString == variableName)
                    && value is NameExpr && value.nameAsString == parameterName
                ) {
                attribute.targetClassNames.add(0, variableDescribedType)
                flow.addSetterToAttribute(setterMethod, 0, listOf(attribute.name), attribute)
                return true
            }
        }
        return false
    }

    private fun interpretPotentialSettersForMethodCallsOnFields() {
        // find setters with matching signatures
        for ((methodCallExpr, attrName) in methodCallsOnFieldsToAttributes) {
            val attribute = flow.getAttribute(attrName)
            val requiredParameterType = attribute.targetClassNames.first()
            val fieldName = methodCallExpr.scope.get().asNameExpr().nameAsString
            val methodOnFieldName = methodCallExpr.nameAsString

            val potentialSetters = classInfo.getPotentialSettersForType(
                    requiredParameterType,
                    attrName
            )
            for (potentialSetter in potentialSetters) {
                if (interpretPotentialSetterForMethodCallOnField(
                                potentialSetter,
                                fieldName,
                                methodOnFieldName,
                                attribute
                        )) {
                    break
                }
            }
        }
    }

    private fun interpretPotentialSetterForMethodCallOnField(
            setterMethod: XdMethod,
            fieldName: String,
            methodOnFieldName: String,
            attribute: AttributeInSource
    ): Boolean {
        // find if method call is applied on our field
        val setterDeclaration = classInfo.getResolvedMethodDeclarationFromThisClassOrSuperClass(setterMethod)
        val methodCallExpressions = setterDeclaration.descendantsOfType(MethodCallExpr::class.java)
        val parameterName = setterDeclaration.parameters.first().nameAsString
        for (methodCallExpr in methodCallExpressions) {
            if (methodCallExpr.scope.isPresent) {
                val scope = methodCallExpr.scope.get()
                if (scope is NameExpr && scope.nameAsString == fieldName
                    && methodCallExpr.nameAsString == methodOnFieldName
                    && methodCallExpr.arguments.size == 1
                    && methodCallExpr.arguments[0] is NameExpr
                    && methodCallExpr.arguments[0].asNameExpr().nameAsString == parameterName
                ) {
                    flow.addSetterToAttribute(setterMethod, 0, listOf(attribute.name), attribute)
                    return true
                }
            }
        }
        return false
    }

    private fun getAttributeSetVariableNameFromParam(): SimpleName {
        for (parameter in relevantConstructorOrMethod.parameters) {
            if (parameter.type.isClassOrInterfaceType
                    && parameter.type.asClassOrInterfaceType().nameAsString == TypeBodyPeeker.attributeSetClassType.simpleName) {
                val indexedClass = DeclaredSymbolResolver.resolveDeclaredType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == TypeBodyPeeker.attributeSetClassType.canonicalName) {
                    return parameter.name
                }
            }
        }
        throw IllegalStateException("relevantConstructorOrMethod has no AttributeSet parameter. Is peeker really peeking the right method constructor?")
    }

    private fun getParametersAsVariables(): List<Variable> {
        val variables = mutableListOf<Variable>()
        for (parameter in relevantConstructorOrMethod.parameters) {
            variables.add(Variable(parameter.name, parameter.type, parameter.nameAsExpression))
        }
        return variables
    }


}



