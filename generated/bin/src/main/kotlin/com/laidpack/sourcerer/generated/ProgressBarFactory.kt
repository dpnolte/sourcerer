package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ProgressBarFactory<TView : ProgressBar, TAttributes : ProgressBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "progressBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ProgressBar(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.min?.let {
                if (min != it) {
                    min = it
                }
            }
            attributes.max?.let {
                if (max != it) {
                    max = it
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
            attributes.indeterminateDrawable?.let {
                val immutableIndeterminateDrawable = ContextCompat.getDrawable(context, it) as Drawable
                if (indeterminateDrawable != immutableIndeterminateDrawable) {
                    setIndeterminateDrawableTiled(immutableIndeterminateDrawable)
                }
            }
            attributes.progressDrawable?.let {
                val immutableProgressDrawable = ContextCompat.getDrawable(context, it) as Drawable
                if (progressDrawable != immutableProgressDrawable) {
                    setProgressDrawableTiled(immutableProgressDrawable)
                }
            }
            attributes.progressTint?.let {
                val immutableProgressTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (progressTintList != immutableProgressTint) {
                    progressTintList = immutableProgressTint
                }
            }
            attributes.progressTintMode?.let {
                val immutableProgressTintMode = it.value.toPorterDuffMode()
                if (progressTintMode != immutableProgressTintMode) {
                    progressTintMode = immutableProgressTintMode
                }
            }
            attributes.progressBackgroundTint?.let {
                val immutableProgressBackgroundTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (progressBackgroundTintList != immutableProgressBackgroundTint) {
                    progressBackgroundTintList = immutableProgressBackgroundTint
                }
            }
            attributes.progressBackgroundTintMode?.let {
                val immutableProgressBackgroundTintMode = it.value.toPorterDuffMode()
                if (progressBackgroundTintMode != immutableProgressBackgroundTintMode) {
                    progressBackgroundTintMode = immutableProgressBackgroundTintMode
                }
            }
            attributes.secondaryProgressTint?.let {
                val immutableSecondaryProgressTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (secondaryProgressTintList != immutableSecondaryProgressTint) {
                    secondaryProgressTintList = immutableSecondaryProgressTint
                }
            }
            attributes.secondaryProgressTintMode?.let {
                val immutableSecondaryProgressTintMode = it.value.toPorterDuffMode()
                if (secondaryProgressTintMode != immutableSecondaryProgressTintMode) {
                    secondaryProgressTintMode = immutableSecondaryProgressTintMode
                }
            }
            attributes.indeterminateTint?.let {
                val immutableIndeterminateTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (indeterminateTintList != immutableIndeterminateTint) {
                    indeterminateTintList = immutableIndeterminateTint
                }
            }
            attributes.indeterminateTintMode?.let {
                val immutableIndeterminateTintMode = it.value.toPorterDuffMode()
                if (indeterminateTintMode != immutableIndeterminateTintMode) {
                    indeterminateTintMode = immutableIndeterminateTintMode
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ProgressBarFactory<ProgressBar, ProgressBarAttributes>())
        }

        inline operator fun <reified TView : ProgressBar, reified TAttributes : ProgressBarAttributes> invoke() = ProgressBarFactory(TView::class.java, TAttributes::class.java)
    }
}
