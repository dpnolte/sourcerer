package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlin.String

open class GridLayoutManagerLayoutParamsFactory<TLayoutParams : GridLayoutManager.LayoutParams, TAttributes : GridLayoutManagerLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT)
}
