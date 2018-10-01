package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class GridLayoutAttributes : ViewGroupAttributes() {
    var orientation: Int? = null

    var rowCount: Int? = null

    var columnCount: Int? = null

    var useDefaultMargins: Boolean? = null

    var alignmentMode: Int? = null

    var rowOrderPreserved: Boolean? = null

    var columnOrderPreserved: Boolean? = null
}
