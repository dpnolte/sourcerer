package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class DrawerLayoutLayoutParamsFactory<TLayoutParams : DrawerLayout.LayoutParams, TAttributes : DrawerLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.drawerlayout.widget.DrawerLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(DrawerLayoutLayoutParamsFactory<DrawerLayout.LayoutParams, DrawerLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : DrawerLayout.LayoutParams, reified TAttributes : DrawerLayoutLayoutParamsAttributes> invoke() = DrawerLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
