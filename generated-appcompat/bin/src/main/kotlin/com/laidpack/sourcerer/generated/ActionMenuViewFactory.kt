package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.ActionMenuView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ActionMenuViewFactory<TView : ActionMenuView, TAttributes : ActionMenuViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "actionMenuView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ActionMenuView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ActionMenuViewFactory<ActionMenuView, ActionMenuViewAttributes>())
        }

        inline operator fun <reified TView : ActionMenuView, reified TAttributes : ActionMenuViewAttributes> invoke() = ActionMenuViewFactory(TView::class.java, TAttributes::class.java)
    }
}
