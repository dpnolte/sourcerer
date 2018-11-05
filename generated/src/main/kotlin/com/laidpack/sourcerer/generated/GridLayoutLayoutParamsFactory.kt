package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.GridLayout
import com.laidpack.generator.api.LayoutParamsElement
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = GridLayoutLayoutParamsFactory.elementType,
        attributesClazz = GridLayoutLayoutParamsAttributes::class
)
open class GridLayoutLayoutParamsFactory<TLayoutParams : GridLayout.LayoutParams, TAttributes : GridLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    companion object {
        const val elementType: String = "android.widget.GridLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : GridLayout.LayoutParams, reified TAttributes : GridLayoutLayoutParamsAttributes> invoke() = GridLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
