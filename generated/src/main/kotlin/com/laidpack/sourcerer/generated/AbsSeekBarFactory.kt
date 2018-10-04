package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSeekBar
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AbsSeekBarFactory<TView : AbsSeekBar, TAttributes : AbsSeekBarAttributes> : ProgressBarFactory<TView, TAttributes>() {
    override val elementName: String = "absSeekBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AbsSeekBar is abstract and cannot be instantiated")
    }
}
