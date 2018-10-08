package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ActionMenuView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ActionMenuViewFactory<TView : ActionMenuView, TAttributes : ActionMenuViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "actionMenuView"

    override val fallBackElementName: String? = "LinearLayout"

    override val minimumApiLevel: Int = 21

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return ActionMenuView(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ActionMenuViewFactory<ActionMenuView, ActionMenuViewAttributes>())
        }

        inline operator fun <reified TView : ActionMenuView, reified TAttributes : ActionMenuViewAttributes> invoke() = ActionMenuViewFactory(TView::class.java, TAttributes::class.java)
    }
}
