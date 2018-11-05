package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.CalendarView
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = CalendarViewFactory.elementType,
        attributesClazz = CalendarViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class CalendarViewFactory<TView : CalendarView, TAttributes : CalendarViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CalendarView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is CalendarView) {
            view.apply {
                attributes.firstDayOfWeek?.let {
                    if (firstDayOfWeek != it) {
                        firstDayOfWeek = it
                    }
                }
                attributes.minDate?.let {
                    val localMinDate = it.toLong()
                    if (minDate != localMinDate) {
                        minDate = localMinDate
                    }
                }
                attributes.maxDate?.let {
                    val localMaxDate = it.toLong()
                    if (maxDate != localMaxDate) {
                        maxDate = localMaxDate
                    }
                }
                attributes.showWeekNumber?.let {
                    if (showWeekNumber != it) {
                        showWeekNumber = it
                    }
                }
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
                attributes.selectedWeekBackgroundColor?.let {
                    if (selectedWeekBackgroundColor != it) {
                        selectedWeekBackgroundColor = it
                    }
                }
                attributes.focusedMonthDateColor?.let {
                    if (focusedMonthDateColor != it) {
                        focusedMonthDateColor = it
                    }
                }
                attributes.unfocusedMonthDateColor?.let {
                    if (unfocusedMonthDateColor != it) {
                        unfocusedMonthDateColor = it
                    }
                }
                attributes.weekNumberColor?.let {
                    if (weekNumberColor != it) {
                        weekNumberColor = it
                    }
                }
                attributes.weekSeparatorLineColor?.let {
                    if (weekSeparatorLineColor != it) {
                        weekSeparatorLineColor = it
                    }
                }
                attributes.selectedDateVerticalBar?.let {
                    val localSelectedDateVerticalBar = ContextCompat.getDrawable(context, it) as Drawable
                    if (selectedDateVerticalBar != localSelectedDateVerticalBar) {
                        selectedDateVerticalBar = localSelectedDateVerticalBar
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "calendarView"

        inline operator fun <reified TView : CalendarView, reified TAttributes : CalendarViewAttributes> invoke() = CalendarViewFactory(TView::class.java, TAttributes::class.java)
    }
}
