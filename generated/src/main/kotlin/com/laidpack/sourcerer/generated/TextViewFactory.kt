package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import com.laidpack.sourcerer.services.api.toTruncateAt
import java.lang.Class
import kotlin.String

open class TextViewFactory<TView : TextView, TAttributes : TextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TextView) {
            view.apply {
                attributes.text?.let {
                    if (text != it) {
                        text = it
                    }
                }
                attributes.hint?.let {
                    if (hint != it) {
                        hint = it
                    }
                }
                if (attributes.textColor.hasColor || attributes.textColor.hasReference) {
                    val localTextColor = when {
                        attributes.textColor.hasColor -> ColorStateList.valueOf(attributes.textColor.color)
                        else -> ResourcesCompat.getColorStateList(context.resources, attributes.textColor.reference, null)
                    }
                    if (textColors != localTextColor) {
                        setTextColor(localTextColor)
                    }
                }
                if (attributes.textColorHint.hasColor || attributes.textColorHint.hasReference) {
                    val localTextColorHint = when {
                        attributes.textColorHint.hasColor -> ColorStateList.valueOf(attributes.textColorHint.color)
                        else -> ResourcesCompat.getColorStateList(context.resources, attributes.textColorHint.reference, null)
                    }
                    if (hintTextColors != localTextColorHint) {
                        setHintTextColor(localTextColorHint)
                    }
                }
                attributes.textScaleX?.let {
                    textScaleX = it
                }
                if (attributes.textColorLink.hasColor || attributes.textColorLink.hasReference) {
                    val localTextColorLink = when {
                        attributes.textColorLink.hasColor -> ColorStateList.valueOf(attributes.textColorLink.color)
                        else -> ResourcesCompat.getColorStateList(context.resources, attributes.textColorLink.reference, null)
                    }
                    if (linkTextColors != localTextColorLink) {
                        setLinkTextColor(localTextColorLink)
                    }
                }
                attributes.height?.let {
                    if (height != it) {
                        height = it
                    }
                }
                attributes.width?.let {
                    if (width != it) {
                        width = it
                    }
                }
                attributes.gravity?.let {
                    val localGravity = it.value
                    if (gravity != localGravity) {
                        gravity = localGravity
                    }
                }
                attributes.enabled?.let {
                    if (isEnabled != it) {
                        isEnabled = it
                    }
                }
                attributes.autoLink?.let {
                    val localAutoLink = it.value
                    if (autoLinkMask != localAutoLink) {
                        autoLinkMask = localAutoLink
                    }
                }
                attributes.linksClickable?.let {
                    if (linksClickable != it) {
                        linksClickable = it
                    }
                }
                attributes.freezesText?.let {
                    if (freezesText != it) {
                        freezesText = it
                    }
                }
                attributes.ellipsize?.let {
                    val localEllipsize = it.value.toTruncateAt()
                    if (ellipsize != localEllipsize) {
                        ellipsize = localEllipsize
                    }
                }
                attributes.drawablePadding?.let {
                    if (compoundDrawablePadding != it) {
                        compoundDrawablePadding = it
                    }
                }
                attributes.privateImeOptions?.let {
                    privateImeOptions = it
                }
                attributes.editorExtras?.let {
                    setInputExtras(it)
                }
                attributes.textIsSelectable?.let {
                    setTextIsSelectable(it)
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.textColorHighlight?.let {
                        if (highlightColor != it) {
                            highlightColor = it
                        }
                    }
                    attributes.maxLines?.let {
                        if (maxLines != it) {
                            maxLines = it
                        }
                    }
                    attributes.maxHeight?.let {
                        if (maxHeight != it) {
                            maxHeight = it
                        }
                    }
                    attributes.lines?.let {
                        if (maxLines != it) {
                            setLines(it)
                        }
                    }
                    attributes.minLines?.let {
                        if (minLines != it) {
                            minLines = it
                        }
                    }
                    attributes.minHeight?.let {
                        if (minHeight != it) {
                            minHeight = it
                        }
                    }
                    attributes.maxEms?.let {
                        if (maxEms != it) {
                            maxEms = it
                        }
                    }
                    attributes.maxWidth?.let {
                        if (maxWidth != it) {
                            maxWidth = it
                        }
                    }
                    attributes.ems?.let {
                        if (maxEms != it) {
                            setEms(it)
                        }
                    }
                    attributes.minEms?.let {
                        if (minEms != it) {
                            minEms = it
                        }
                    }
                    attributes.minWidth?.let {
                        if (minWidth != it) {
                            minWidth = it
                        }
                    }
                    attributes.includeFontPadding?.let {
                        if (includeFontPadding != it) {
                            includeFontPadding = it
                        }
                    }
                    if (attributes.shadowColor != null || attributes.shadowDx != null || attributes.shadowDy != null || attributes.shadowRadius != null) {
                        val localShadowColor = attributes.shadowColor ?: shadowColor
                        val localShadowDx = attributes.shadowDx ?: shadowDx
                        val localShadowDy = attributes.shadowDy ?: shadowDy
                        val localShadowRadius = attributes.shadowRadius ?: shadowRadius
                        if (shadowColor != localShadowColor || shadowDx != localShadowDx || shadowDy != localShadowDy || shadowRadius != localShadowRadius) {
                            setShadowLayer(localShadowRadius, localShadowDx, localShadowDy, localShadowColor)
                        }
                    }
                    if (attributes.marqueeRepeatLimit.hasInteger || attributes.marqueeRepeatLimit.hasEnum) {
                        val localMarqueeRepeatLimit = when {
                            attributes.marqueeRepeatLimit.hasInteger -> attributes.marqueeRepeatLimit.integer
                            else -> attributes.marqueeRepeatLimit.enum
                        }
                        if (marqueeRepeatLimit != localMarqueeRepeatLimit) {
                            marqueeRepeatLimit = localMarqueeRepeatLimit
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    if (attributes.drawableTop.hasColor || attributes.drawableTop.hasReference || attributes.drawableLeft.hasColor || attributes.drawableLeft.hasReference || attributes.drawableRight.hasColor || attributes.drawableRight.hasReference || attributes.drawableBottom.hasColor || attributes.drawableBottom.hasReference) {
                        val localDrawableTop = when {
                            attributes.drawableTop.hasColor -> ColorDrawable(attributes.drawableTop.color)
                            attributes.drawableTop.hasReference -> ContextCompat.getDrawable(context, attributes.drawableTop.reference) as Drawable
                            else -> compoundDrawablesRelative[1]
                        }
                        val localDrawableLeft = when {
                            attributes.drawableLeft.hasColor -> ColorDrawable(attributes.drawableLeft.color)
                            attributes.drawableLeft.hasReference -> ContextCompat.getDrawable(context, attributes.drawableLeft.reference) as Drawable
                            else -> compoundDrawables[0]
                        }
                        val localDrawableRight = when {
                            attributes.drawableRight.hasColor -> ColorDrawable(attributes.drawableRight.color)
                            attributes.drawableRight.hasReference -> ContextCompat.getDrawable(context, attributes.drawableRight.reference) as Drawable
                            else -> compoundDrawables[2]
                        }
                        val localDrawableBottom = when {
                            attributes.drawableBottom.hasColor -> ColorDrawable(attributes.drawableBottom.color)
                            attributes.drawableBottom.hasReference -> ContextCompat.getDrawable(context, attributes.drawableBottom.reference) as Drawable
                            else -> compoundDrawablesRelative[3]
                        }
                        if (compoundDrawablesRelative[1] != localDrawableTop || compoundDrawables[0] != localDrawableLeft || compoundDrawables[2] != localDrawableRight || compoundDrawablesRelative[3] != localDrawableBottom) {
                            setCompoundDrawablesWithIntrinsicBounds(localDrawableLeft, localDrawableTop, localDrawableRight, localDrawableBottom)
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    attributes.drawableTint?.let {
                        val localDrawableTint = ColorStateList.valueOf(it)
                        if (compoundDrawableTintList != localDrawableTint) {
                            setCompoundDrawableTintList(localDrawableTint)
                        }
                    }
                    attributes.drawableTintMode?.let {
                        val localDrawableTintMode = it.value.toPorterDuffMode()
                        if (compoundDrawableTintMode != localDrawableTintMode) {
                            setCompoundDrawableTintMode(localDrawableTintMode)
                        }
                    }
                    attributes.breakStrategy?.let {
                        if (breakStrategy != it.value) {
                            breakStrategy = it.value
                        }
                    }
                    attributes.hyphenationFrequency?.let {
                        if (hyphenationFrequency != it.value) {
                            hyphenationFrequency = it.value
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    attributes.autoSizeTextType?.let {
                        if (autoSizeTextType != it.value) {
                            setAutoSizeTextTypeWithDefaults(it.value)
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    attributes.lineHeight?.let {
                        lineHeight = it
                    }
                    attributes.firstBaselineToTopHeight?.let {
                        if (firstBaselineToTopHeight != it) {
                            firstBaselineToTopHeight = it
                        }
                    }
                    attributes.lastBaselineToBottomHeight?.let {
                        if (lastBaselineToBottomHeight != it) {
                            lastBaselineToBottomHeight = it
                        }
                    }
                    attributes.fallbackLineSpacing?.let {
                        if (isEnabled != it) {
                            isFallbackLineSpacing = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "textView"

        inline operator fun <reified TView : TextView, reified TAttributes : TextViewAttributes> invoke() = TextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
