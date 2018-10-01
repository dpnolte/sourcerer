package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ImageViewFactory<TView : ImageView, TAttributes : ImageViewAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "imageView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ImageView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ImageView
        view.init {
            attributes.scaleType?.let {
                val immutableScaleType = it.value.toScaleType()
                if (scaleType != immutableScaleType) {
                    scaleType = immutableScaleType
                }
            }
            attributes.baselineAlignBottom?.let {
                if (baselineAlignBottom != it) {
                    baselineAlignBottom = it
                }
            }
            attributes.baseline?.let {
                if (baseline != it) {
                    baseline = it
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.adjustViewBounds?.let {
                    if (adjustViewBounds != it) {
                        adjustViewBounds = it
                    }
                }
                attributes.maxWidth?.let {
                    if (maxWidth != it) {
                        maxWidth = it
                    }
                }
                attributes.maxHeight?.let {
                    if (maxHeight != it) {
                        maxHeight = it
                    }
                }
                attributes.cropToPadding?.let {
                    if (cropToPadding != it) {
                        cropToPadding = it
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.tint?.let {
                    val immutableTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (imageTintList != immutableTint) {
                        setImageTintList(immutableTint)
                    }
                }
                attributes.tintMode?.let {
                    val immutableTintMode = it.toPorterDuffMode()
                    if (imageTintMode != immutableTintMode) {
                        setImageTintMode(immutableTintMode)
                    }
                }
            }
        }
    }
}
