package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class LinearLayoutFactory<TView : LinearLayout, TAttributes : LinearLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "linearLayout"

    override fun createInstance(context: Context): View = LinearLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is LinearLayout) {
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

    companion object {
        init {
            InflaterComponent.addFactory(LinearLayoutFactory<LinearLayout, LinearLayoutAttributes>())
        }

        inline operator fun <reified TView : LinearLayout, reified TAttributes : LinearLayoutAttributes> invoke() = LinearLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
