package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RecyclerViewFactory<TView : RecyclerView, TAttributes : RecyclerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "recyclerView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = RecyclerView(context)

    companion object {
        init {
            InflaterComponent.addFactory(RecyclerViewFactory<RecyclerView, RecyclerViewAttributes>())
        }

        inline operator fun <reified TView : RecyclerView, reified TAttributes : RecyclerViewAttributes> invoke() = RecyclerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
