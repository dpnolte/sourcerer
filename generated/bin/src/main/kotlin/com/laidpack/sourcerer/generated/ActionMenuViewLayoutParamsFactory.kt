package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.ActionMenuView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ActionMenuViewLayoutParamsFactory<TLayoutParams : ActionMenuView.LayoutParams, TAttributes : ActionMenuViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.ActionMenuView.LayoutParams"

    override val fallBackElementName: String? = "android.widget.LinearLayout.LayoutParams"

    override val minimumApiLevel: Int = 21

    override fun createInstance(context: Context): ViewGroup.LayoutParams {
        if (Build.VERSION.SDK_INT >= 21) {
            return ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.MATCH_PARENT)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ActionMenuViewLayoutParamsFactory<ActionMenuView.LayoutParams, ActionMenuViewLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ActionMenuView.LayoutParams, reified TAttributes : ActionMenuViewLayoutParamsAttributes> invoke() = ActionMenuViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
