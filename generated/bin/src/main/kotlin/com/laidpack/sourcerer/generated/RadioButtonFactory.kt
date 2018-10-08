package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RadioButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RadioButtonFactory<TView : RadioButton, TAttributes : RadioButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "radioButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = RadioButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(RadioButtonFactory<RadioButton, RadioButtonAttributes>())
        }

        inline operator fun <reified TView : RadioButton, reified TAttributes : RadioButtonAttributes> invoke() = RadioButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
