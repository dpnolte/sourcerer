package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class LinearLayoutFactory<TView : LinearLayout, TAttributes : LinearLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "linearLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = LinearLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as LinearLayout
        view.init {
            attributes.orientation?.let {
                if (orientation != it) {
                    orientation = it
                }
            }
            attributes.baselineAligned?.let {
                if (isBaselineAligned != it) {
                    isBaselineAligned = it
                }
            }
            attributes.baselineAlignedChildIndex?.let {
                if (baselineAlignedChildIndex != it) {
                    baselineAlignedChildIndex = it
                }
            }
            attributes.measureWithLargestChild?.let {
                if (isMeasureWithLargestChildEnabled != it) {
                    isMeasureWithLargestChildEnabled = it
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.divider?.let {
                    val immutableDivider = ContextCompat.getDrawable(context, it) as Drawable
                    if (dividerDrawable != immutableDivider) {
                        dividerDrawable = immutableDivider
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 24) {
                attributes.gravity?.let {
                    if (gravity != it) {
                        gravity = it
                    }
                }
            }
        }
    }
}
