package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ImageSwitcher
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ImageSwitcherFactory<TView : ImageSwitcher, TAttributes : ImageSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewSwitcherFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "imageSwitcher"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ImageSwitcher(context)

    companion object {
        init {
            InflaterComponent.addFactory(ImageSwitcherFactory<ImageSwitcher, ImageSwitcherAttributes>())
        }

        inline operator fun <reified TView : ImageSwitcher, reified TAttributes : ImageSwitcherAttributes> invoke() = ImageSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
