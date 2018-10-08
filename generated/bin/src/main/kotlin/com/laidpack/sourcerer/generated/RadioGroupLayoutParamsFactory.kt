package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.RadioGroup
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RadioGroupLayoutParamsFactory<TLayoutParams : RadioGroup.LayoutParams, TAttributes : RadioGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.RadioGroup.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(RadioGroupLayoutParamsFactory<RadioGroup.LayoutParams, RadioGroupLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : RadioGroup.LayoutParams, reified TAttributes : RadioGroupLayoutParamsAttributes> invoke() = RadioGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
