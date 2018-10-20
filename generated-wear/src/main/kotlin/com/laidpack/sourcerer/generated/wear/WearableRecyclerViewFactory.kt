package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.WearableRecyclerView
import com.laidpack.sourcerer.generated.recyclerview.RecyclerViewFactory
import java.lang.Class
import kotlin.String

open class WearableRecyclerViewFactory<TView : WearableRecyclerView, TAttributes : WearableRecyclerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RecyclerViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = WearableRecyclerView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is WearableRecyclerView) {
            view.apply {
                attributes.bezelWidth?.let {
                    bezelFraction = it
                }
                attributes.circularScrollingGestureEnabled?.let {
                    if (isCircularScrollingGestureEnabled != it) {
                        isCircularScrollingGestureEnabled = it
                    }
                }
                attributes.scrollDegreesPerScreen?.let {
                    scrollDegreesPerScreen = it
                }
            }
        }
    }

    companion object {
        const val elementType: String = "wearableRecyclerView"

        inline operator fun <reified TView : WearableRecyclerView, reified TAttributes : WearableRecyclerViewAttributes> invoke() = WearableRecyclerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
