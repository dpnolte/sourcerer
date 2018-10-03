package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.GuidedActionEditText
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class GuidedActionEditTextFactory<TView : GuidedActionEditText, TAttributes : GuidedActionEditTextAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "guidedActionEditText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = GuidedActionEditText(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
