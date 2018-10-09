package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.Toolbar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ToolbarLayoutParamsFactory<TLayoutParams : Toolbar.LayoutParams, TAttributes : ToolbarLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.Toolbar.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams {
        if (Build.VERSION.SDK_INT >= 21) {
            return Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ToolbarLayoutParamsFactory<Toolbar.LayoutParams, ToolbarLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : Toolbar.LayoutParams, reified TAttributes : ToolbarLayoutParamsAttributes> invoke() = ToolbarLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}