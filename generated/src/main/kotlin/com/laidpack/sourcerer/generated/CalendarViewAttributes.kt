package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class CalendarViewAttributes : FrameLayoutAttributes(), IAttributes {
    var firstDayOfWeek: Int? = null

    var minDate: Int? = null

    var maxDate: Int? = null

    @field:ReferenceQualifier
    var weekDayTextAppearance: Int? = null

    @field:ReferenceQualifier
    var dateTextAppearance: Int? = null

    var showWeekNumber: Boolean? = null

    var shownWeekCount: Int? = null

    @field:ColorQualifier
    var selectedWeekBackgroundColor: MultiFormat =
            MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ColorQualifier
    var focusedMonthDateColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ColorQualifier
    var unfocusedMonthDateColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ColorQualifier
    var weekNumberColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ColorQualifier
    var weekSeparatorLineColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ReferenceQualifier
    var selectedDateVerticalBar: Int? = null
}
