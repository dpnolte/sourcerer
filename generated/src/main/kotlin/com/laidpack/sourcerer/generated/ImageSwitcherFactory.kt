package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ImageSwitcher
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ImageSwitcherFactory.elementType,
        attributesClazz = ImageSwitcherAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ImageSwitcherFactory<TView : ImageSwitcher, TAttributes : ImageSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewSwitcherFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ImageSwitcher(context)

    companion object {
        const val elementType: String = "imageSwitcher"

        inline operator fun <reified TView : ImageSwitcher, reified TAttributes : ImageSwitcherAttributes> invoke() = ImageSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
