package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class TabWidgetAttributes : LinearLayoutAttributes(), IAttributes {
    var tabStripEnabled: Boolean? = null

    @field:ReferenceQualifier
    var tabStripLeft: Int? = null

    @field:ReferenceQualifier
    var tabStripRight: Int? = null
}
