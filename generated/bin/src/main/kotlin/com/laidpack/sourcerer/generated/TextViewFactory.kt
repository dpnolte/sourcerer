package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import com.laidpack.sourcerer.service.api.toTruncateAt
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TextViewFactory<TView : TextView, TAttributes : TextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TextView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
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
            attributes.textColor?.let {
                if (textColors.defaultColor != it) {
                    setTextColor(it)
                }
            }
            attributes.textColorHighlight?.let {
                if (highlightColor != it) {
                    highlightColor = it
                }
            }
            attributes.textColorHint?.let {
                if (hintTextColors.defaultColor != it) {
                    setHintTextColor(it)
                }
            }
            attributes.textSize?.let {
                if (textSize != it) {
                    textSize = it
                }
            }
            attributes.textScaleX?.let {
                if (textScaleX != it) {
                    textScaleX = it
                }
            }
            attributes.textColorLink?.let {
                if (linkTextColors.defaultColor != it) {
                    setLinkTextColor(it)
                }
            }
            attributes.cursorVisible?.let {
                if (isCursorVisible != it) {
                    isCursorVisible = it
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
                if (lineCount != it) {
                    setLines(it)
                }
            }
            attributes.height?.let {
                if (height != it) {
                    height = it
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
            attributes.width?.let {
                if (width != it) {
                    width = it
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
            attributes.gravity?.let {
                if (gravity != it) {
                    gravity = it
                }
            }
            attributes.enabled?.let {
                if (isEnabled != it) {
                    isEnabled = it
                }
            }
            attributes.includeFontPadding?.let {
                if (includeFontPadding != it) {
                    includeFontPadding = it
                }
            }
            if (attributes.shadowColor != null || attributes.shadowDx != null || attributes.shadowDy != null || attributes.shadowRadius != null) {
                val immutableShadowColor = attributes.shadowColor ?: shadowColor
                val immutableShadowDx = attributes.shadowDx ?: shadowDx
                val immutableShadowDy = attributes.shadowDy ?: shadowDy
                val immutableShadowRadius = attributes.shadowRadius ?: shadowRadius
                if (shadowColor != immutableShadowColor || shadowDx != immutableShadowDx || shadowDy != immutableShadowDy || shadowRadius != immutableShadowRadius) {
                    setShadowLayer(immutableShadowRadius, immutableShadowDx, immutableShadowDy, immutableShadowColor)
                }
            }
            attributes.autoLink?.let {
                if (autoLinkMask != it) {
                    autoLinkMask = it
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
                val immutableEllipsize = it.toTruncateAt()
                if (ellipsize != immutableEllipsize) {
                    ellipsize = immutableEllipsize
                }
            }
            if (attributes.drawableTop.hasColor || attributes.drawableTop.hasReference || attributes.drawableBottom.hasColor || attributes.drawableBottom.hasReference || attributes.drawableLeft.hasColor || attributes.drawableLeft.hasReference || attributes.drawableRight.hasColor || attributes.drawableRight.hasReference) {
                val immutableDrawableTop = when {
                    attributes.drawableTop.hasColor -> ColorDrawable(attributes.drawableTop.color)
                    attributes.drawableTop.hasReference -> ContextCompat.getDrawable(context, attributes.drawableTop.reference) as Drawable
                    else -> compoundDrawablesRelative[1]
                }
                val immutableDrawableBottom = when {
                    attributes.drawableBottom.hasColor -> ColorDrawable(attributes.drawableBottom.color)
                    attributes.drawableBottom.hasReference -> ContextCompat.getDrawable(context, attributes.drawableBottom.reference) as Drawable
                    else -> compoundDrawablesRelative[3]
                }
                val immutableDrawableLeft = when {
                    attributes.drawableLeft.hasColor -> ColorDrawable(attributes.drawableLeft.color)
                    attributes.drawableLeft.hasReference -> ContextCompat.getDrawable(context, attributes.drawableLeft.reference) as Drawable
                    else -> compoundDrawables[0]
                }
                val immutableDrawableRight = when {
                    attributes.drawableRight.hasColor -> ColorDrawable(attributes.drawableRight.color)
                    attributes.drawableRight.hasReference -> ContextCompat.getDrawable(context, attributes.drawableRight.reference) as Drawable
                    else -> compoundDrawables[2]
                }
                if (compoundDrawablesRelative[1] != immutableDrawableTop || compoundDrawablesRelative[3] != immutableDrawableBottom || compoundDrawables[0] != immutableDrawableLeft || compoundDrawables[2] != immutableDrawableRight) {
                    setCompoundDrawablesWithIntrinsicBounds(immutableDrawableLeft, immutableDrawableTop, immutableDrawableRight, immutableDrawableBottom)
                }
            }
            attributes.drawablePadding?.let {
                if (compoundDrawablePadding != it) {
                    compoundDrawablePadding = it
                }
            }
            attributes.drawableTint?.let {
                val immutableDrawableTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (compoundDrawableTintList != immutableDrawableTint) {
                    setCompoundDrawableTintList(immutableDrawableTint)
                }
            }
            attributes.drawableTintMode?.let {
                val immutableDrawableTintMode = it.value.toPorterDuffMode()
                if (compoundDrawableTintMode != immutableDrawableTintMode) {
                    setCompoundDrawableTintMode(immutableDrawableTintMode)
                }
            }
            attributes.lineHeight?.let {
                if (lineHeight != it) {
                    lineHeight = it
                }
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
            attributes.marqueeRepeatLimit?.let {
                if (marqueeRepeatLimit != it.value) {
                    marqueeRepeatLimit = it.value
                }
            }
            attributes.inputType?.let {
                if (inputType != it) {
                    inputType = it
                }
            }
            attributes.imeOptions?.let {
                if (imeOptions != it) {
                    imeOptions = it
                }
            }
            attributes.privateImeOptions?.let {
                if (privateImeOptions != it) {
                    privateImeOptions = it
                }
            }
            attributes.textAllCaps?.let {
                if (isAllCaps != it) {
                    isAllCaps = it
                }
            }
            attributes.elegantTextHeight?.let {
                if (isElegantTextHeight != it) {
                    isElegantTextHeight = it
                }
            }
            attributes.fallbackLineSpacing?.let {
                if (isFallbackLineSpacing != it) {
                    isFallbackLineSpacing = it
                }
            }
            attributes.letterSpacing?.let {
                if (letterSpacing != it) {
                    letterSpacing = it
                }
            }
            attributes.fontFeatureSettings?.let {
                if (fontFeatureSettings != it) {
                    fontFeatureSettings = it
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
            attributes.autoSizeTextType?.let {
                if (autoSizeTextType != it.value) {
                    setAutoSizeTextTypeWithDefaults(it.value)
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(TextViewFactory<TextView, TextViewAttributes>())
        }

        inline operator fun <reified TView : TextView, reified TAttributes : TextViewAttributes> invoke() = TextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
