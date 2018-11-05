package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.SpeechOrbView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SpeechOrbViewFactory.elementType,
        attributesClazz = SpeechOrbViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class SpeechOrbViewFactory<TView : SpeechOrbView, TAttributes : SpeechOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : SearchOrbViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SpeechOrbView(context)

    companion object {
        const val elementType: String = "speechOrbView"

        inline operator fun <reified TView : SpeechOrbView, reified TAttributes : SpeechOrbViewAttributes> invoke() = SpeechOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
