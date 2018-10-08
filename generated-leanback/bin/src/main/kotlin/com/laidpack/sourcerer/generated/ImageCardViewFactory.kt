package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ImageCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ImageCardViewFactory<TView : ImageCardView, TAttributes : ImageCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "imageCardView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ImageCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ImageCardViewFactory<ImageCardView, ImageCardViewAttributes>())
        }

        inline operator fun <reified TView : ImageCardView, reified TAttributes : ImageCardViewAttributes> invoke() = ImageCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
