package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class AutoCompleteTextViewFactory<TView : AutoCompleteTextView, TAttributes : AutoCompleteTextViewAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "autoCompleteTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = AutoCompleteTextView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as AutoCompleteTextView
        view.init {
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
            attributes.dropDownWidth?.let {
                if (dropDownWidth != it.value) {
                    dropDownWidth = it.value
                }
            }
            attributes.dropDownHeight?.let {
                if (dropDownHeight != it.value) {
                    dropDownHeight = it.value
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
