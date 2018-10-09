package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class DrawerLayoutFactory<TView : DrawerLayout, TAttributes : DrawerLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "drawerLayout"

    override fun createInstance(context: Context): View = DrawerLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(DrawerLayoutFactory<DrawerLayout, DrawerLayoutAttributes>())
        }

        inline operator fun <reified TView : DrawerLayout, reified TAttributes : DrawerLayoutAttributes> invoke() = DrawerLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
