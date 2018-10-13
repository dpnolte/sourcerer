package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.SpeechOrbView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class SpeechOrbViewFactory<TView : SpeechOrbView, TAttributes : SpeechOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SpeechOrbView(context)

    companion object {
        const val elementType: String = "speechOrbView"

        inline operator fun <reified TView : SpeechOrbView, reified TAttributes : SpeechOrbViewAttributes> invoke() = SpeechOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
