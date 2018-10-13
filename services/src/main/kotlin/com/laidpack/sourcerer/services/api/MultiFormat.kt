// do not edit, auto-generated
package com.laidpack.sourcerer.services.api

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.util.SortedMap
import kotlin.Any
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.collections.Set
import kotlin.jvm.Transient

@TypeScript
class MultiFormat(@Transient private val allowedFormats: Set<Format> = setOf()) {
    var mutableBoolean: Boolean? = null

    val hasBoolean: Boolean
        get() = mutableBoolean != null

    val boolean: Boolean
        get() {
            if (!allowedFormats.contains(Format.Boolean)) throw IllegalStateException("Format 'Boolean' is not allowed as value")
            return mutableBoolean ?: throw IllegalStateException("Boolean is null")
        }

    var mutableColor: Int? = null

    val hasColor: Boolean
        get() = mutableColor != null

    val color: Int
        get() {
            if (!allowedFormats.contains(Format.Color)) throw IllegalStateException("Format 'Color' is not allowed as value")
            return mutableColor ?: throw IllegalStateException("Color is null")
        }

    var mutableReference: Int? = null

    val hasReference: Boolean
        get() = mutableReference != null

    val reference: Int
        get() {
            if (!allowedFormats.contains(Format.Reference)) throw IllegalStateException("Format 'Reference' is not allowed as value")
            return mutableReference ?: throw IllegalStateException("Reference is null")
        }

    var mutableFloat: Float? = null

    val hasFloat: Boolean
        get() = mutableFloat != null

    val float: Float
        get() {
            if (!allowedFormats.contains(Format.Float)) throw IllegalStateException("Format 'Float' is not allowed as value")
            return mutableFloat ?: throw IllegalStateException("Float is null")
        }

    var mutableDimension: Int? = null

    val hasDimension: Boolean
        get() = mutableDimension != null

    val dimension: Int
        get() {
            if (!allowedFormats.contains(Format.Dimension)) throw IllegalStateException("Format 'Dimension' is not allowed as value")
            return mutableDimension ?: throw IllegalStateException("Dimension is null")
        }

    var mutableInteger: Int? = null

    val hasInteger: Boolean
        get() = mutableInteger != null

    val integer: Int
        get() {
            if (!allowedFormats.contains(Format.Integer)) throw IllegalStateException("Format 'Integer' is not allowed as value")
            return mutableInteger ?: throw IllegalStateException("Integer is null")
        }

    var mutableString: String? = null

    val hasString: Boolean
        get() = mutableString != null

    val string: String
        get() {
            if (!allowedFormats.contains(Format.String)) throw IllegalStateException("Format 'String' is not allowed as value")
            return mutableString ?: throw IllegalStateException("String is null")
        }

    var mutableEnum: Int? = null

    val hasEnum: Boolean
        get() = mutableEnum != null

    val enum: Int
        get() {
            if (!allowedFormats.contains(Format.Enum)) throw IllegalStateException("Format 'Enum' is not allowed as value")
            return mutableEnum ?: throw IllegalStateException("Enum is null")
        }

    var mutableFraction: Float? = null

    val hasFraction: Boolean
        get() = mutableFraction != null

    val fraction: Float
        get() {
            if (!allowedFormats.contains(Format.Fraction)) throw IllegalStateException("Format 'Fraction' is not allowed as value")
            return mutableFraction ?: throw IllegalStateException("Fraction is null")
        }

    var mutableUnspecified: Int? = null

    val hasUnspecified: Boolean
        get() = mutableUnspecified != null

    val unspecified: Int
        get() {
            if (!allowedFormats.contains(Format.Unspecified)) throw IllegalStateException("Format 'Unspecified' is not allowed as value")
            return mutableUnspecified ?: throw IllegalStateException("Unspecified is null")
        }

    val hasAnyValue: Boolean
        get() = mutableBoolean != null
        			|| mutableColor != null
        			|| mutableReference != null
        			|| mutableFloat != null
        			|| mutableDimension != null
        			|| mutableInteger != null
        			|| mutableString != null
        			|| mutableEnum != null
        			|| mutableFraction != null
        			|| mutableUnspecified != null

    fun setValue(format: Format, value: Any) {
        when (format) {
            Format.Boolean -> mutableBoolean = value as Boolean
            Format.Color -> mutableColor = value as Int
            Format.Reference -> mutableReference = value as Int
            Format.Float -> mutableFloat = value as Float
            Format.Dimension -> mutableDimension = value as Int
            Format.Integer -> mutableInteger = value as Int
            Format.String -> mutableString = value as String
            Format.Enum -> mutableEnum = value as Int
            Format.Fraction -> mutableFraction = value as Float
            Format.Unspecified -> mutableUnspecified = value as Int
        }
    }

    companion object {
        fun getAdaptersMap(moshi: Moshi): SortedMap<Format, () -> JsonAdapter<*>> = sortedMapOf(
        			Format.Reference to {moshi.adapter<Int?>(Int::class.javaObjectType, ReferenceQualifier::class.java) as JsonAdapter<*>},
        			Format.Color to {moshi.adapter<Int?>(Int::class.javaObjectType, ColorQualifier::class.java) as JsonAdapter<*>},
        			Format.Dimension to {moshi.adapter<Int?>(Int::class.javaObjectType, DimensionQualifier::class.java) as JsonAdapter<*>},
        			Format.Boolean to {moshi.adapter<Boolean?>(Boolean::class.javaObjectType) as JsonAdapter<*>},
        			Format.Float to {moshi.adapter<Float?>(Float::class.javaObjectType) as JsonAdapter<*>},
        			Format.Integer to {moshi.adapter<Int?>(Int::class.javaObjectType) as JsonAdapter<*>},
        			Format.String to {moshi.adapter<String?>(String::class.javaObjectType) as JsonAdapter<*>},
        			Format.Enum to {moshi.adapter<Int?>(Int::class.javaObjectType) as JsonAdapter<*>},
        			Format.Fraction to {moshi.adapter<Float?>(Float::class.javaObjectType) as JsonAdapter<*>},
        			Format.Unspecified to {moshi.adapter<Int?>(Int::class.javaObjectType) as JsonAdapter<*>}
        		)
    }
}
