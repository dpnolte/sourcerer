package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class RadioButtonFactory<TView : RadioButton, TAttributes : RadioButtonAttributes> : ButtonFactory<TView, TAttributes>() {
    override val elementName: String = "radioButton"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RadioButton(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
