package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewSwitcher
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ViewSwitcherFactory.elementType,
        attributesClazz = ViewSwitcherAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ViewSwitcherFactory<TView : ViewSwitcher, TAttributes : ViewSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewAnimatorFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ViewSwitcher(context)

    companion object {
        const val elementType: String = "viewSwitcher"

        inline operator fun <reified TView : ViewSwitcher, reified TAttributes : ViewSwitcherAttributes> invoke() = ViewSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
