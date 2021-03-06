package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TabHost
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TabHostFactory.elementType,
        attributesClazz = TabHostAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TabHostFactory<TView : TabHost, TAttributes : TabHostAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TabHost(context)

    companion object {
        const val elementType: String = "tabHost"

        inline operator fun <reified TView : TabHost, reified TAttributes : TabHostAttributes> invoke() = TabHostFactory(TView::class.java, TAttributes::class.java)
    }
}
