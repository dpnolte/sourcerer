package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import com.laidpack.sourcerer.service.api.toScaleType
import java.lang.Class
import kotlin.String

open class ImageViewFactory<TView : ImageView, TAttributes : ImageViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "imageView"

    override fun createInstance(context: Context): View = ImageView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ImageView) {
            view.init {
                if (attributes.src.hasColor || attributes.src.hasReference) {
                    val immutableSrc = when {
                        attributes.src.hasColor -> ColorDrawable(attributes.src.color)
                        else -> ContextCompat.getDrawable(context, attributes.src.reference) as Drawable
                    }
                    if (drawable != immutableSrc) {
                        setImageDrawable(immutableSrc)
                    }
                }
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
                    attributes.drawableAlpha?.let {
                        if (imageAlpha != it) {
                            imageAlpha = it
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.tint?.let {
                        val immutableTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                        if (imageTintList != immutableTint) {
                            imageTintList = immutableTint
                        }
                    }
                    attributes.tintMode?.let {
                        val immutableTintMode = it.toPorterDuffMode()
                        if (imageTintMode != immutableTintMode) {
                            imageTintMode = immutableTintMode
                        }
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ImageViewFactory<ImageView, ImageViewAttributes>())
        }

        inline operator fun <reified TView : ImageView, reified TAttributes : ImageViewAttributes> invoke() = ImageViewFactory(TView::class.java, TAttributes::class.java)
    }
}
