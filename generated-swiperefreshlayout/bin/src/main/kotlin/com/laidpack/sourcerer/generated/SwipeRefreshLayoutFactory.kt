package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SwipeRefreshLayoutFactory<TView : SwipeRefreshLayout, TAttributes : SwipeRefreshLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "swipeRefreshLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SwipeRefreshLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(SwipeRefreshLayoutFactory<SwipeRefreshLayout, SwipeRefreshLayoutAttributes>())
        }

        inline operator fun <reified TView : SwipeRefreshLayout, reified TAttributes : SwipeRefreshLayoutAttributes> invoke() = SwipeRefreshLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
