package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class LinearLayoutLayoutParamsFactory<TLayoutParams : LinearLayout.LayoutParams, TAttributes : LinearLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.LinearLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(LinearLayoutLayoutParamsFactory<LinearLayout.LayoutParams, LinearLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : LinearLayout.LayoutParams, reified TAttributes : LinearLayoutLayoutParamsAttributes> invoke() = LinearLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
