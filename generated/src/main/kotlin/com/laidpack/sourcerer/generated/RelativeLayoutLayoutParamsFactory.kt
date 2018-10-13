package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import java.lang.Class
import kotlin.String

open class RelativeLayoutLayoutParamsFactory<TLayoutParams : RelativeLayout.LayoutParams, TAttributes : RelativeLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "android.widget.RelativeLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : RelativeLayout.LayoutParams, reified TAttributes : RelativeLayoutLayoutParamsAttributes> invoke() = RelativeLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
