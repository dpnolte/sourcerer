package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class ToolbarLayoutParamsFactory<TLayoutParams : Toolbar.LayoutParams, TAttributes : ToolbarLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "androidx.appcompat.widget.Toolbar.LayoutParams"

        inline operator fun <reified TLayoutParams : Toolbar.LayoutParams, reified TAttributes : ToolbarLayoutParamsAttributes> invoke() = ToolbarLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
