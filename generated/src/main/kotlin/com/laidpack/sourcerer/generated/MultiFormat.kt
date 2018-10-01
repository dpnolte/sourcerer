package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.Set

@JsonClass(generateAdapter = true)
@TypeScript
class MultiFormat(private val allowedFormats: Set<Format>) {
    val mutableBoolean: Boolean? = null

    val hasBoolean: Boolean
        get() = mutableBoolean != null

    val boolean: Boolean
        get() {
            if (!allowedFormats.contains(Format.Boolean)) throw IllegalStateException("Format 'Boolean' is not allowed as value")
            return mutableBoolean ?: throw IllegalStateException("Boolean is null")
        }

    val mutableColor: Int? = null

    val hasColor: Boolean
        get() = mutableColor != null

    val color: Int
        get() {
            if (!allowedFormats.contains(Format.Color)) throw IllegalStateException("Format 'Color' is not allowed as value")
            return mutableColor ?: throw IllegalStateException("Color is null")
        }

    val mutableReference: Int? = null

    val hasReference: Boolean
        get() = mutableReference != null

    val reference: Int
        get() {
            if (!allowedFormats.contains(Format.Reference)) throw IllegalStateException("Format 'Reference' is not allowed as value")
            return mutableReference ?: throw IllegalStateException("Reference is null")
        }

    val mutableFloat: Float? = null

    val hasFloat: Boolean
        get() = mutableFloat != null

    val float: Float
        get() {
            if (!allowedFormats.contains(Format.Float)) throw IllegalStateException("Format 'Float' is not allowed as value")
            return mutableFloat ?: throw IllegalStateException("Float is null")
        }

    val mutableDimension: Int? = null

    val hasDimension: Boolean
        get() = mutableDimension != null

    val dimension: Int
        get() {
            if (!allowedFormats.contains(Format.Dimension)) throw IllegalStateException("Format 'Dimension' is not allowed as value")
            return mutableDimension ?: throw IllegalStateException("Dimension is null")
        }

    val mutableInteger: Int? = null

    val hasInteger: Boolean
        get() = mutableInteger != null

    val integer: Int
        get() {
            if (!allowedFormats.contains(Format.Integer)) throw IllegalStateException("Format 'Integer' is not allowed as value")
            return mutableInteger ?: throw IllegalStateException("Integer is null")
        }

    val mutableString: String? = null

    val hasString: Boolean
        get() = mutableString != null

    val string: String
        get() {
            if (!allowedFormats.contains(Format.String)) throw IllegalStateException("Format 'String' is not allowed as value")
            return mutableString ?: throw IllegalStateException("String is null")
        }

    val mutableEnum: Int? = null

    val hasEnum: Boolean
        get() = mutableEnum != null

    val enum: Int
        get() {
            if (!allowedFormats.contains(Format.Enum)) throw IllegalStateException("Format 'Enum' is not allowed as value")
            return mutableEnum ?: throw IllegalStateException("Enum is null")
        }

    val mutableFraction: Float? = null

    val hasFraction: Boolean
        get() = mutableFraction != null

    val fraction: Float
        get() {
            if (!allowedFormats.contains(Format.Fraction)) throw IllegalStateException("Format 'Fraction' is not allowed as value")
            return mutableFraction ?: throw IllegalStateException("Fraction is null")
        }

    val mutableUnspecified: Int? = null

    val hasUnspecified: Boolean
        get() = mutableUnspecified != null

    val unspecified: Int
        get() {
            if (!allowedFormats.contains(Format.Unspecified)) throw IllegalStateException("Format 'Unspecified' is not allowed as value")
            return mutableUnspecified ?: throw IllegalStateException("Unspecified is null")
        }
}
