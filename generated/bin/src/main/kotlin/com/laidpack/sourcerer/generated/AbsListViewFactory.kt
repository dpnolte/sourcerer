package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AbsListView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class AbsListViewFactory<TView : AbsListView, TAttributes : AbsListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "absListView"

    override fun createInstance(context: Context): View {
        // // AbsListView is abstract
        return super.createInstance(context)
    }

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AbsListView) {
            view.init {
                if (attributes.listSelector.hasColor || attributes.listSelector.hasReference) {
                    val immutableListSelector = when {
                        attributes.listSelector.hasColor -> ColorDrawable(attributes.listSelector.color)
                        else -> ContextCompat.getDrawable(context, attributes.listSelector.reference) as Drawable
                    }
                    if (selector != immutableListSelector) {
                        selector = immutableListSelector
                    }
                }
                attributes.stackFromBottom?.let {
                    if (isStackFromBottom != it) {
                        isStackFromBottom = it
                    }
                }
                attributes.scrollingCache?.let {
                    if (isScrollingCacheEnabled != it) {
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
                    if (isFastScrollEnabled != it) {
                        isFastScrollEnabled = it
                    }
                }
                attributes.smoothScrollbar?.let {
                    if (isSmoothScrollbarEnabled != it) {
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
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(AbsListViewFactory<AbsListView, AbsListViewAttributes>())
        }

        inline operator fun <reified TView : AbsListView, reified TAttributes : AbsListViewAttributes> invoke() = AbsListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
