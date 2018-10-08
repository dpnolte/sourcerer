package com.laidpack.sourcerer.service.api

import android.content.Context
import android.view.View
import android.view.ViewGroup

object LayoutElement {
    interface Factory {
        val elementName: String
        val minimumApiLevel: Int
        val fallBackElementName: String?
    }
    interface ViewFactory : Factory {
        fun create(context: Context, attributes: IAttributes, theme: Int = 0): View
        fun update(view: View, context: Context, attributes: IAttributes)
    }
    interface LayoutParamsFactory : Factory {
        fun create(context: Context, attributes: IAttributes, theme: Int = 0): ViewGroup.LayoutParams
        fun update(layoutParams: ViewGroup.LayoutParams, context: Context, attributes: IAttributes)
    }
}

