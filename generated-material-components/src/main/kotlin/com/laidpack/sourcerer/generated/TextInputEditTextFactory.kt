package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class TextInputEditTextFactory<TView : TextInputEditText, TAttributes : TextInputEditTextAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "textInputEditText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TextInputEditText(context)
}
