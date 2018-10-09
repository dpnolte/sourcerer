package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.AbsListView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AbsListViewLayoutParamsFactory<TLayoutParams : AbsListView.LayoutParams, TAttributes : AbsListViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.AbsListView.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(AbsListViewLayoutParamsFactory<AbsListView.LayoutParams, AbsListViewLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : AbsListView.LayoutParams, reified TAttributes : AbsListViewLayoutParamsAttributes> invoke() = AbsListViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
