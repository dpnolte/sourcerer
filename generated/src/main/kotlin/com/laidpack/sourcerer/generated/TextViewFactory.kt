package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import com.laidpack.sourcerer.services.api.toTruncateAt
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = TextViewFactory.elementType,
        attributesClazz = TextViewAttributes::class
)
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
                attributes.textSize?.let {
                    val localTextSize = it.toFloat()
                    if (textSize != localTextSize) {
                        textSize = localTextSize
                    }
                }
                attributes.textScaleX?.let {
                    if (textScaleX != it) {
                        textScaleX = it
                    }
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
                attributes.View_clickable?.let {
                    if (isClickable != it) {
                        isClickable = it
                    }
                }
                attributes.View_longClickable?.let {
                    if (isLongClickable != it) {
                        isLongClickable = it
                    }
                }
                if (attributes.drawableTop.hasColor || attributes.drawableTop.hasReference || attributes.drawableLeft.hasColor || attributes.drawableLeft.hasReference || attributes.drawableBottom.hasColor || attributes.drawableBottom.hasReference || attributes.drawableRight.hasColor || attributes.drawableRight.hasReference) {
                    val localDrawableBottomReference = if (attributes.drawableBottom.hasReference) attributes.drawableBottom.reference else bottom
                    val localDrawableLeftReference = if (attributes.drawableLeft.hasReference) attributes.drawableLeft.reference else left
                    val localDrawableRightReference = if (attributes.drawableRight.hasReference) attributes.drawableRight.reference else right
                    val localDrawableTopReference = if (attributes.drawableTop.hasReference) attributes.drawableTop.reference else top
                    if (bottom != localDrawableBottomReference || left != localDrawableLeftReference || right != localDrawableRightReference || top != localDrawableTopReference) {
                        setCompoundDrawablesWithIntrinsicBounds(localDrawableLeftReference, localDrawableTopReference, localDrawableRightReference, localDrawableBottomReference)
                    }
                    val localDrawableLeftColor = ColorDrawable(attributes.drawableLeft.color)
                    val localDrawableTopColor = ColorDrawable(attributes.drawableTop.color)
                    val localDrawableRightColor = ColorDrawable(attributes.drawableRight.color)
                    val localDrawableBottomColor = ColorDrawable(attributes.drawableBottom.color)
                    setCompoundDrawablesWithIntrinsicBounds(localDrawableLeftColor, localDrawableTopColor, localDrawableRightColor, localDrawableBottomColor)
                }
                attributes.inputType?.let {
                    val localInputType = it.value
                    if (inputType != localInputType) {
                        inputType = localInputType
                    }
                }
                attributes.imeOptions?.let {
                    val localImeOptions = it.value
                    if (imeOptions != localImeOptions) {
                        imeOptions = localImeOptions
                    }
                }
                attributes.privateImeOptions?.let {
                    if (privateImeOptions != it) {
                        privateImeOptions = it
                    }
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
                if (Build.VERSION.SDK_INT >= 21) {
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
                    attributes.justificationMode?.let {
                        if (justificationMode != it.value) {
                            justificationMode = it.value
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 28) {
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
