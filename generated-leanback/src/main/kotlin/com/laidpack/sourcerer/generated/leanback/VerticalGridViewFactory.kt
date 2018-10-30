package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.VerticalGridView
import java.lang.Class
import kotlin.String

open class VerticalGridViewFactory<TView : VerticalGridView, TAttributes : VerticalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseGridViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = VerticalGridView(context)

    companion object {
        const val elementType: String = "verticalGridView"

        inline operator fun <reified TView : VerticalGridView, reified TAttributes : VerticalGridViewAttributes> invoke() = VerticalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
