package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class RelativeLayoutFactory<TView : RelativeLayout, TAttributes : RelativeLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "relativeLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RelativeLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as RelativeLayout
        view.init {
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.gravity?.let {
                    if (gravity != it) {
                        gravity = it
                    }
                }
            }
        }
    }
}
