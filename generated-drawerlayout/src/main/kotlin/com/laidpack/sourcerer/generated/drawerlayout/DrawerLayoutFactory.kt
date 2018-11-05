package com.laidpack.sourcerer.generated.drawerlayout

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = DrawerLayoutFactory.elementType,
        attributesClazz = DrawerLayoutAttributes::class,
        layoutParamAttributesClazz = DrawerLayoutLayoutParamsAttributes::class
)
open class DrawerLayoutFactory<TView : DrawerLayout, TAttributes : DrawerLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = DrawerLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is DrawerLayout) {
            view.apply {
                attributes.colorPrimaryDark?.let {
                    val localColorPrimaryDark = ColorDrawable(it)
                    if (statusBarBackgroundDrawable != localColorPrimaryDark) {
                        setStatusBarBackground(localColorPrimaryDark)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "drawerLayout"

        inline operator fun <reified TView : DrawerLayout, reified TAttributes : DrawerLayoutAttributes> invoke() = DrawerLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
