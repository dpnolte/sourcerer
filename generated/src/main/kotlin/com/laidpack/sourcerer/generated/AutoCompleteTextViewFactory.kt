package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AutoCompleteTextView
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AutoCompleteTextViewFactory.elementType,
        attributesClazz = AutoCompleteTextViewAttributes::class
)
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
                attributes.completionHint?.let {
                    if (hint != it) {
                        completionHint = it
                    }
                }
                attributes.completionThreshold?.let {
                    if (threshold != it) {
                        threshold = it
                    }
                }
                attributes.dropDownAnchor?.let {
                    if (id != it) {
                        dropDownAnchor = it
                    }
                }
                if (attributes.dropDownWidth.hasDimension || attributes.dropDownWidth.hasEnum) {
                    val localDropDownWidth = when {
                        attributes.dropDownWidth.hasDimension -> attributes.dropDownWidth.dimension
                        else -> attributes.dropDownWidth.enum
                    }
                    if (width != localDropDownWidth) {
                        dropDownWidth = localDropDownWidth
                    }
                }
                if (attributes.dropDownHeight.hasDimension || attributes.dropDownHeight.hasEnum) {
                    val localDropDownHeight = when {
                        attributes.dropDownHeight.hasDimension -> attributes.dropDownHeight.dimension
                        else -> attributes.dropDownHeight.enum
                    }
                    if (height != localDropDownHeight) {
                        dropDownHeight = localDropDownHeight
                    }
                }
                attributes.dropDownVerticalOffset?.let {
                    if (dropDownVerticalOffset != it) {
                        dropDownVerticalOffset = it
                    }
                }
                attributes.dropDownHorizontalOffset?.let {
                    if (dropDownHorizontalOffset != it) {
                        dropDownHorizontalOffset = it
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
