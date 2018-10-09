package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseGridView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BaseGridViewFactory<TView : BaseGridView, TAttributes : BaseGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "baseGridView"

    override fun createInstance(context: Context): View {
        // // BaseGridView is abstract
        return super.createInstance(context)
    }

    companion object {
        init {
            InflaterComponent.addFactory(BaseGridViewFactory<BaseGridView, BaseGridViewAttributes>())
        }

        inline operator fun <reified TView : BaseGridView, reified TAttributes : BaseGridViewAttributes> invoke() = BaseGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
