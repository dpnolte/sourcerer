package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ViewAnimator
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ViewAnimatorFactory<TView : ViewAnimator, TAttributes : ViewAnimatorAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "viewAnimator"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ViewAnimator(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ViewAnimator
        view.init {
            if (Build.VERSION.SDK_INT >= 17) {
                attributes.animateFirstView?.let {
                    if (animateFirstView != it) {
                        animateFirstView = it
                    }
                }
            }
        }
    }
}
