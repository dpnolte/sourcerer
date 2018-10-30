package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.ImageCardView
import java.lang.Class
import kotlin.String

open class ImageCardViewFactory<TView : ImageCardView, TAttributes : ImageCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseCardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ImageCardView(context)

    companion object {
        const val elementType: String = "imageCardView"

        inline operator fun <reified TView : ImageCardView, reified TAttributes : ImageCardViewAttributes> invoke() = ImageCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
