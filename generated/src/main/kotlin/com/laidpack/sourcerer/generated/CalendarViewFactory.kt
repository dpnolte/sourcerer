package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class CalendarViewFactory<TView : CalendarView, TAttributes : CalendarViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "calendarView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CalendarView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as CalendarView
        view.init {
            attributes.firstDayOfWeek?.let {
                if (firstDayOfWeek != it) {
                    firstDayOfWeek = it
                }
            }
            attributes.minDate?.let {
                val immutableMinDate = it.toLong()
                if (minDate != immutableMinDate) {
                    minDate = immutableMinDate
                }
            }
            attributes.maxDate?.let {
                val immutableMaxDate = it.toLong()
                if (maxDate != immutableMaxDate) {
                    maxDate = immutableMaxDate
                }
            }
            attributes.showWeekNumber?.let {
                if (showWeekNumber != it) {
                    showWeekNumber = it
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.weekDayTextAppearance?.let {
                    if (weekDayTextAppearance != it) {
                        weekDayTextAppearance = it
                    }
                }
                attributes.dateTextAppearance?.let {
                    if (dateTextAppearance != it) {
                        dateTextAppearance = it
                    }
                }
                attributes.shownWeekCount?.let {
                    if (shownWeekCount != it) {
                        shownWeekCount = it
                    }
                }
                if (attributes.selectedWeekBackgroundColor.hasColor) {
                    val immutableSelectedWeekBackgroundColor = attributes.selectedWeekBackgroundColor.color
                    if (selectedWeekBackgroundColor != immutableSelectedWeekBackgroundColor) {
                        selectedWeekBackgroundColor = immutableSelectedWeekBackgroundColor
                    }
                }
                if (attributes.focusedMonthDateColor.hasColor) {
                    val immutableFocusedMonthDateColor = attributes.focusedMonthDateColor.color
                    if (focusedMonthDateColor != immutableFocusedMonthDateColor) {
                        focusedMonthDateColor = immutableFocusedMonthDateColor
                    }
                }
                if (attributes.unfocusedMonthDateColor.hasColor) {
                    val immutableUnfocusedMonthDateColor = attributes.unfocusedMonthDateColor.color
                    if (unfocusedMonthDateColor != immutableUnfocusedMonthDateColor) {
                        unfocusedMonthDateColor = immutableUnfocusedMonthDateColor
                    }
                }
                if (attributes.weekNumberColor.hasColor) {
                    val immutableWeekNumberColor = attributes.weekNumberColor.color
                    if (weekNumberColor != immutableWeekNumberColor) {
                        weekNumberColor = immutableWeekNumberColor
                    }
                }
                if (attributes.weekSeparatorLineColor.hasColor) {
                    val immutableWeekSeparatorLineColor = attributes.weekSeparatorLineColor.color
                    if (weekSeparatorLineColor != immutableWeekSeparatorLineColor) {
                        weekSeparatorLineColor = immutableWeekSeparatorLineColor
                    }
                }
                attributes.selectedDateVerticalBar?.let {
                    val immutableSelectedDateVerticalBar = ContextCompat.getDrawable(context, it) as Drawable
                    if (selectedDateVerticalBar != immutableSelectedDateVerticalBar) {
                        selectedDateVerticalBar = immutableSelectedDateVerticalBar
                    }
                }
            }
        }
    }
}
