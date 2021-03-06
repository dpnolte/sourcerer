package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.RowHeaderView
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.TextViewFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = RowHeaderViewFactory.elementType,
        attributesClazz = RowHeaderViewAttributes::class
)
open class RowHeaderViewFactory<TAttributes : RowHeaderViewAttributes>(attributesType: Class<TAttributes>) : TextViewFactory<RowHeaderView, TAttributes>(RowHeaderView::class.java, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = RowHeaderView(context)

    companion object {
        const val elementType: String = "rowHeaderView"

        inline operator fun <reified TAttributes : RowHeaderViewAttributes> invoke() = RowHeaderViewFactory(TAttributes::class.java)
    }
}
