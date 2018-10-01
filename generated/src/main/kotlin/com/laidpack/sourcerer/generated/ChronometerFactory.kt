package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ChronometerFactory<TView : Chronometer, TAttributes : ChronometerAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "chronometer"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = Chronometer(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
