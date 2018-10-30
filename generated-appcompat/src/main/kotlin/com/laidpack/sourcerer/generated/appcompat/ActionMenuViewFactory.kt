package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.ActionMenuView
import java.lang.Class
import kotlin.String

open class ActionMenuViewFactory<TView : ActionMenuView, TAttributes : ActionMenuViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutCompatFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ActionMenuView(context)

    companion object {
        const val elementType: String = "appcompat.actionMenuView"

        inline operator fun <reified TView : ActionMenuView, reified TAttributes : ActionMenuViewAttributes> invoke() = ActionMenuViewFactory(TView::class.java, TAttributes::class.java)
    }
}
