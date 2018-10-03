package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AppCompatAutoCompleteTextViewFactory<TView : AppCompatAutoCompleteTextView, TAttributes : AppCompatAutoCompleteTextViewAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "appCompatAutoCompleteTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = AppCompatAutoCompleteTextView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
