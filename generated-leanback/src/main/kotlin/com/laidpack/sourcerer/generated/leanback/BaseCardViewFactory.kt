package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = BaseCardViewFactory.elementType,
        attributesClazz = BaseCardViewAttributes::class,
        layoutParamAttributesClazz = BaseCardViewLayoutParamsAttributes::class
)
open class BaseCardViewFactory<TView : BaseCardView, TAttributes : BaseCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BaseCardView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is BaseCardView) {
            view.apply {
                attributes.lbcardType?.let {
                    if (cardType != it) {
                        cardType = it
                    }
                }
                attributes.lbinfoVisibility?.let {
                    if (visibility != it) {
                        infoVisibility = it
                    }
                }
                attributes.lbextraVisibility?.let {
                    if (visibility != it) {
                        extraVisibility = it
                    }
                }
                attributes.lbcardForeground?.let {
                    val localLbcardForeground = ContextCompat.getDrawable(context, it) as Drawable
                    if (foreground != localLbcardForeground) {
                        foreground = localLbcardForeground
                    }
                }
                attributes.lbcardBackground?.let {
                    val localLbcardBackground = ContextCompat.getDrawable(context, it) as Drawable
                    if (background != localLbcardBackground) {
                        background = localLbcardBackground
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "baseCardView"

        inline operator fun <reified TView : BaseCardView, reified TAttributes : BaseCardViewAttributes> invoke() = BaseCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
