package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.Toolbar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ToolbarLayoutParamsFactory<TLayoutParams : Toolbar.LayoutParams, TAttributes : ToolbarLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.Toolbar.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(ToolbarLayoutParamsFactory<Toolbar.LayoutParams, ToolbarLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : Toolbar.LayoutParams, reified TAttributes : ToolbarLayoutParamsAttributes> invoke() = ToolbarLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
