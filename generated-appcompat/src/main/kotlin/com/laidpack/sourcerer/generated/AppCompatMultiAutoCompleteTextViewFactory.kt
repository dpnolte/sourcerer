package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AppCompatMultiAutoCompleteTextViewFactory<TView : AppCompatMultiAutoCompleteTextView, TAttributes : AppCompatMultiAutoCompleteTextViewAttributes> : MultiAutoCompleteTextViewFactory<TView, TAttributes>() {
    override val elementName: String = "appCompatMultiAutoCompleteTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = AppCompatMultiAutoCompleteTextView(context)
}
