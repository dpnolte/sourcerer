package com.laidpack.sourcerer.generated.coordinatorlayout

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class CoordinatorLayoutFactory<TView : CoordinatorLayout, TAttributes : CoordinatorLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CoordinatorLayout(context)

    companion object {
        const val elementType: String = "coordinatorLayout"

        inline operator fun <reified TView : CoordinatorLayout, reified TAttributes : CoordinatorLayoutAttributes> invoke() = CoordinatorLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
