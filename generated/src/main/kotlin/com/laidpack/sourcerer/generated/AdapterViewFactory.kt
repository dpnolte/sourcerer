package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AdapterView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AdapterViewFactory<TView : AdapterView<*>, TAttributes : AdapterViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "adapterView"

    override fun createInstance(context: Context): View {
        // // AdapterView is abstract
        return super.createInstance(context)
    }

    companion object {
        init {
            InflaterComponent.addFactory(AdapterViewFactory<AdapterView<*>, AdapterViewAttributes>())
        }

        inline operator fun <reified TView : AdapterView<*>, reified TAttributes : AdapterViewAttributes> invoke() = AdapterViewFactory(TView::class.java, TAttributes::class.java)
    }
}
