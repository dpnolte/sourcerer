package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class LinearLayoutCompatFactory<TView : LinearLayoutCompat, TAttributes : LinearLayoutCompatAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "linearLayoutCompat"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = LinearLayoutCompat(context)
}
