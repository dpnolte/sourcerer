package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.ActionMenuView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ActionMenuViewLayoutParamsFactory<TLayoutParams : ActionMenuView.LayoutParams, TAttributes : ActionMenuViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.appcompat.widget.ActionMenuView.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(ActionMenuViewLayoutParamsFactory<ActionMenuView.LayoutParams, ActionMenuViewLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ActionMenuView.LayoutParams, reified TAttributes : ActionMenuViewLayoutParamsAttributes> invoke() = ActionMenuViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
