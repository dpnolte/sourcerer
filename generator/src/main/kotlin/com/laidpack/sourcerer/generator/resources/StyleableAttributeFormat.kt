package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query
import kotlinx.dnq.xdRequiredStringProp
import kotlin.reflect.KClass


enum class StyleableAttributeFormat(val value: kotlin.String, val requiresQualifier: kotlin.Boolean) {
    Boolean("boolean", false),
    Color("color", true),
    Reference("reference", true),
    Float("float", false),
    Dimension("dimension", true),
    Integer("integer", false),
    String("string", false),
    Flags("flag", true),
    Enum("enum", false),
    Fraction("fraction", false),
    Unspecified( "", false);

    fun toClass(): KClass<*> {
        return when (this) {
            Boolean -> kotlin.Boolean::class
            Color -> kotlin.Int::class
            Reference -> kotlin.Int::class
            Float -> kotlin.Float::class
            Dimension -> kotlin.Int::class
            Integer -> kotlin.Int::class
            String -> kotlin.String::class
            Enum -> kotlin.Int::class
            Flags -> kotlin.Int::class
            Fraction -> kotlin.Float::class
            Unspecified -> kotlin.Int::class // assuming unspecified is integer (TBC)
        }
    }


    fun toQualifierAnnotationClassName(): ClassName {
        return when (this) {
            Color -> colorQualifierClassName
            Reference -> referenceQualifierClassName
            Dimension -> dimensionQualifierClassName
            Flags -> flagsQualifierClassName
            else -> throw IllegalStateException("Format $this has no required qualifier")
        }
    }

    fun toEntity(): XdFormat {
        return XdFormat.query(
                XdFormat::presentation eq this.name
        ).first()
    }

    companion object {
        val colorQualifierClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "ColorQualifier")
        val referenceQualifierClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "ReferenceQualifier")
        val dimensionQualifierClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "DimensionQualifier")
        val flagsQualifierClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "FlagsQualifier")

        fun fromString(formatString: kotlin.String): List<StyleableAttributeFormat> {
            return formatString.split("|").map {
                getEnumValueByString(it)
            }
        }
        private fun getEnumValueByString(value: kotlin.String): StyleableAttributeFormat {
            return StyleableAttributeFormat.values().first { it.value == value }
        }

        fun fromClass(type: KClass<*>): StyleableAttributeFormat {
            return fromTypeName(type.asTypeName())
        }

        fun fromTypeName(typeName: TypeName): StyleableAttributeFormat {
            return when (typeName) {
                kotlin.Boolean::class.asTypeName() -> Boolean
                kotlin.Float::class.asTypeName() -> Float
                kotlin.Int::class.asTypeName() -> Integer
                kotlin.String::class.asTypeName() -> String
                else -> Unspecified
            }
        }
    }
}

class XdFormat(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdFormat>() {
        val Boolean by enumField { presentation = StyleableAttributeFormat.Boolean.name }
        val Color by enumField { presentation = StyleableAttributeFormat.Color.name }
        val Reference by enumField { presentation = StyleableAttributeFormat.Reference.name }
        val Float by enumField { presentation = StyleableAttributeFormat.Float.name }
        val Dimension by enumField { presentation = StyleableAttributeFormat.Dimension.name }
        val Integer by enumField { presentation = StyleableAttributeFormat.Integer.name }
        val String by enumField { presentation = StyleableAttributeFormat.String.name }
        val Enum by enumField { presentation = StyleableAttributeFormat.Enum.name }
        val Flags by enumField { presentation = StyleableAttributeFormat.Flags.name }
        val Fraction by enumField { presentation = StyleableAttributeFormat.Fraction.name }
        val Unspecified by enumField { presentation = StyleableAttributeFormat.Unspecified.name }
    }
    var presentation by xdRequiredStringProp()

    fun toEnum(transaction: Boolean = true): StyleableAttributeFormat {
        val block = {
            enumValueOf<StyleableAttributeFormat>(this.presentation)
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}