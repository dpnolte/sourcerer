package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class AppBarLayoutFactory<TView : AppBarLayout, TAttributes : AppBarLayoutAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "appBarLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = AppBarLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as AppBarLayout
        view.init {
            attributes.elevation?.let {
                val immutableElevation = it.toFloat()
                if (targetElevation != immutableElevation) {
                    targetElevation = immutableElevation
                }
            }
        }
    }
}
