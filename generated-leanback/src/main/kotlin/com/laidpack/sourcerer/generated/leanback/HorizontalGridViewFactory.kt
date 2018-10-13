package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.HorizontalGridView
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class HorizontalGridViewFactory<TView : HorizontalGridView, TAttributes : HorizontalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = HorizontalGridView(context)

    companion object {
        const val elementType: String = "horizontalGridView"

        inline operator fun <reified TView : HorizontalGridView, reified TAttributes : HorizontalGridViewAttributes> invoke() = HorizontalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
