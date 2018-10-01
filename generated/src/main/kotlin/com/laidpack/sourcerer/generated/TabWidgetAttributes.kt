package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class TabWidgetAttributes : LinearLayoutAttributes() {
    @field:ReferenceQualifier
    var tabStripLeft: Int? = null

    @field:ReferenceQualifier
    var tabStripRight: Int? = null
}
