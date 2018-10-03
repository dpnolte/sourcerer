package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class TextSwitcherFactory<TView : TextSwitcher, TAttributes : TextSwitcherAttributes> : ViewSwitcherFactory<TView, TAttributes>() {
    override val elementName: String = "textSwitcher"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TextSwitcher(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
