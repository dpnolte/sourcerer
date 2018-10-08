package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.SpeechOrbView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SpeechOrbViewFactory<TView : SpeechOrbView, TAttributes : SpeechOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "speechOrbView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SpeechOrbView(context)

    companion object {
        init {
            InflaterComponent.addFactory(SpeechOrbViewFactory<SpeechOrbView, SpeechOrbViewAttributes>())
        }

        inline operator fun <reified TView : SpeechOrbView, reified TAttributes : SpeechOrbViewAttributes> invoke() = SpeechOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
