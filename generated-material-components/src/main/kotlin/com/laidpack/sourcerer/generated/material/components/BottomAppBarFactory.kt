package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.bottomappbar.BottomAppBar
import com.laidpack.sourcerer.generated.appcompat.ToolbarFactory
import java.lang.Class
import kotlin.String

open class BottomAppBarFactory<TView : BottomAppBar, TAttributes : BottomAppBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ToolbarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BottomAppBar(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is BottomAppBar) {
            view.apply {
                attributes.fabAlignmentMode?.let {
                    if (fabAlignmentMode != it.value) {
                        fabAlignmentMode = it.value
                    }
                }
                attributes.hideOnScroll?.let {
                    if (hideOnScroll != it) {
                        hideOnScroll = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "bottomAppBar"

        inline operator fun <reified TView : BottomAppBar, reified TAttributes : BottomAppBarAttributes> invoke() = BottomAppBarFactory(TView::class.java, TAttributes::class.java)
    }
}
