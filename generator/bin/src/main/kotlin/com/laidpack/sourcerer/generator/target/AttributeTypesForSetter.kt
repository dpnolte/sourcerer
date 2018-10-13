package com.laidpack.sourcerer.generator.target

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.generators.MultiFormatGenerator
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.resources.XdFormat
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList

// setter parameter described type can come from 2 sources (TypedArray getter or parameter type from setter).
// That's why it is declared here not @ setter
data class AttributeTypesForSetter (
        val targetClassNames: List<String>,
        val defaultValue: String = "",
        val unassociatedToParameter: Boolean = false,
        val isField: Boolean = false
) {
    var hasEnumAsAttributeType = false
    var enumClassName : TypeName? = null
    val setterClassName : String = if (!unassociatedToParameter || isField) targetClassNames.first() else ""

    var mutableAttributeType : TypeName? = null
    val hasAttributeType : Boolean
        get() = mutableAttributeType != null
    val attributeType : TypeName
        get() = if(mutableAttributeType != null) mutableAttributeType
            else throw java.lang.NullPointerException("Attribute type is null. Setter type: $setterType, attribute formats: ${formats}'")

    val attributeCanonicalNames = mutableListOf<String>()

    var mutableSetterType : TypeName? = null
    val hasSetterType : Boolean
        get() = mutableSetterType != null
    val setterType : TypeName
        get() = mutableSetterType as TypeName

    var mutableSetterCompoundType : TypeName? = null
    val hasSetterCompoundType : Boolean
        get() = mutableSetterCompoundType != null
    val setterCompoundType : TypeName
        get() = mutableSetterCompoundType as TypeName

    var mutableResolvedSetterType : TypeName? = null
    val hasResolvedSetterType : Boolean
        get() = mutableResolvedSetterType != null
    val resolvedSetterType : TypeName
        get() = mutableResolvedSetterType as TypeName

    val formats = mutableSetOf<StyleableAttributeFormat>()

    val requiresTransformMethod : Boolean
        get() {
            return if (attributeType == MultiFormatGenerator.multiFormatClassName) {
                formats.any { format ->
                    format.toClass().asTypeName() != setterType
                }
            } else {
                val inputTypeName = when {
                    hasEnumAsAttributeType -> Int::class.asTypeName()
                    else -> attributeType
                }
                inputTypeName != setterType
            }
    }
    // index is parameter index
    val transformingCode = mutableListOf<TransformingCode>()
    fun toEntity(
            attribute: XdAttribute,
            setter: XdSetter
    ): XdAttributeTypesForSetter {
        val type = XdAttributeTypesForSetter.new()
        type.targetClassNames = this.targetClassNames.toSet()
        if (this.defaultValue.isNotBlank()) type.defaultValue = this.defaultValue
        type.unassociatedToParameter = this.unassociatedToParameter
        type.isField = this.isField
        type.hasEnumAsAttributeType = this.hasEnumAsAttributeType

        type.attributeCanonicalNames = this.attributeCanonicalNames.toSet()
        if (this.enumClassName != null) type.enumClassNameAsString = (this.enumClassName as ClassName).canonicalName
        if (this.hasAttributeType) type.attributeTypeAsString = this.attributeType.toString()
        if (this.hasSetterType) type.setterTypeAsString = this.setterType.toString()
        if (this.hasSetterCompoundType) type.setterCompoundTypeAsString = this.setterCompoundType.toString()
        if (this.hasResolvedSetterType) type.resolvedSetterTypeAsString = this.resolvedSetterType.toString()
        type.formats.addAll(this.formats.map { it.toEntity() })
        type.attribute = attribute
        type.setter = setter

        return type
    }
}

class XdAttributeTypesForSetter(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdAttributeTypesForSetter>()

    var targetClassNames by xdSetProp<XdAttributeTypesForSetter, String>()
    var defaultValue by xdStringProp()
    var unassociatedToParameter by xdBooleanProp()
    var isField: Boolean by xdBooleanProp()
    var hasEnumAsAttributeType by xdBooleanProp()
    var enumClassNameAsString by xdStringProp()
    var attributeTypeAsString by xdStringProp()
    var attributeCanonicalNames by xdSetProp<XdAttributeTypesForSetter, String>()
    var setterTypeAsString by xdStringProp()
    var setterCompoundTypeAsString by xdStringProp()
    var resolvedSetterTypeAsString by xdStringProp()
    val formats by xdLink0_N(XdFormat)
    var attribute : XdAttribute by xdParent(XdAttribute::typesPerSetter)
    var setter : XdSetter by xdLink1(XdSetter)

    // index is parameter index
    val transformingCode by xdLink0_N(XdTransformingCode)

    fun toSnapshot(transaction: Boolean = true): AttributeTypesForSetter {
        val block = {
            val types = AttributeTypesForSetter(
                    this.targetClassNames.toList(),
                    this.defaultValue ?: "",
                    this.unassociatedToParameter,
                    this.isField
            )
            types.hasEnumAsAttributeType = this.hasEnumAsAttributeType
            this.enumClassNameAsString?.let {s ->
                types.enumClassName = ClassName.bestGuess(s)
            }
            this.attributeTypeAsString?.let {s ->
                types.mutableAttributeType = ClassName.bestGuess(s)
            }
            types.attributeCanonicalNames.addAll(this.attributeCanonicalNames)
            this.setterTypeAsString?.let {s ->
                types.mutableSetterType = ClassName.bestGuess(s)
            }
            this.setterCompoundTypeAsString?.let{ s ->
                types.mutableSetterCompoundType = ClassName.bestGuess(s)
            }
            this.resolvedSetterTypeAsString?.let {s ->
                types.mutableResolvedSetterType = ClassName.bestGuess(s)
            }
            types.formats.addAll(this.formats.toList().map { xdFormat ->
                xdFormat.toEnum(false)
            })
            types
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}

data class TransformingCode(
        val fromClassName: String,
        val toClassNames: String,
        val parameterIndex: Int,
        val conditionLiteral: String,
        val operator: String,
        val operatorType: String,
        val valueLiteral: String
)

class XdTransformingCode(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdTransformingCode>()

    val fromClassName by xdRequiredStringProp()
    val toClassNames by xdRequiredStringProp()
    val parameterIndex by xdRequiredIntProp()
    val conditionLiteral by xdRequiredStringProp()
    val operator by xdRequiredStringProp()
    val operatorType by xdRequiredStringProp()
    val valueLiteral by xdRequiredStringProp()

    fun toSnapshot(): TransformingCode {
        return Store.transactional {
            TransformingCode(
                    this.fromClassName,
                    this.toClassNames,
                    this.parameterIndex,
                    this.conditionLiteral,
                    this.operator,
                    this.operatorType,
                    this.valueLiteral
            )
        }
    }
}
