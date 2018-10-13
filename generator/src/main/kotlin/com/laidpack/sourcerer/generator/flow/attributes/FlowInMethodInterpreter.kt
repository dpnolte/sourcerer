package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.SimpleName
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.attributes.handlers.*
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.FlowVisitor
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.peeker.*


class FlowInMethodInterpreter (
        private val relevantConstructorOrMethod: Pair<ResolvedMethodLikeDeclaration, CallableDeclaration<*>>,
        private val classInfo: ClassInfo,
        typedArrayInfo: TypedArrayInfo,
        attrManager: AttributeManager,
        classRegistry: ClassRegistry
) : BaseFlowInterpreter()  {
    private val flow = AttributeFlow(
            this.getAttributeSetVariableNameFromParam(),
            attrManager,
            this,
            classInfo,
            typedArrayInfo,
            this.getParametersAsVariables(),
            classRegistry
    )
    private val visitor = FlowVisitor(this::onNodeIteration) {flow.isConditionalToAttribute}
    lateinit var attributes : Map<String, Attribute>
    lateinit var setters: MutableMap<Int, Setter>
    lateinit var variablesToAttributes : Map<String, String>


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
        visitor.explore(relevantConstructorOrMethod.second)
        flow.injectVariableNamesToAttributes(this)
        if (variablesToAttributes.isNotEmpty()) {
            // try to resolve by analyze potential setters for that member field
            interpretPotentialSettersForPrivateFields()
        }
        flow.injectAttributes(this)
        flow.injectSetters(this)

        return Interpretation(attributes, setters)
    }

    fun interpretNestedMethod(methodInfo: MethodInfo) {
        visitor.explore(methodInfo.methodDeclaration, flow.currentLevel + 1)
    }

    private fun interpretPotentialSettersForPrivateFields() {
        // find setters with matching signatures
        for ((variableName, attrName) in variablesToAttributes) {
            val field = flow.getField(variableName)
            val variable = field.variables.first { it.nameAsString == variableName }
            val variableDescribedType = variable.describeType()
            // find method with 1 parameter of variable type and return type void
            // first try conventional setter name before looping through all methods
            val conventionalSetterName = "set${if(variableName.startsWith("m")) variableName.substring(1) else variableName.capitalize()}"
            var hasFoundSetter = false
            if (classInfo.methodDeclarations.containsKey(conventionalSetterName)) {
                for (methodInfo in classInfo.methodDeclarations[conventionalSetterName] as List<MethodInfo>) {
                    hasFoundSetter = interpretPotentialSetterForPrivateField(
                            methodInfo,
                            variableName,
                            variableDescribedType,
                            attrName
                    )
                    if (hasFoundSetter) break
                }
            }
            if (!hasFoundSetter) {
                for (methodInfo in classInfo.methodDeclarations.values.flatten()) {
                    hasFoundSetter = interpretPotentialSetterForPrivateField(
                            methodInfo,
                            variableName,
                            variableDescribedType,
                            attrName
                    )
                    if (hasFoundSetter) break
                }
            }

        }
    }

    fun interpretPotentialSetterForPrivateField(
            methodInfo: MethodInfo,
            variableName: String,
            variableDescribedType: String,
            attrName: String
    ): Boolean {
        if (hasPotentialSetterRightSignatureForVariable(methodInfo, variableDescribedType)) {
            // find if any assignment is done to variable
            val assignExpressions = methodInfo.methodDeclaration.descendantsOfType(AssignExpr::class.java)
            val parameterName = methodInfo.methodDeclaration.parameters.first().nameAsString
            val attribute = flow.getAttribute(attrName)
            for (assignExpression in assignExpressions) {
                val target = assignExpression.target
                val value = assignExpression.value
                if (target is NameExpr && target.nameAsString == variableName
                        && value is NameExpr && value.nameAsString == parameterName
                    ) {
                    flow.addSetterToAttribute(methodInfo, 0, listOf(attrName), attribute)
                }
            }
        }
        return false
    }

    private fun hasPotentialSetterRightSignatureForVariable(methodInfo: MethodInfo, variableDescribedType: String): Boolean {
        return ClassInfo.isEligibleMethod(methodInfo)
            && methodInfo.methodDeclaration.parameters.size == 1
            && try { methodInfo.resolvedMethodDeclaration.returnType.isVoid } catch (e: Exception) { false }
            && methodInfo.methodDeclaration.parameters[0].describeType() == variableDescribedType
    }

    private fun getAttributeSetVariableNameFromParam(): SimpleName {
        for (parameter in relevantConstructorOrMethod.second.parameters) {
            if (parameter.type.isClassOrInterfaceType) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == SourcePeeker.attributeSetClassType.canonicalName) {
                    return parameter.name
                }
            }
        }
        throw IllegalStateException("relevantConstructorOrMethod has no AttributeSet parameter. Is peeker really peeking the right method constructor?")
    }

    private fun getParametersAsVariables(): List<Variable> {
        val variables = mutableListOf<Variable>()
        for (parameter in relevantConstructorOrMethod.second.parameters) {
            variables.add(Variable(parameter.name, parameter.type, parameter.nameAsExpression))
        }
        return variables
    }


}



