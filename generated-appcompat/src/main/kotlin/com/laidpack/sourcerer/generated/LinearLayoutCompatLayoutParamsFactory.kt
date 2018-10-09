package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class LinearLayoutCompatLayoutParamsFactory<TLayoutParams : LinearLayoutCompat.LayoutParams, TAttributes : LinearLayoutCompatLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.appcompat.widget.LinearLayoutCompat.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(LinearLayoutCompatLayoutParamsFactory<LinearLayoutCompat.LayoutParams, LinearLayoutCompatLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : LinearLayoutCompat.LayoutParams, reified TAttributes : LinearLayoutCompatLayoutParamsAttributes> invoke() = LinearLayoutCompatLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
