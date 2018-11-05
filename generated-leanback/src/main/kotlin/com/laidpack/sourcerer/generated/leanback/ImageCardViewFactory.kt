package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ImageCardViewFactory.elementType,
        attributesClazz = ImageCardViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ImageCardViewFactory<TView : ImageCardView, TAttributes : ImageCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseCardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ImageCardView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ImageCardView) {
            view.apply {
                attributes.lbinfoAreaBackground?.let {
                    val localLbinfoAreaBackground = ContextCompat.getDrawable(context, it) as Drawable
                    if (infoAreaBackground != localLbinfoAreaBackground) {
                        infoAreaBackground = localLbinfoAreaBackground
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "imageCardView"

        inline operator fun <reified TView : ImageCardView, reified TAttributes : ImageCardViewAttributes> invoke() = ImageCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
