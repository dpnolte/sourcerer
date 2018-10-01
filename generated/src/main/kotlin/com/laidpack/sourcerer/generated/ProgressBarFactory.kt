package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ProgressBarFactory<TView : ProgressBar, TAttributes : ProgressBarAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "progressBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ProgressBar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ProgressBar
        view.init {
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.progressTint?.let {
                    val immutableProgressTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (progressTintList != immutableProgressTint) {
                        setProgressTintList(immutableProgressTint)
                    }
                }
                attributes.progressTintMode?.let {
                    val immutableProgressTintMode = it.value.toPorterDuffMode()
                    if (progressTintMode != immutableProgressTintMode) {
                        setProgressTintMode(immutableProgressTintMode)
                    }
                }
                attributes.progressBackgroundTint?.let {
                    val immutableProgressBackgroundTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (progressBackgroundTintList != immutableProgressBackgroundTint) {
                        setProgressBackgroundTintList(immutableProgressBackgroundTint)
                    }
                }
                attributes.progressBackgroundTintMode?.let {
                    val immutableProgressBackgroundTintMode = it.value.toPorterDuffMode()
                    if (progressBackgroundTintMode != immutableProgressBackgroundTintMode) {
                        setProgressBackgroundTintMode(immutableProgressBackgroundTintMode)
                    }
                }
                attributes.secondaryProgressTint?.let {
                    val immutableSecondaryProgressTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (secondaryProgressTintList != immutableSecondaryProgressTint) {
                        setSecondaryProgressTintList(immutableSecondaryProgressTint)
                    }
                }
                attributes.secondaryProgressTintMode?.let {
                    val immutableSecondaryProgressTintMode = it.value.toPorterDuffMode()
                    if (secondaryProgressTintMode != immutableSecondaryProgressTintMode) {
                        setSecondaryProgressTintMode(immutableSecondaryProgressTintMode)
                    }
                }
                attributes.indeterminateTint?.let {
                    val immutableIndeterminateTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (indeterminateTintList != immutableIndeterminateTint) {
                        setIndeterminateTintList(immutableIndeterminateTint)
                    }
                }
                attributes.indeterminateTintMode?.let {
                    val immutableIndeterminateTintMode = it.value.toPorterDuffMode()
                    if (indeterminateTintMode != immutableIndeterminateTintMode) {
                        setIndeterminateTintMode(immutableIndeterminateTintMode)
                    }
                }
            }
        }
    }
}
