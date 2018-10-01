package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class AbsListViewFactory<TView : AbsListView, TAttributes : AbsListViewAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "absListView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AbsListView is abstract and cannot be instantiated")
    }

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as AbsListView
        view.init {
            attributes.smoothScrollbar?.let {
                if (isSmoothScrollbarEnabled != it) {
                    isSmoothScrollbarEnabled = it
                }
            }
        }
    }
}
