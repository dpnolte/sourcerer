package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
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
                attributes.textColor?.let {
                    if (textColors.defaultColor != it) {
                        setTextColor(it)
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
                    if (gravity != it) {
                        gravity = it
                    }
                }
                attributes.enabled?.let {
                    if (isEnabled != it) {
                        isEnabled = it
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
                attributes.drawablePadding?.let {
                    if (compoundDrawablePadding != it) {
                        compoundDrawablePadding = it
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
                        val immutableShadowColor = attributes.shadowColor ?: shadowColor
                        val immutableShadowDx = attributes.shadowDx ?: shadowDx
                        val immutableShadowDy = attributes.shadowDy ?: shadowDy
                        val immutableShadowRadius = attributes.shadowRadius ?: shadowRadius
                        if (shadowColor != immutableShadowColor || shadowDx != immutableShadowDx || shadowDy != immutableShadowDy || shadowRadius != immutableShadowRadius) {
                            setShadowLayer(immutableShadowRadius, immutableShadowDx, immutableShadowDy, immutableShadowColor)
                        }
                    }
                    attributes.marqueeRepeatLimit?.let {
                        if (marqueeRepeatLimit != it.value) {
                            marqueeRepeatLimit = it.value
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
                        if (isFallbackLineSpacing != it) {
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
