package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class LinearLayoutCompatLayoutParamsFactory<TLayoutParams : LinearLayoutCompat.LayoutParams, TAttributes : LinearLayoutCompatLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "androidx.appcompat.widget.LinearLayoutCompat.LayoutParams"

        inline operator fun <reified TLayoutParams : LinearLayoutCompat.LayoutParams, reified TAttributes : LinearLayoutCompatLayoutParamsAttributes> invoke() = LinearLayoutCompatLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
