package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ListView
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ListViewFactory.elementType,
        attributesClazz = ListViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ListViewFactory<TView : ListView, TAttributes : ListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ListView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ListView) {
            view.apply {
                if (attributes.divider.hasColor || attributes.divider.hasReference) {
                    val localDivider = when {
                        attributes.divider.hasColor -> ColorDrawable(attributes.divider.color)
                        else -> ContextCompat.getDrawable(context, attributes.divider.reference) as Drawable
                    }
                    if (divider != localDivider) {
                        divider = localDivider
                    }
                }
                attributes.dividerHeight?.let {
                    if (height != it) {
                        dividerHeight = it
                    }
                }
                if (attributes.overScrollHeader.hasColor || attributes.overScrollHeader.hasReference) {
                    val localOverScrollHeader = when {
                        attributes.overScrollHeader.hasColor -> ColorDrawable(attributes.overScrollHeader.color)
                        else -> ContextCompat.getDrawable(context, attributes.overScrollHeader.reference) as Drawable
                    }
                    if (overscrollHeader != localOverScrollHeader) {
                        overscrollHeader = localOverScrollHeader
                    }
                }
                if (attributes.overScrollFooter.hasColor || attributes.overScrollFooter.hasReference) {
                    val localOverScrollFooter = when {
                        attributes.overScrollFooter.hasColor -> ColorDrawable(attributes.overScrollFooter.color)
                        else -> ContextCompat.getDrawable(context, attributes.overScrollFooter.reference) as Drawable
                    }
                    if (overscrollFooter != localOverScrollFooter) {
                        overscrollFooter = localOverScrollFooter
                    }
                }
                attributes.headerDividersEnabled?.let {
                    if (areHeaderDividersEnabled() != it) {
                        setHeaderDividersEnabled(it)
                    }
                }
                attributes.footerDividersEnabled?.let {
                    if (areFooterDividersEnabled() != it) {
                        setFooterDividersEnabled(it)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "listView"

        inline operator fun <reified TView : ListView, reified TAttributes : ListViewAttributes> invoke() = ListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
