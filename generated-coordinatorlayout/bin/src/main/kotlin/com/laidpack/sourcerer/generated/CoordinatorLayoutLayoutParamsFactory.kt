package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CoordinatorLayoutLayoutParamsFactory<TLayoutParams : CoordinatorLayout.LayoutParams, TAttributes : CoordinatorLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String =
            "androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(CoordinatorLayoutLayoutParamsFactory<CoordinatorLayout.LayoutParams, CoordinatorLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : CoordinatorLayout.LayoutParams, reified TAttributes : CoordinatorLayoutLayoutParamsAttributes> invoke() = CoordinatorLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
