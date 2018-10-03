package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class DatePickerAttributes : FrameLayoutAttributes(), IAttributes {
    var firstDayOfWeek: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(DatePickerAttributes::class, DatePickerAttributesJsonAdapter::class, "datePicker")
        }
    }
}
