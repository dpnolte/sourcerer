package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.car.widget.ActionBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ActionBarFactory<TView : ActionBar, TAttributes : ActionBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RelativeLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "actionBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ActionBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(ActionBarFactory<ActionBar, ActionBarAttributes>())
        }

        inline operator fun <reified TView : ActionBar, reified TAttributes : ActionBarAttributes> invoke() = ActionBarFactory(TView::class.java, TAttributes::class.java)
    }
}
