package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AbsListView
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = AbsListViewFactory.elementType,
        attributesClazz = AbsListViewAttributes::class,
        layoutParamAttributesClazz = AbsListViewLayoutParamsAttributes::class
)
open class AbsListViewFactory<TView : AbsListView, TAttributes : AbsListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AbsListView) {
            view.apply {
                if (attributes.listSelector.hasColor || attributes.listSelector.hasReference) {
                    val localListSelector = when {
                        attributes.listSelector.hasColor -> ColorDrawable(attributes.listSelector.color)
                        else -> ContextCompat.getDrawable(context, attributes.listSelector.reference) as Drawable
                    }
                    if (selector != localListSelector) {
                        selector = localListSelector
                    }
                }
                attributes.drawSelectorOnTop?.let {
                    setDrawSelectorOnTop(it)
                }
                attributes.stackFromBottom?.let {
                    if (isStackFromBottom != it) {
                        isStackFromBottom = it
                    }
                }
                attributes.scrollingCache?.let {
                    if (isEnabled != it) {
                        isScrollingCacheEnabled = it
                    }
                }
                attributes.textFilterEnabled?.let {
                    if (isTextFilterEnabled != it) {
                        isTextFilterEnabled = it
                    }
                }
                attributes.transcriptMode?.let {
                    if (transcriptMode != it.value) {
                        transcriptMode = it.value
                    }
                }
                attributes.cacheColorHint?.let {
                    if (cacheColorHint != it) {
                        cacheColorHint = it
                    }
                }
                attributes.fastScrollEnabled?.let {
                    if (isEnabled != it) {
                        isFastScrollEnabled = it
                    }
                }
                attributes.smoothScrollbar?.let {
                    if (isEnabled != it) {
                        isSmoothScrollbarEnabled = it
                    }
                }
                attributes.choiceMode?.let {
                    if (choiceMode != it.value) {
                        choiceMode = it.value
                    }
                }
                attributes.fastScrollAlwaysVisible?.let {
                    if (isFastScrollAlwaysVisible != it) {
                        isFastScrollAlwaysVisible = it
                    }
                }
                attributes.fastScrollStyle?.let {
                    setFastScrollStyle(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "absListView"

        inline operator fun <reified TView : AbsListView, reified TAttributes : AbsListViewAttributes> invoke() = AbsListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
