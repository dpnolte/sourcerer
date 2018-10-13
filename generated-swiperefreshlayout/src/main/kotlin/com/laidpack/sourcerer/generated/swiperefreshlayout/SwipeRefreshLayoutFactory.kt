package com.laidpack.sourcerer.generated.swiperefreshlayout

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class SwipeRefreshLayoutFactory<TView : SwipeRefreshLayout, TAttributes : SwipeRefreshLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SwipeRefreshLayout(context)

    companion object {
        const val elementType: String = "swipeRefreshLayout"

        inline operator fun <reified TView : SwipeRefreshLayout, reified TAttributes : SwipeRefreshLayoutAttributes> invoke() = SwipeRefreshLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
