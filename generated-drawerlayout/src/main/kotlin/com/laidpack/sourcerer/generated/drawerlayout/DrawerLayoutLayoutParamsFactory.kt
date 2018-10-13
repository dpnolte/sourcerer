package com.laidpack.sourcerer.generated.drawerlayout

import android.content.Context
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class DrawerLayoutLayoutParamsFactory<TLayoutParams : DrawerLayout.LayoutParams, TAttributes : DrawerLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "androidx.drawerlayout.widget.DrawerLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : DrawerLayout.LayoutParams, reified TAttributes : DrawerLayoutLayoutParamsAttributes> invoke() = DrawerLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
