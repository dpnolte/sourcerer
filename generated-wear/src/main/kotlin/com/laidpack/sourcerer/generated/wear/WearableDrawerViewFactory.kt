package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableDrawerView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class WearableDrawerViewFactory<TView : WearableDrawerView, TAttributes : WearableDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = WearableDrawerView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is WearableDrawerView) {
            view.apply {
                attributes.enableAutoPeek?.let {
                    if (isAutoPeekEnabled != it) {
                        setIsAutoPeekEnabled(it)
                    }
                }
                attributes.android_elevation?.let {
                    val localAndroidElevation = it.toFloat()
                    if (elevation != localAndroidElevation) {
                        elevation = localAndroidElevation
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "wearableDrawerView"

        inline operator fun <reified TView : WearableDrawerView, reified TAttributes : WearableDrawerViewAttributes> invoke() = WearableDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
