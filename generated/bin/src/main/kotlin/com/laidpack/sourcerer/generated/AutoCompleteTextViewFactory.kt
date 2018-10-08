package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AutoCompleteTextView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AutoCompleteTextViewFactory<TView : AutoCompleteTextView, TAttributes : AutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "autoCompleteTextView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = AutoCompleteTextView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.completionHint?.let {
                if (completionHint != it) {
                    completionHint = it
                }
            }
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
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(AutoCompleteTextViewFactory<AutoCompleteTextView, AutoCompleteTextViewAttributes>())
        }

        inline operator fun <reified TView : AutoCompleteTextView, reified TAttributes : AutoCompleteTextViewAttributes> invoke() = AutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
