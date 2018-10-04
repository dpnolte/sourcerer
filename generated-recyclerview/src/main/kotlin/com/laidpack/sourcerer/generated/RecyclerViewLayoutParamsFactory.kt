package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.String

open class RecyclerViewLayoutParamsFactory<TLayoutParams : RecyclerView.LayoutParams, TAttributes : RecyclerViewLayoutParamsAttributes> : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT)
}
