package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CoordinatorLayoutFactory<TView : CoordinatorLayout, TAttributes : CoordinatorLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "coordinatorLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CoordinatorLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(CoordinatorLayoutFactory<CoordinatorLayout, CoordinatorLayoutAttributes>())
        }

        inline operator fun <reified TView : CoordinatorLayout, reified TAttributes : CoordinatorLayoutAttributes> invoke() = CoordinatorLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
