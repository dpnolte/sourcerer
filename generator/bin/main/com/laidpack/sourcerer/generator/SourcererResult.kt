package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.index.ClassCategory
import com.laidpack.sourcerer.generator.index.ConstructorExpression
import com.laidpack.sourcerer.generator.index.XdDeclaredType
import com.laidpack.sourcerer.generator.index.XdClassCategory
import com.laidpack.sourcerer.generator.target.*
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.toList

data class SourcererResult(
        val targetClassName: ClassName,
        val superClassName: ClassName?,
        val fallbackClassName: ClassName?,
        val defaultLayoutParamsClassName: ClassName?,
        val isAbstract: Boolean,
        val isFinal: Boolean,
        val constructorExpression: ConstructorExpression,
        val numberOfTypeVariables: Int,
        val classCategory: ClassCategory,
        val isViewGroup: Boolean,
        val minimumApiLevel: Int,
        val attributes: Map<String, Attribute>,
        val setters: Map<Int, Setter>,
        val codeBlocks: List<CodeBlock>,
        val xdDeclaredType: XdDeclaredType,
        val targetPackageName: String
) {
    fun toEntity(): XdSourcererResult {
        val xdResult = XdSourcererResult.new()
        xdResult.targetClass = this.xdDeclaredType
        xdResult.targetClassCanonicalName = this.targetClassName.canonicalName
        if (this.superClassName != null) xdResult.superClassCanonicalName = this.superClassName.canonicalName
        if (this.fallbackClassName != null) xdResult.fallbackClassCanonicalName = this.fallbackClassName.canonicalName
        if (this.defaultLayoutParamsClassName != null) xdResult.defaultLayoutParamsClassName = this.defaultLayoutParamsClassName.canonicalName
        xdResult.isAbstract = this.isAbstract
        xdResult.isFinal = this.isFinal
        xdResult.numberOfTypeVariables = this.numberOfTypeVariables
        xdResult.classCategory = this.classCategory.toEntity()
        xdResult.isViewGroup = this.isViewGroup
        xdResult.minimumApiLevel = this.minimumApiLevel
        xdResult.targetPackageName = this.targetPackageName

        val xdSetters = mutableMapOf<Int, XdSetter>()
        for (setter in this.setters) {
            val xdSetter = setter.value.toEntity(xdResult)
            xdSetters[setter.key] = xdSetter
            xdResult.setters.add(xdSetter)
        }
        val xdAttributes = mutableMapOf<String, XdAttribute>()
        for (attribute in this.attributes) {
            val xdAttribute = attribute.value.toEntity(xdSetters, xdResult)
            xdAttributes[attribute.key] = xdAttribute
            xdResult.attributes.add(xdAttribute)
        }
        for (codeBlock in this.codeBlocks) {
            xdResult.codeBlocks.add(
                    codeBlock.toEntity(xdResult, xdSetters, xdAttributes)
            )
        }
        return xdResult
    }
}

class XdSourcererResult(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdSourcererResult>()

    var targetClass : XdDeclaredType by xdParent(XdDeclaredType::sourcererResult)
    var targetClassCanonicalName by xdRequiredStringProp()
    var superClassCanonicalName by xdStringProp()
    var fallbackClassCanonicalName by xdStringProp()
    var defaultLayoutParamsClassName by xdStringProp()
    var isAbstract by xdBooleanProp()
    var isFinal by xdBooleanProp()
    var numberOfTypeVariables by xdRequiredIntProp()
    var classCategory by xdLink1(XdClassCategory)
    var isViewGroup by xdBooleanProp()
    var minimumApiLevel by xdRequiredIntProp()
    val attributes by xdChildren0_N(XdAttribute::sourcererResult)
    val setters by xdChildren0_N(XdSetter::sourcererResult)
    val codeBlocks by xdChildren0_N(XdCodeBlock::sourcererResult)
    var targetPackageName by xdRequiredStringProp()

    fun toSnapshot(transaction: Boolean = true): SourcererResult {
        val block = {
            val attributes = this.attributes.toList().associate {
                Pair(it.name, it.toSnapshot(false))
            }
            val setters = this.setters.toList().associate {
                val setter = it.toSnapshot(false)
                Pair(setter.hashCode(), setter)
            }
            SourcererResult(
                    this.targetClass.targetClassName,
                    if (this.superClassCanonicalName != null) ClassName.bestGuess(this.superClassCanonicalName as String) else null,
                    if (this.fallbackClassCanonicalName != null) ClassName.bestGuess(this.fallbackClassCanonicalName as String) else null,
                    if (this.defaultLayoutParamsClassName != null) ClassName.bestGuess(this.defaultLayoutParamsClassName as String) else null,
                    this.isAbstract,
                    this.isFinal,
                    this.targetClass.constructorExpression?.toEnum(false) as ConstructorExpression,
                    this.numberOfTypeVariables,
                    this.classCategory.toEnum(false),
                    this.isViewGroup,
                    this.minimumApiLevel,
                    attributes,
                    setters,
                    this.codeBlocks.toList().map {
                        it.toSnapshot(setters, attributes, transaction = false)
                    },
                    this.targetClass,
                    this.targetPackageName
            )
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}