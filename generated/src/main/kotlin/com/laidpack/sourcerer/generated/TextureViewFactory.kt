package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.TextureView
import android.view.View
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = TextureViewFactory.elementType,
        attributesClazz = TextureViewAttributes::class
)
open class TextureViewFactory<TView : TextureView, TAttributes : TextureViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextureView(context)

    companion object {
        const val elementType: String = "textureView"

        inline operator fun <reified TView : TextureView, reified TAttributes : TextureViewAttributes> invoke() = TextureViewFactory(TView::class.java, TAttributes::class.java)
    }
}
