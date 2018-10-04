package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.AbsListView
import kotlin.String

open class AbsListViewLayoutParamsFactory<TLayoutParams : AbsListView.LayoutParams, TAttributes : AbsListViewLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT)
}
