package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.WearableRecyclerView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class WearableRecyclerViewFactory<TView : WearableRecyclerView, TAttributes : WearableRecyclerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "wearableRecyclerView"

    override fun createInstance(context: Context): View = WearableRecyclerView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is WearableRecyclerView) {
            view.init {
                attributes.bezelWidth?.let {
                    if (bezelFraction != it) {
                        bezelFraction = it
                    }
                }
                attributes.circularScrollingGestureEnabled?.let {
                    if (isCircularScrollingGestureEnabled != it) {
                        isCircularScrollingGestureEnabled = it
                    }
                }
                attributes.scrollDegreesPerScreen?.let {
                    if (scrollDegreesPerScreen != it) {
                        scrollDegreesPerScreen = it
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(WearableRecyclerViewFactory<WearableRecyclerView, WearableRecyclerViewAttributes>())
        }

        inline operator fun <reified TView : WearableRecyclerView, reified TAttributes : WearableRecyclerViewAttributes> invoke() = WearableRecyclerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
