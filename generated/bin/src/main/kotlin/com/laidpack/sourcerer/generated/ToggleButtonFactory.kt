package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ToggleButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ToggleButtonFactory<TView : ToggleButton, TAttributes : ToggleButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "toggleButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ToggleButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(ToggleButtonFactory<ToggleButton, ToggleButtonAttributes>())
        }

        inline operator fun <reified TView : ToggleButton, reified TAttributes : ToggleButtonAttributes> invoke() = ToggleButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
