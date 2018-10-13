package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.QuickContactBadge
import java.lang.Class
import kotlin.String

open class QuickContactBadgeFactory<TView : QuickContactBadge, TAttributes : QuickContactBadgeAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = QuickContactBadge(context)

    companion object {
        const val elementType: String = "quickContactBadge"

        inline operator fun <reified TView : QuickContactBadge, reified TAttributes : QuickContactBadgeAttributes> invoke() = QuickContactBadgeFactory(TView::class.java, TAttributes::class.java)
    }
}
