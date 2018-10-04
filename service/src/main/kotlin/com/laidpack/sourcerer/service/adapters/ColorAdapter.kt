package com.laidpack.sourcerer.service.adapters

import android.graphics.Color
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import java.util.HashMap

class ColorAdapter(
        private val parseColor: (String) -> Int = {colorString -> Color.parseColor(colorString)}
) {
    @ToJson
    fun toJson(@ColorQualifier rgb: Int): String {
        return String.format("#%06x", rgb)
    }
    @FromJson
    @ColorQualifier
    fun fromJson(colorString: String): Int {
        var input = colorString
        if(colorString.startsWith('#') && colorString.length == 4) {
            input = "#" + colorString.substring(1, 2).repeat(2) + colorString.substring(2, 3).repeat(2) + colorString.substring(3, 4).repeat(2)
        }

        if (!isValidHexColor(input) && !isValidColorLabel(input)) {
            throw JsonDataException("Invalid color format '$colorString'. It must be hexadecimal (#333, #123456 or #00123456) or label like 'black'")
        }
        return parseColor(input)
    }

    private fun isValidHexColor(colorString: String): Boolean {
        return colorString[0] == '#'
                &&  (colorString.length == 7 || colorString.length == 9)
    }
    private fun isValidColorLabel(colorString: String): Boolean {
        return colorLabels.contains(colorString)
    }
    
    companion object {
        private val colorLabels = setOf(
            "black",
            "darkgray",
            "gray",
            "lightgray",
            "white",
            "red",
            "green",
            "blue",
            "yellow",
            "cyan",
            "magenta",
            "aqua",
            "fuchsia",
            "darkgrey",
            "grey",
            "lightgrey",
            "lime",
            "maroon",
            "navy",
            "olive",
            "purple",
            "silver",
            "teal"
        )
    }
}