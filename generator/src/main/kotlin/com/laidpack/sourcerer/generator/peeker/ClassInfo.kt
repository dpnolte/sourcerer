package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.*
import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType
import com.laidpack.sourcerer.generator.resources.StyleableAttributeEnumValue
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.resources.Widget
import com.laidpack.sourcerer.generator.target.Getter
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query
import kotlinx.dnq.xdRequiredStringProp


data class MethodInfo(
        val resolvedMethodDeclaration: ResolvedMethodDeclaration,
        val methodDeclaration: MethodDeclaration,
        val javadoc: Javadoc
) {
    val line : Int = methodDeclaration.begin.get().line
    fun describeReturnType(): String {
        return try {
            resolvedMethodDeclaration.returnType.describe()
        } catch (e: Exception) {
            val returnType = methodDeclaration.type
            if (returnType.isClassOrInterfaceType) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(returnType.asClassOrInterfaceType())
                indexedClass.targetClassName.canonicalName
            } else throw e
        }
    }
}



data class ConstructorInfo(
        val resolvedConstructorDeclaration: ResolvedConstructorDeclaration,
        val constructorDeclaration: ConstructorDeclaration
)
data class ClassInfo(
        val targetClassName: ClassName,
        val widget: Widget,
        val classCategory: ClassCategory,
        val isViewGroup: Boolean,
        val classDeclaration: ClassOrInterfaceDeclaration,
        val superClassNames: List<ClassName>,
        val constructorOrMethodsWithAttributeSetParamKeys : Map<String, List<Int>>,
        val methodsWithAttributeBlockTagKeys : Map<String, List<Int>>,
        val constructorDeclarations: Map<String, List<ConstructorInfo>>,
        val methodDeclarations: Map<String, List<MethodInfo>>,
        val fieldDeclarations: Map<String, FieldDeclaration>,
        val constructorExpression: ConstructorExpression,
        val indexedClass: IndexedClass,
        val annotations: List<AnnotationExpr>,
        val intDefinedAnnotations: Map<String, AnnotationExpr>
) {
    var hasResolvedAttributes: Boolean = false
    val specifiedAttributes = mutableMapOf<String, Attribute>()
    private var mutableResolvedAttributes = mapOf<String, Attribute>()
    val resolvedAttributes : Map<String, Attribute>
            get() = mutableResolvedAttributes
    val hasSuperClass = superClassNames.isNotEmpty()

    val potentialGetters : List<MethodInfo> by lazy {
        val getters = mutableListOf<MethodInfo>()
        for (methodDeclarationGroup in methodDeclarations) {
            for (methodInfo in methodDeclarationGroup.value) {
                val isVoid = try {
                    methodInfo.resolvedMethodDeclaration.returnType.isVoid
                } catch (e: Exception) { false }
                if (isEligibleGetter(methodInfo) && !isVoid) {
                    getters.add(methodInfo)
                }
            }
        }
        getters
    }

    fun getPotentialGettersForField(field: FieldDeclaration, variableName: String): List<MethodInfo> {
        val fieldTypeAsString = field.describeType(variableName)
        return potentialGetters.filter {
            fieldTypeAsString == it.describeReturnType()
        }
    }

    fun getMethodLikeWithAttributeSetAsParam(): List<Pair<ResolvedMethodLikeDeclaration, CallableDeclaration<*>>> {
        val list = mutableListOf<Pair<ResolvedMethodLikeDeclaration, CallableDeclaration<*>>>()
        constructorOrMethodsWithAttributeSetParamKeys.keys.forEach { key ->
            val isConstructor = constructorDeclarations.containsKey(key)
            constructorOrMethodsWithAttributeSetParamKeys[key]!!.forEach { index ->
                if (isConstructor) {
                    val constructorInfo = constructorDeclarations[key]!![index]
                    list.add(Pair(constructorInfo.resolvedConstructorDeclaration, constructorInfo.constructorDeclaration))
                } else {
                    val methodInfo = methodDeclarations[key]!![index]
                    list.add(Pair(methodInfo.resolvedMethodDeclaration, methodInfo.methodDeclaration))
                }
            }
        }
        return list
    }
    fun getMethodsWithAttributeTagInComments(): List<MethodInfo> {
        val list = mutableListOf<MethodInfo>()
        methodsWithAttributeBlockTagKeys.keys.forEach { key ->
            methodsWithAttributeBlockTagKeys[key]!!.forEach { index ->
                list.add(methodDeclarations[key]!![index])
            }
        }
        return list
    }

    fun getMethodInfoByCallExpr(methodCallExpr: MethodCallExpr): MethodInfo? {
        if(!methodCallExpr.scope.isPresent && methodDeclarations.containsKey(methodCallExpr.nameAsString)) {
            methodDeclarations[methodCallExpr.nameAsString]?.let {methodDeclarations ->
                return if (methodDeclarations.size == 1) {
                    methodDeclarations.first()
                } else {
                    val arguments = methodCallExpr.arguments
                    methodDeclarations.find {
                        var index = -1
                        try {
                            arguments.size == it.methodDeclaration.parameters.size
                                    && it.methodDeclaration.parameters.all { param ->
                                index += 1
                                arguments[index] !is MethodCallExpr
                                        && param.describeType() == arguments[index].calculateResolvedType().describe()
                            }
                        } catch (e: Exception) {
                            throw e
                        }
                    }
                }
            }
        }
        return null
    }

    fun isPublicMethodCallFromThisClass(methodCallExpr: MethodCallExpr): Boolean {
        val methodInfo = this.getMethodInfoByCallExpr(methodCallExpr)
        return methodInfo != null && methodInfo.methodDeclaration.isPublic
    }

    fun isMethodCallFromThisClass(methodCallExpr: MethodCallExpr): Boolean {
        return this.getMethodInfoByCallExpr(methodCallExpr) != null
    }

    fun isFieldFromThisClass(variableName: String): Boolean {
        return this.fieldDeclarations.containsKey(variableName)
    }

    fun isPublicFieldFromThisClass(variableName: String): Boolean {
        return isFieldFromThisClass(variableName) && getFieldFromThisClass(variableName).isPublic
    }

    fun getFieldFromThisClass(variableName: String): FieldDeclaration {
        if (!isFieldFromThisClass(variableName)) throw IllegalStateException("$variableName is not a member variable of $targetClassName")
        return this.fieldDeclarations[variableName] as FieldDeclaration
    }

    fun getSetterMethodInfo(setter: Setter): MethodInfo {
        val methodInfos = methodDeclarations[setter.name] ?: throw IllegalArgumentException("No method info for setter ${setter.name}, no match based on name")
        if (methodInfos.size == 1) return methodInfos.first()

        val hashCode = setter.hashCode()
        return methodInfos.first {
            Setter.getHashCodeFromMethodInfo(it) == hashCode
        }
    }

    fun getGetterMethodInfo(getter: Getter): MethodInfo {
        val methodInfos = methodDeclarations[getter.name] ?: throw IllegalArgumentException("No method info for getter ${getter.name}, no match based on name")
        if (methodInfos.size == 1) return methodInfos.first()

        val hashCode = getter.hashCode()
        return methodInfos.first {
            Getter.getHashCodeFromMethodInfo(it) == hashCode
        }
    }

    fun setResolvedAttributes(attrs : Map<String, Attribute>) {
        mutableResolvedAttributes = attrs
        hasResolvedAttributes = true
    }

    fun convertIntDefinedAnnotationToEnum(
            intDefinedAnnotation: AnnotationExpr
    ): List<StyleableAttributeEnumValue> {
        val enumValues = mutableListOf<StyleableAttributeEnumValue>()
        val arrayInitializerExpr =
                intDefinedAnnotation.firstDescendantOfType(ArrayInitializerExpr::class.java) as ArrayInitializerExpr
        val variables = arrayInitializerExpr.values.map {
            when (it) {
                is NameExpr -> {
                    val name = it.nameAsString
                    val field = fieldDeclarations[name] as FieldDeclaration
                    field.variables.first { it.nameAsString == name }
                }
                is FieldAccessExpr -> {
                    val variable = findVariable(it) ?: throw IllegalStateException("Cannot find field '$it' in @IntDef annotation $intDefinedAnnotation")
                    variable
                }
                else -> throw IllegalStateException("Cannot convert '$it' to a variable")
            }
        }
        for (variable in variables) {
            enumValues.add(StyleableAttributeEnumValue(
                    variable.nameAsString.toLowerCase(),
                    resolveIntValueFromNode(variable))
            )
        }
        return enumValues
    }

    private fun resolveIntValueFromNode(node: Node): Int {
        val initializerExpr = node.firstDescendantOfType(IntegerLiteralExpr::class.java)
        if (initializerExpr != null) {
            return initializerExpr.asInt()
        } else {
            val fieldAccessExpr = node.firstDescendantOfType(FieldAccessExpr::class.java)
            if (fieldAccessExpr != null) {
                return resolveFieldAccessExpr(fieldAccessExpr)
                    ?: throw IllegalStateException("Could not resolve int value for node $node with field access expression $fieldAccessExpr")
            }
            val binaryExpr = node.firstDescendantOfType(BinaryExpr::class.java)
            if (binaryExpr != null) {
                return resolveBinaryExpr(binaryExpr)
                        ?: throw IllegalStateException("Could not resolve int value for node $node with binary expression $binaryExpr")
            }
            val nameExpr = node.firstDescendantOfType(NameExpr::class.java)
                    ?: throw IllegalArgumentException("Cannot resolve node to Int. No integer literal expression, field access expression, binary expression, or name expression found for node '$node'")

            return resolveNameExpr(nameExpr)
        }

    }

    private fun resolveFieldAccessExpr(fieldAccessExpr: FieldAccessExpr): Int? {
        return resolveIntValueFromNode(findVariable(fieldAccessExpr)
            ?: return null)
    }

    private fun resolveBinaryExpr(binaryExpr: BinaryExpr): Int? {
        val leftHandSide = resolveIntValueFromNode(binaryExpr.left)
        val rightHandSide = resolveIntValueFromNode(binaryExpr.right)

        return when (binaryExpr.operator) {
            BinaryExpr.Operator.BINARY_OR -> leftHandSide.or(rightHandSide)
            BinaryExpr.Operator.BINARY_AND -> leftHandSide.and(rightHandSide)
            BinaryExpr.Operator.XOR -> leftHandSide.xor(rightHandSide)
            BinaryExpr.Operator.LEFT_SHIFT -> leftHandSide.shl(rightHandSide)
            BinaryExpr.Operator.SIGNED_RIGHT_SHIFT -> leftHandSide.shr(rightHandSide)
            BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT -> leftHandSide.ushr(rightHandSide)
            BinaryExpr.Operator.PLUS -> leftHandSide + rightHandSide
            BinaryExpr.Operator.MINUS -> leftHandSide - rightHandSide
            BinaryExpr.Operator.MULTIPLY -> leftHandSide * rightHandSide
            BinaryExpr.Operator.DIVIDE -> leftHandSide / rightHandSide
            BinaryExpr.Operator.REMAINDER -> leftHandSide % rightHandSide
            else -> null
        }
    }

    private fun resolveNameExpr(nameExpr: NameExpr): Int {
        val classDeclaration = nameExpr.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java)
        val variableDeclarator = classDeclaration?.firstDescendantOfType(
                VariableDeclarator::class.java
        ) { n -> n.nameAsString == nameExpr.nameAsString}
            ?: throw java.lang.IllegalArgumentException("No variable found with name $nameExpr")
        return resolveIntValueFromNode(variableDeclarator)
    }

    private fun findVariable(fieldAccessExpr: FieldAccessExpr): VariableDeclarator? {
        val simpleName = fieldAccessExpr.scope.asNameExpr().nameAsString
        val fieldName = fieldAccessExpr.name.asString()
        val foundClasses = ClassRegistry.findBySimpleName(simpleName)
        if (foundClasses.isNotEmpty()) {
            for (foundClass in foundClasses) {
                val classDeclaration = foundClass.node
                return classDeclaration.firstDescendantOfType(
                        VariableDeclarator::class.java
                ) { v -> v.nameAsString == fieldName } ?: continue
            }
        } else {
            // check if it is defined with an annotation declaratino
            // example: @interface Gutter { int NONE = 0; }
            val rootNode = fieldAccessExpr.findRootNode()
            val annotationDeclaration = rootNode.firstDescendantOfType(
                    AnnotationDeclaration::class.java
            ) { n -> n.nameAsString == simpleName}
                    ?: throw IllegalStateException("Ambiguous field access expression '$fieldAccessExpr'. No classes found in index with that name")

            return annotationDeclaration.firstDescendantOfType(
                    VariableDeclarator::class.java
            ) { v -> v.nameAsString == fieldName }
        }
        return null
    }

    companion object {
        fun isEligibleGetter(method: MethodInfo): Boolean {
            return method.methodDeclaration.parameters.isEmpty()
                    && isEligibleMethod(method)
        }
        fun isEligibleMethod(method: MethodInfo): Boolean {
            return method.methodDeclaration.isPublic
                    && !method.javadoc.blockTags.any { it.tagName == "hide" || it.tagName == "removed" }
        }

        fun isEligibleField(field: FieldDeclaration): Boolean {
            return field.isPublic
        }
    }
}

