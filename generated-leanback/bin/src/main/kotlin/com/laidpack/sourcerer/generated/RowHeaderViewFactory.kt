package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.RowHeaderView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RowHeaderViewFactory<TView : RowHeaderView, TAttributes : RowHeaderViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "rowHeaderView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = RowHeaderView(context)

    companion object {
        init {
            InflaterComponent.addFactory(RowHeaderViewFactory<RowHeaderView, RowHeaderViewAttributes>())
        }

        inline operator fun <reified TView : RowHeaderView, reified TAttributes : RowHeaderViewAttributes> invoke() = RowHeaderViewFactory(TView::class.java, TAttributes::class.java)
    }
}
