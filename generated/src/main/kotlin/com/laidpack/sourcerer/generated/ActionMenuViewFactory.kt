package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ActionMenuView
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ActionMenuViewFactory.elementType,
        attributesClazz = ActionMenuViewAttributes::class,
        layoutParamAttributesClazz = ActionMenuViewLayoutParamsAttributes::class
)
open class ActionMenuViewFactory<TView : ActionMenuView, TAttributes : ActionMenuViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return ActionMenuView(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        const val elementType: String = "actionMenuView"

        inline operator fun <reified TView : ActionMenuView, reified TAttributes : ActionMenuViewAttributes> invoke() = ActionMenuViewFactory(TView::class.java, TAttributes::class.java)
    }
}
