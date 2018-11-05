package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = ProgressBarFactory.elementType,
        attributesClazz = ProgressBarAttributes::class
)
open class ProgressBarFactory<TView : ProgressBar, TAttributes : ProgressBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ProgressBar(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ProgressBar) {
            view.apply {
                attributes.max?.let {
                    if (max != it) {
                        max = it
                    }
                }
                attributes.progress?.let {
                    if (progress != it) {
                        progress = it
                    }
                }
                attributes.secondaryProgress?.let {
                    if (secondaryProgress != it) {
                        secondaryProgress = it
                    }
                }
                attributes.indeterminate?.let {
                    if (isIndeterminate != it) {
                        isIndeterminate = it
                    }
                }
                attributes.interpolator?.let {
                    setInterpolator(context, it)
                }
                attributes.minWidth?.let {
                    if (minimumWidth != it) {
                        minimumWidth = it
                    }
                }
                attributes.minHeight?.let {
                    if (minimumHeight != it) {
                        minimumHeight = it
                    }
                }
                attributes.indeterminateDrawable?.let {
                    val localIndeterminateDrawable = ContextCompat.getDrawable(context, it) as Drawable
                    if (indeterminateDrawable != localIndeterminateDrawable) {
                        setIndeterminateDrawableTiled(localIndeterminateDrawable)
                    }
                }
                attributes.progressDrawable?.let {
                    val localProgressDrawable = ContextCompat.getDrawable(context, it) as Drawable
                    if (progressDrawable != localProgressDrawable) {
                        setProgressDrawableTiled(localProgressDrawable)
                    }
                }
                attributes.progressTint?.let {
                    val localProgressTint = ColorStateList.valueOf(it)
                    if (progressTintList != localProgressTint) {
                        progressTintList = localProgressTint
                    }
                }
                attributes.progressTintMode?.let {
                    val localProgressTintMode = it.value.toPorterDuffMode()
                    if (progressTintMode != localProgressTintMode) {
                        progressTintMode = localProgressTintMode
                    }
                }
                attributes.progressBackgroundTint?.let {
                    val localProgressBackgroundTint = ColorStateList.valueOf(it)
                    if (progressBackgroundTintList != localProgressBackgroundTint) {
                        progressBackgroundTintList = localProgressBackgroundTint
                    }
                }
                attributes.progressBackgroundTintMode?.let {
                    val localProgressBackgroundTintMode = it.value.toPorterDuffMode()
                    if (progressBackgroundTintMode != localProgressBackgroundTintMode) {
                        progressBackgroundTintMode = localProgressBackgroundTintMode
                    }
                }
                attributes.secondaryProgressTint?.let {
                    val localSecondaryProgressTint = ColorStateList.valueOf(it)
                    if (secondaryProgressTintList != localSecondaryProgressTint) {
                        secondaryProgressTintList = localSecondaryProgressTint
                    }
                }
                attributes.secondaryProgressTintMode?.let {
                    val localSecondaryProgressTintMode = it.value.toPorterDuffMode()
                    if (secondaryProgressTintMode != localSecondaryProgressTintMode) {
                        secondaryProgressTintMode = localSecondaryProgressTintMode
                    }
                }
                attributes.indeterminateTint?.let {
                    val localIndeterminateTint = ColorStateList.valueOf(it)
                    if (indeterminateTintList != localIndeterminateTint) {
                        indeterminateTintList = localIndeterminateTint
                    }
                }
                attributes.indeterminateTintMode?.let {
                    val localIndeterminateTintMode = it.value.toPorterDuffMode()
                    if (indeterminateTintMode != localIndeterminateTintMode) {
                        indeterminateTintMode = localIndeterminateTintMode
                    }
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    attributes.min?.let {
                        if (min != it) {
                            min = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "progressBar"

        inline operator fun <reified TView : ProgressBar, reified TAttributes : ProgressBarAttributes> invoke() = ProgressBarFactory(TView::class.java, TAttributes::class.java)
    }
}
