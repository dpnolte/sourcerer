package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class LinearLayoutCompatFactory<TView : LinearLayoutCompat, TAttributes : LinearLayoutCompatAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = LinearLayoutCompat(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is LinearLayoutCompat) {
            view.apply {
                attributes.measureWithLargestChild?.let {
                    if (isEnabled != it) {
                        isMeasureWithLargestChildEnabled = it
                    }
                }
                attributes.divider?.let {
                    val localDivider = ContextCompat.getDrawable(context, it) as Drawable
                    dividerDrawable = localDivider
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
            }
        }
    }

    companion object {
        const val elementType: String = "linearLayoutCompat"

        inline operator fun <reified TView : LinearLayoutCompat, reified TAttributes : LinearLayoutCompatAttributes> invoke() = LinearLayoutCompatFactory(TView::class.java, TAttributes::class.java)
    }
}
