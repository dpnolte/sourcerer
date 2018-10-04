package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.picker.Picker
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class PickerFactory<TView : Picker, TAttributes : PickerAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "picker"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = Picker(context, null, 0)
}
