package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TextSwitcher
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TextSwitcherFactory.elementType,
        attributesClazz = TextSwitcherAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TextSwitcherFactory<TView : TextSwitcher, TAttributes : TextSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewSwitcherFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextSwitcher(context)

    companion object {
        const val elementType: String = "textSwitcher"

        inline operator fun <reified TView : TextSwitcher, reified TAttributes : TextSwitcherAttributes> invoke() = TextSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
