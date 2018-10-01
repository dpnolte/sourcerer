package com.laidpack.sourcerer.generator.generators.api

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface ViewFactoryComponent<T : View, TAttributes> {
    fun create(context: Context, attributes: TAttributes, theme: Int = 0): View
    fun update(view: T, context: Context, attributes: TAttributes)
    val elementName: String
    val defaultLayoutParamsFactory : LayoutParamsFactoryComponent<*, *>
}
interface LayoutParamsFactoryComponent<T : ViewGroup.LayoutParams, TAttributes> {
    fun create(context: Context, attributes: TAttributes, theme: Int = 0): ViewGroup.LayoutParams
    fun update(layoutParams: T, context: Context, attributes: TAttributes)
    val elementName: String
}