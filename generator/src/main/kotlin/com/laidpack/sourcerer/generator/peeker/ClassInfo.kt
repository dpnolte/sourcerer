package com.laidpack.sourcerer.generator.peeker

import android.view.ViewGroup
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.resources.Widget
import com.laidpack.sourcerer.generator.target.XdResolvedStatus
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEntity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.XdNaturalEntityType
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
        } catch (e: UnsolvedSymbolException) {
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
        val classDeclaration: ClassOrInterfaceDeclaration,
        val superClassNames: List<ClassName>,
        val constructorOrMethodsWithAttributeSetParamKeys : Map<String, List<Int>>,
        val methodsWithAttributeBlockTagKeys : Map<String, List<Int>>,
        val constructorDeclarations: Map<String, List<ConstructorInfo>>,
        val methodDeclarations: Map<String, List<MethodInfo>>,
        val fieldDeclarations: Map<String, FieldDeclaration>,
        val constructorExpression: ConstructorExpression,
        val indexedClass: IndexedClass
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
                if (isEligibleMethod(methodInfo) && !methodInfo.resolvedMethodDeclaration.returnType.isVoid) {
                    getters.add(methodInfo)
                }
            }
        }
        getters
    }

    fun getPotentialGettersForField(field: FieldDeclaration): List<MethodInfo> {
        val fieldTypeAsString = field.describeType()
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

    fun getSetterInfo(setter: Setter): MethodInfo {
        val methodInfos = methodDeclarations[setter.name] ?: throw IllegalArgumentException("No method info for setter ${setter.name}, no match based on name")
        if (methodInfos.size == 1) return methodInfos.first()

        val hashCode = setter.hashCode()
        return methodInfos.first {
            Setter.getHashCodeFromMethodInfo(it) == hashCode
        }
    }

    fun setResolvedAttributes(attrs : Map<String, Attribute>) {
        mutableResolvedAttributes = attrs
        hasResolvedAttributes = true
    }

    companion object {
        val layoutParamsClassType = ViewGroup.LayoutParams::class
        fun isEligibleMethod(method: MethodInfo): Boolean {
            return method.methodDeclaration.parameters.isEmpty()
                    && method.methodDeclaration.isPublic
                    && !method.javadoc.blockTags.any { it.tagName == "hide" || it.tagName == "removed" }
        }

        fun isEligibleField(field: FieldDeclaration): Boolean {
            return field.isPublic
        }
    }
}

fun FieldDeclaration.describeType(): String {
    val variable = this.variables.first()
    return try {
        variable.type.resolve().describe()
    } catch (e: UnsolvedSymbolException) {
        if (variable.type.isClassOrInterfaceType) {
            val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(variable.type.asClassOrInterfaceType())
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
    } catch (e: UnsolvedSymbolException) {
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