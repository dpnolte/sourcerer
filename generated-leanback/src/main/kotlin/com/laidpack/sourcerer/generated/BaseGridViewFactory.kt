package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.BaseGridView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class BaseGridViewFactory<TView : BaseGridView, TAttributes : BaseGridViewAttributes> : RecyclerViewFactory<TView, TAttributes>() {
    override val elementName: String = "baseGridView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("androidx.leanback.widget.BaseGridView is abstract and cannot be instantiated")
    }
}
