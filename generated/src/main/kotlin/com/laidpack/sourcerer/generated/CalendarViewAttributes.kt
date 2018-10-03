package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.MultiFormatQualifier
import com.laidpack.sourcerer.service.api.ReferenceQualifier
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

    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var selectedWeekBackgroundColor: MultiFormat =
            MultiFormat(setOf(Format.Color, Format.Reference))

    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var focusedMonthDateColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var unfocusedMonthDateColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var weekNumberColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var weekSeparatorLineColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:ReferenceQualifier
    var selectedDateVerticalBar: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(CalendarViewAttributes::class, CalendarViewAttributesJsonAdapter::class, "calendarView")
        }
    }
}
