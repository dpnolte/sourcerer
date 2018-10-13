package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.AbsListView
import java.lang.Class
import kotlin.String

open class AbsListViewLayoutParamsFactory<TLayoutParams : AbsListView.LayoutParams, TAttributes : AbsListViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "android.widget.AbsListView.LayoutParams"

        inline operator fun <reified TLayoutParams : AbsListView.LayoutParams, reified TAttributes : AbsListViewLayoutParamsAttributes> invoke() = AbsListViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
