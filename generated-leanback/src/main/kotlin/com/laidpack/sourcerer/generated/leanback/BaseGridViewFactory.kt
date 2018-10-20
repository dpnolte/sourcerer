package com.laidpack.sourcerer.generated.leanback

import androidx.leanback.widget.BaseGridView
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class BaseGridViewFactory<TView : BaseGridView, TAttributes : BaseGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    companion object {
        const val elementType: String = "baseGridView"

        inline operator fun <reified TView : BaseGridView, reified TAttributes : BaseGridViewAttributes> invoke() = BaseGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
