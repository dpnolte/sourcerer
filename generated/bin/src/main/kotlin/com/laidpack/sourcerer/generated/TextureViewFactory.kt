package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.TextureView
import android.view.View
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TextureViewFactory<TView : TextureView, TAttributes : TextureViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textureView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TextureView(context)

    companion object {
        init {
            InflaterComponent.addFactory(TextureViewFactory<TextureView, TextureViewAttributes>())
        }

        inline operator fun <reified TView : TextureView, reified TAttributes : TextureViewAttributes> invoke() = TextureViewFactory(TView::class.java, TAttributes::class.java)
    }
}
