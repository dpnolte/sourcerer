package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ViewStubFactory<TView : ViewStub, TAttributes : ViewStubAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "viewStub"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ViewStub(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ViewStub
        view.init {
            attributes.layout?.let {
                if (layoutResource != it) {
                    layoutResource = it
                }
            }
            attributes.inflatedId?.let {
                if (inflatedId != it) {
                    inflatedId = it
                }
            }
        }
    }
}
