package com.laidpack.sourcerer.generated.viewpager

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerTitleStrip
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = PagerTitleStripFactory.elementType,
        attributesClazz = PagerTitleStripAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class PagerTitleStripFactory<TView : PagerTitleStrip, TAttributes : PagerTitleStripAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = PagerTitleStrip(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is PagerTitleStrip) {
            view.apply {
                attributes.textSize?.let {
                    val localTextSize = it.toFloat()
                    setTextSize(0 /* TypedValue.COMPLEX_UNIT_PX */, localTextSize)
                }
                if (attributes.gravity.hasInteger || attributes.gravity.hasFlags) {
                    val localGravity = when {
                        attributes.gravity.hasInteger -> attributes.gravity.integer
                        else -> attributes.gravity.flags
                    }
                    setGravity(localGravity)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "pagerTitleStrip"

        inline operator fun <reified TView : PagerTitleStrip, reified TAttributes : PagerTitleStripAttributes> invoke() = PagerTitleStripFactory(TView::class.java, TAttributes::class.java)
    }
}
