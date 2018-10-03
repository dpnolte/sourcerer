package com.laidpack.sourcerer.service

import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass


class LayoutMap(val elements : Map<String, LayoutElement>)

data class LayoutElement(
        val id: String,
        val elementName: String,
        val attributes: IAttributes,
        val children: List<String>
)