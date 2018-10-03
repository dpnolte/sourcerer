package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.slice.widget.RemoteInputView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class RemoteEditTextFactory<TView : RemoteInputView.RemoteEditText, TAttributes : RemoteEditTextAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "remoteEditText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RemoteInputView.RemoteEditText(context, null)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
