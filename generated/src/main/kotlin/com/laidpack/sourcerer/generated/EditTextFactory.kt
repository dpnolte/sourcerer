package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class EditTextFactory<TView : EditText, TAttributes : EditTextAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "editText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EditText(context)
}
