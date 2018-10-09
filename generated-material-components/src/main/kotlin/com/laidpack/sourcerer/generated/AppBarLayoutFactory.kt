package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class AppBarLayoutFactory<TView : AppBarLayout, TAttributes : AppBarLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appBarLayout"

    override fun createInstance(context: Context): View = AppBarLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AppBarLayout) {
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

    companion object {
        init {
            InflaterComponent.addFactory(AppBarLayoutFactory<AppBarLayout, AppBarLayoutAttributes>())
        }

        inline operator fun <reified TView : AppBarLayout, reified TAttributes : AppBarLayoutAttributes> invoke() = AppBarLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
