package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import java.lang.Class
import kotlin.String

open class LinearLayoutFactory<TView : LinearLayout, TAttributes : LinearLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = LinearLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is LinearLayout) {
            view.apply {
                attributes.orientation?.let {
                    if (orientation != it.value) {
                        orientation = it.value
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
                attributes.showDividers?.let {
                    val localShowDividers = it.value
                    if (showDividers != localShowDividers) {
                        showDividers = localShowDividers
                    }
                }
                attributes.dividerPadding?.let {
                    if (dividerPadding != it) {
                        dividerPadding = it
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.divider?.let {
                        val localDivider = ContextCompat.getDrawable(context, it) as Drawable
                        if (dividerDrawable != localDivider) {
                            dividerDrawable = localDivider
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    attributes.gravity?.let {
                        val localGravity = it.value
                        if (gravity != localGravity) {
                            gravity = localGravity
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "linearLayout"

        inline operator fun <reified TView : LinearLayout, reified TAttributes : LinearLayoutAttributes> invoke() = LinearLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
