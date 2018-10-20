package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.AutoCompleteTextView
import java.lang.Class
import kotlin.String

open class AutoCompleteTextViewFactory<TView : AutoCompleteTextView, TAttributes : AutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AutoCompleteTextView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AutoCompleteTextView) {
            view.apply {
                attributes.completionThreshold?.let {
                    if (threshold != it) {
                        threshold = it
                    }
                }
                attributes.dropDownAnchor?.let {
                    if (dropDownAnchor != it) {
                        dropDownAnchor = it
                    }
                }
                if (attributes.dropDownWidth.hasDimension || attributes.dropDownWidth.hasEnum) {
                    val localDropDownWidth = when {
                        attributes.dropDownWidth.hasDimension -> attributes.dropDownWidth.dimension
                        else -> attributes.dropDownWidth.enum
                    }
                    if (dropDownWidth != localDropDownWidth) {
                        dropDownWidth = localDropDownWidth
                    }
                }
                if (attributes.dropDownHeight.hasDimension || attributes.dropDownHeight.hasEnum) {
                    val localDropDownHeight = when {
                        attributes.dropDownHeight.hasDimension -> attributes.dropDownHeight.dimension
                        else -> attributes.dropDownHeight.enum
                    }
                    if (dropDownHeight != localDropDownHeight) {
                        dropDownHeight = localDropDownHeight
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.completionHint?.let {
                        if (completionHint != it) {
                            completionHint = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "autoCompleteTextView"

        inline operator fun <reified TView : AutoCompleteTextView, reified TAttributes : AutoCompleteTextViewAttributes> invoke() = AutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
