package com.laidpack.sourcerer.generated.car

import android.content.Context
import android.view.View
import androidx.car.widget.ActionBar
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.RelativeLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ActionBarFactory.elementType,
        attributesClazz = ActionBarAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ActionBarFactory<TView : ActionBar, TAttributes : ActionBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RelativeLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ActionBar(context)

    companion object {
        const val elementType: String = "actionBar"

        inline operator fun <reified TView : ActionBar, reified TAttributes : ActionBarAttributes> invoke() = ActionBarFactory(TView::class.java, TAttributes::class.java)
    }
}
