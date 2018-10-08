package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.QuickContactBadge
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class QuickContactBadgeFactory<TView : QuickContactBadge, TAttributes : QuickContactBadgeAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "quickContactBadge"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = QuickContactBadge(context)

    companion object {
        init {
            InflaterComponent.addFactory(QuickContactBadgeFactory<QuickContactBadge, QuickContactBadgeAttributes>())
        }

        inline operator fun <reified TView : QuickContactBadge, reified TAttributes : QuickContactBadgeAttributes> invoke() = QuickContactBadgeFactory(TView::class.java, TAttributes::class.java)
    }
}
