package com.laidpack.sourcerer.services.layout

import com.laidpack.sourcerer.services.api.IAttributes

data class LayoutProperties(
        val id: String,
        val type: String,
        val attributes: IAttributes,
        val children: Set<String>
) {
    val hashedId: Int = id.hashCode()
    var parentId: String? = null
    var layoutAttributes: IAttributes? = null
    var layoutParamsType: String? = null
}