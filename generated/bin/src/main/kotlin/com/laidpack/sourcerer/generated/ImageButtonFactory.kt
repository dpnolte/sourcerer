package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ImageButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ImageButtonFactory<TView : ImageButton, TAttributes : ImageButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "imageButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ImageButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(ImageButtonFactory<ImageButton, ImageButtonAttributes>())
        }

        inline operator fun <reified TView : ImageButton, reified TAttributes : ImageButtonAttributes> invoke() = ImageButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
