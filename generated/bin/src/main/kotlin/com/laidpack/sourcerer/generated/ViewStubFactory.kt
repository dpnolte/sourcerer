package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewStub
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ViewStubFactory<TView : ViewStub, TAttributes : ViewStubAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "viewStub"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ViewStub(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.layout?.let {
                if (layoutResource != it) {
                    layoutResource = it
                }
            }
            attributes.inflatedId?.let {
                if (inflatedId != it) {
                    inflatedId = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ViewStubFactory<ViewStub, ViewStubAttributes>())
        }

        inline operator fun <reified TView : ViewStub, reified TAttributes : ViewStubAttributes> invoke() = ViewStubFactory(TView::class.java, TAttributes::class.java)
    }
}
