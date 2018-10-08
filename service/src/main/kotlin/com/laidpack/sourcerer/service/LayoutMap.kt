package com.laidpack.sourcerer.service

import com.laidpack.sourcerer.service.api.IAttributes


class LayoutMap(val elements : Map<String, LayoutProperties>, val rootElementId: String)

data class LayoutProperties(
        val id: String,
        val elementName: String,
        val attributes: IAttributes,
        val children: List<String>
) {
    var parentId: String? = null
    var layoutAttributes: IAttributes? = null
    var layoutParamsElementName: String? = null
}