fun FieldDeclaration.describeType(variableName: String): String {
    val variable = this.variables.find { it.nameAsString == variableName }
        ?: throw java.lang.IllegalArgumentException("Field $this contains no variable '$variableName'")
    return variable.describeType()
}

fun VariableDeclarator.describeType(): String {
    return try {
        this.type.resolve().describe()
    } catch (e: Exception) {
        if (this.type.isClassOrInterfaceType) {
            val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(this.type.asClassOrInterfaceType())
            indexedClass.targetClassName.canonicalName
        } else throw e
    }
}

fun com.github.javaparser.ast.body.Parameter.describeType(): String {
    return try {
        when {
            this.isVarArgs -> this.resolve().describeType().replace("...", "[]")
            else -> this.resolve().describeType()
        }
    } catch (e: Exception) {
        if (this.type.isClassOrInterfaceType) {
            val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(this.type.asClassOrInterfaceType())
            indexedClass.targetClassName.canonicalName
        } else throw e
    }
}


interface ConstructorExpression {
    fun toEntity(): XdConstructorExpression
}
enum class ViewConstructorExpression : ConstructorExpression {
    ContextOnly,
    ContextAndAttrs,
    ContextAttrsAndDefStyleAttr,
    AbstractView;

    override fun toEntity(): XdConstructorExpression {
        return XdConstructorExpression.query(
                XdConstructorExpression::presentation eq this.name
        ).first()
    }
}
enum class LayoutParamsConstructorExpression : ConstructorExpression {
    WidthAndHeight,
    CopySource,
    Empty,
    AbstractLayoutParams;

