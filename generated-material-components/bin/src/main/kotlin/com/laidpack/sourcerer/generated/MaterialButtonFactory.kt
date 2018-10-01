package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class MaterialButtonFactory<TView : MaterialButton, TAttributes : MaterialButtonAttributes> : ButtonFactory<TView, TAttributes>() {
    override val elementName: String = "materialButton"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = MaterialButton(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as MaterialButton
        view.init {
            attributes.icon?.let {
                val immutableIcon = ContextCompat.getDrawable(context, it) as Drawable
                if (icon != immutableIcon) {
                    icon = immutableIcon
                }
            }
            attributes.iconSize?.let {
                if (iconSize != it) {
                    iconSize = it
                }
            }
            attributes.iconPadding?.let {
                if (iconPadding != it) {
                    iconPadding = it
                }
            }
            attributes.iconGravity?.let {
                if (iconGravity != it.value) {
                    iconGravity = it.value
                }
            }
            attributes.iconTint?.let {
                val immutableIconTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (iconTint != immutableIconTint) {
                    setIconTint(immutableIconTint)
                }
            }
            attributes.iconTintMode?.let {
                val immutableIconTintMode = it.toPorterDuffMode()
                if (iconTintMode != immutableIconTintMode) {
                    iconTintMode = immutableIconTintMode
                }
            }
            attributes.strokeColor?.let {
                val immutableStrokeColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (strokeColor != immutableStrokeColor) {
                    setStrokeColor(immutableStrokeColor)
                }
            }
            attributes.strokeWidth?.let {
                if (strokeWidth != it) {
                    strokeWidth = it
                }
            }
            attributes.cornerRadius?.let {
                if (cornerRadius != it) {
                    cornerRadius = it
                }
            }
            attributes.rippleColor?.let {
                val immutableRippleColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (rippleColor != immutableRippleColor) {
                    setRippleColor(immutableRippleColor)
                }
            }
        }
    }
}
