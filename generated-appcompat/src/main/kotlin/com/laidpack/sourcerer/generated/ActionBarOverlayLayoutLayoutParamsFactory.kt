package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.ActionBarOverlayLayout
import kotlin.String

open class ActionBarOverlayLayoutLayoutParamsFactory<TLayoutParams : ActionBarOverlayLayout.LayoutParams, TAttributes : ActionBarOverlayLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ActionBarOverlayLayout.LayoutParams(ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.MATCH_PARENT)
}