    override fun toEntity(): XdConstructorExpression {
        return XdConstructorExpression.query(
                XdConstructorExpression::presentation eq this.name
        ).first()
    }
}
class XdConstructorExpression(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdConstructorExpression>() {
        val ContextOnly by enumField { this.presentation = ViewConstructorExpression.ContextOnly.name }
        val ContextAndAttrs by enumField { this.presentation = ViewConstructorExpression.ContextAndAttrs.name }
        val ContextAttrsAndDefStyleAttr by enumField { this.presentation = ViewConstructorExpression.ContextAttrsAndDefStyleAttr.name }
        val AbstractView by enumField { this.presentation = ViewConstructorExpression.AbstractView.name }
        val WidthAndHeight by enumField { this.presentation = LayoutParamsConstructorExpression.WidthAndHeight.name }
        val CopySource by enumField { this.presentation = LayoutParamsConstructorExpression.CopySource.name }
        val Empty by enumField { this.presentation = LayoutParamsConstructorExpression.Empty.name }
        val AbstractLayoutParams by enumField { this.presentation = LayoutParamsConstructorExpression.AbstractLayoutParams.name }

    }
    var presentation by xdRequiredStringProp()

    fun toEnum(transaction: Boolean = true): ConstructorExpression {
        val block= {
            var constructorExpression : ConstructorExpression? = null
            for (enumValue in enumValues<ViewConstructorExpression>()) {
                if (enumValue.name == this.presentation) {
                    constructorExpression = enumValue
                    break
                }
            }
            if (constructorExpression == null) {
                for (enumValue in enumValues<LayoutParamsConstructorExpression>()) {
                    if (enumValue.name == this.presentation) {
                        constructorExpression = enumValue
                        break
                    }
                }
            }
            constructorExpression
                    ?: throw IllegalStateException("ConstructorExpression with name '${this.presentation}' could not be resolved")
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}