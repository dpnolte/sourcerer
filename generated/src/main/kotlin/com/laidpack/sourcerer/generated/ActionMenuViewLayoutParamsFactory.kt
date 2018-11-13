package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.ActionMenuView
import com.laidpack.generator.api.LayoutParamsElement
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = ActionMenuViewLayoutParamsFactory.elementType,
        attributesClazz = ActionMenuViewLayoutParamsAttributes::class
)
open class ActionMenuViewLayoutParamsFactory<TLayoutParams : ActionMenuView.LayoutParams, TAttributes : ActionMenuViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams {
        if (Build.VERSION.SDK_INT >= 21) {
            return ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.MATCH_PARENT)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        const val elementType: String = "android.widget.ActionMenuView.LayoutParams"

        inline operator fun <reified TLayoutParams : ActionMenuView.LayoutParams, reified TAttributes : ActionMenuViewLayoutParamsAttributes> invoke() = ActionMenuViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
