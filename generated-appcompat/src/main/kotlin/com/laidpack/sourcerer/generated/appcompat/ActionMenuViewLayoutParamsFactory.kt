package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.ActionMenuView
import java.lang.Class
import kotlin.String

open class ActionMenuViewLayoutParamsFactory<TLayoutParams : ActionMenuView.LayoutParams, TAttributes : ActionMenuViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutCompatLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "androidx.appcompat.widget.ActionMenuView.LayoutParams"

        inline operator fun <reified TLayoutParams : ActionMenuView.LayoutParams, reified TAttributes : ActionMenuViewLayoutParamsAttributes> invoke() = ActionMenuViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
