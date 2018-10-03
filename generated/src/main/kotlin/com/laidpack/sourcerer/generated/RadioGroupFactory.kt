package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class RadioGroupFactory<TView : RadioGroup, TAttributes : RadioGroupAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "radioGroup"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RadioGroup(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
