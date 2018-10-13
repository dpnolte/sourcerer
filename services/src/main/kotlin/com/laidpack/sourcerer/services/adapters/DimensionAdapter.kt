package com.laidpack.sourcerer.services.adapters

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class DimensionAdapter(
        private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics,
        private val applyDimension: (unit: Int, value: Float, metrics: DisplayMetrics) -> Float = { unit, value, metrics -> TypedValue.applyDimension(unit, value, metrics)}
) {

    @ToJson
    fun toJson(@DimensionQualifier dimensionValue: Int?): String {
        return dimensionValue.toString() // always return in pixels
    }

    @FromJson
    @DimensionQualifier
    fun fromJson(dimensionAsString: String): Int? {
        val matchResult = dimensionRegex.find(dimensionAsString)
        return if (matchResult == null) {
            // assume it is in pixels then..
            try {
                dimensionAsString.toInt()
            } catch (e: NumberFormatException) {
                null
            }
        } else {
            val value = matchResult.groupValues[1].toFloat()
            val unitName = matchResult.groupValues[3].toLowerCase()
            val unit = mapping[unitName] ?: throw NumberFormatException("Dimension value has invalid format: '$dimensionAsString'. Following unit indicators allowed: '${mapping.keys.joinToString(", ")}'")
            applyDimension(unit, value, displayMetrics).toInt()
        }
    }

    companion object {
        private val mapping = mapOf(
                "px" to TypedValue.COMPLEX_UNIT_PX,
                "dip" to TypedValue.COMPLEX_UNIT_DIP,
                "dp" to TypedValue.COMPLEX_UNIT_DIP,
                "sp" to TypedValue.COMPLEX_UNIT_SP,
                "pt" to TypedValue.COMPLEX_UNIT_PT,
                "in" to TypedValue.COMPLEX_UNIT_IN,
                "mm" to TypedValue.COMPLEX_UNIT_MM
        )
        val dimensionRegex = Regex("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$")

    }
}