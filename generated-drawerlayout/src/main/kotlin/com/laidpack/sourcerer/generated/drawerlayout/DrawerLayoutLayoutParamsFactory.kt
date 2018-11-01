package com.laidpack.sourcerer.generated.drawerlayout

import android.content.Context
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class DrawerLayoutLayoutParamsFactory<TLayoutParams : DrawerLayout.LayoutParams, TAttributes : DrawerLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is DrawerLayout.LayoutParams) {
            layoutParams.apply {
                if (attributes.layout_gravity.hasInteger || attributes.layout_gravity.hasFlags) {
                    val localLayoutGravity = when {
                        attributes.layout_gravity.hasInteger -> attributes.layout_gravity.integer
                        else -> attributes.layout_gravity.flags
                    }
                    if (localLayoutGravity != gravity) {
                        gravity = localLayoutGravity
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "androidx.drawerlayout.widget.DrawerLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : DrawerLayout.LayoutParams, reified TAttributes : DrawerLayoutLayoutParamsAttributes> invoke() = DrawerLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
