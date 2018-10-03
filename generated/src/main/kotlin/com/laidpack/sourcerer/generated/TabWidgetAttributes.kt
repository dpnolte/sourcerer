package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class TabWidgetAttributes : LinearLayoutAttributes(), IAttributes {
    @field:ReferenceQualifier
    var tabStripLeft: Int? = null

    @field:ReferenceQualifier
    var tabStripRight: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(TabWidgetAttributes::class, TabWidgetAttributesJsonAdapter::class, "tabWidget")
        }
    }
}
