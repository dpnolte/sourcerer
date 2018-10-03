package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import kotlin.String

open class DrawerLayoutLayoutParamsFactory<TLayoutParams : DrawerLayout.LayoutParams, TAttributes : DrawerLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
