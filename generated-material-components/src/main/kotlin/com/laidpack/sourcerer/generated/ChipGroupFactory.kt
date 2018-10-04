package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.ChipGroup
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class ChipGroupFactory<TView : ChipGroup, TAttributes : ChipGroupAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "chipGroup"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ChipGroup(context)
}
