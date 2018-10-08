package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseGridView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class BaseGridViewFactory<TView : BaseGridView, TAttributes : BaseGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RecyclerViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "baseGridView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View {
        throw IllegalStateException("androidx.leanback.widget.BaseGridView is abstract and cannot be instantiated")
    }

    companion object {
        init {
            InflaterComponent.addFactory(BaseGridViewFactory<BaseGridView, BaseGridViewAttributes>())
        }

        inline operator fun <reified TView : BaseGridView, reified TAttributes : BaseGridViewAttributes> invoke() = BaseGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
