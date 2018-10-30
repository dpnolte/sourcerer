package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.laidpack.sourcerer.generated.appcompat.AppCompatButtonFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class MaterialButtonFactory<TView : MaterialButton, TAttributes : MaterialButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = MaterialButton(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is MaterialButton) {
            view.apply {
                attributes.icon?.let {
                    val localIcon = ContextCompat.getDrawable(context, it) as Drawable
                    if (icon != localIcon) {
                        icon = localIcon
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
                    val localIconGravity = it.value
                    if (iconGravity != localIconGravity) {
                        iconGravity = localIconGravity
                    }
                }
                attributes.iconTint?.let {
                    val localIconTint = ColorStateList.valueOf(it)
                    if (iconTint != localIconTint) {
                        setIconTint(localIconTint)
                    }
                }
                attributes.iconTintMode?.let {
                    val localIconTintMode = it.toPorterDuffMode()
                    if (iconTintMode != localIconTintMode) {
                        iconTintMode = localIconTintMode
                    }
                }
                attributes.strokeColor?.let {
                    val localStrokeColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (strokeColor != localStrokeColor) {
                        setStrokeColor(localStrokeColor)
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
                    val localRippleColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (rippleColor != localRippleColor) {
                        setRippleColor(localRippleColor)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "materialButton"

        inline operator fun <reified TView : MaterialButton, reified TAttributes : MaterialButtonAttributes> invoke() = MaterialButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
