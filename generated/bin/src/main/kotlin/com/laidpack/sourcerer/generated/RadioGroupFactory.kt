package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RadioGroup
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class RadioGroupFactory<TView : RadioGroup, TAttributes : RadioGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "radioGroup"

    override fun createInstance(context: Context): View = RadioGroup(context)

    companion object {
        init {
            InflaterComponent.addFactory(RadioGroupFactory<RadioGroup, RadioGroupAttributes>())
        }

        inline operator fun <reified TView : RadioGroup, reified TAttributes : RadioGroupAttributes> invoke() = RadioGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
