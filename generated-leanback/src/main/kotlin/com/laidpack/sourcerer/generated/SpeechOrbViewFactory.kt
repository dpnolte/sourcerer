package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.SpeechOrbView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class SpeechOrbViewFactory<TView : SpeechOrbView, TAttributes : SpeechOrbViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "speechOrbView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SpeechOrbView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
