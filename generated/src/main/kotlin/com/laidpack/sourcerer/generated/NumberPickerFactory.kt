package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class NumberPickerFactory<TView : NumberPicker, TAttributes : NumberPickerAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "numberPicker"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = NumberPicker(context)
}
