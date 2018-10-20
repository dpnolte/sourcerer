package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import com.laidpack.sourcerer.services.api.toScaleType
import java.lang.Class
import kotlin.String

open class ImageViewFactory<TView : ImageView, TAttributes : ImageViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ImageView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ImageView) {
            view.apply {
                if (attributes.src.hasColor || attributes.src.hasReference) {
                    val localSrc = when {
                        attributes.src.hasColor -> ColorDrawable(attributes.src.color)
                        else -> ContextCompat.getDrawable(context, attributes.src.reference) as Drawable
                    }
                    setImageDrawable(localSrc)
                }
                attributes.scaleType?.let {
                    val localScaleType = it.value.toScaleType()
                    if (scaleType != localScaleType) {
                        scaleType = localScaleType
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
                        imageAlpha = it
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.tint?.let {
                        val localTint = ColorStateList.valueOf(it)
                        if (imageTintList != localTint) {
                            imageTintList = localTint
                        }
                    }
                    attributes.tintMode?.let {
                        val localTintMode = it.toPorterDuffMode()
                        if (imageTintMode != localTintMode) {
                            imageTintMode = localTintMode
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "imageView"

        inline operator fun <reified TView : ImageView, reified TAttributes : ImageViewAttributes> invoke() = ImageViewFactory(TView::class.java, TAttributes::class.java)
    }
}
