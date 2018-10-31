package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

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
            }
        }
    }

    companion object {
        const val elementType: String = "baseCardView"

        inline operator fun <reified TView : BaseCardView, reified TAttributes : BaseCardViewAttributes> invoke() = BaseCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
