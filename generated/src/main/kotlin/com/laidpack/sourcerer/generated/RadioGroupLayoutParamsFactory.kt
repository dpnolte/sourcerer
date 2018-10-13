package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.RadioGroup
import java.lang.Class
import kotlin.String

open class RadioGroupLayoutParamsFactory<TLayoutParams : RadioGroup.LayoutParams, TAttributes : RadioGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "android.widget.RadioGroup.LayoutParams"

        inline operator fun <reified TLayoutParams : RadioGroup.LayoutParams, reified TAttributes : RadioGroupLayoutParamsAttributes> invoke() = RadioGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
