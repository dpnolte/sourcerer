package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableDrawerLayout
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class WearableDrawerLayoutFactory<TView : WearableDrawerLayout, TAttributes : WearableDrawerLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = WearableDrawerLayout(context)

    companion object {
        const val elementType: String = "wearableDrawerLayout"

        inline operator fun <reified TView : WearableDrawerLayout, reified TAttributes : WearableDrawerLayoutAttributes> invoke() = WearableDrawerLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
