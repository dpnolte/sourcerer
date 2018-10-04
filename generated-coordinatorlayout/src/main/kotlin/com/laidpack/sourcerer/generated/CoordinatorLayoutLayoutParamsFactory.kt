package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import kotlin.String

open class CoordinatorLayoutLayoutParamsFactory<TLayoutParams : CoordinatorLayout.LayoutParams, TAttributes : CoordinatorLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT)
}
