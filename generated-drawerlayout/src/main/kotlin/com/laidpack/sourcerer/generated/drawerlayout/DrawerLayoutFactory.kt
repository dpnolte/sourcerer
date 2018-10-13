package com.laidpack.sourcerer.generated.drawerlayout

import android.content.Context
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class DrawerLayoutFactory<TView : DrawerLayout, TAttributes : DrawerLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = DrawerLayout(context)

    companion object {
        const val elementType: String = "drawerLayout"

        inline operator fun <reified TView : DrawerLayout, reified TAttributes : DrawerLayoutAttributes> invoke() = DrawerLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
