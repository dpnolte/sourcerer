package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ToggleButton
import java.lang.Class
import kotlin.String

open class ToggleButtonFactory<TView : ToggleButton, TAttributes : ToggleButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ToggleButton(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ToggleButton) {
            view.apply {
                attributes.textOn?.let {
                    if (textOn != it) {
                        textOn = it
                    }
                }
                attributes.textOff?.let {
                    if (textOff != it) {
                        textOff = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "toggleButton"

        inline operator fun <reified TView : ToggleButton, reified TAttributes : ToggleButtonAttributes> invoke() = ToggleButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
