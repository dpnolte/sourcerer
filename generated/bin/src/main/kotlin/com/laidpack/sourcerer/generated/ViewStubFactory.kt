package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewStub
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ViewStubFactory<TAttributes : ViewStubAttributes>(attributesType: Class<TAttributes>) : ViewFactory<ViewStub, TAttributes>(ViewStub::class.java, attributesType) {
    override val elementName: String = "viewStub"

    override fun createInstance(context: Context): View = ViewStub(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewStub) {
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
    }

    companion object {
        init {
            InflaterComponent.addFactory(ViewStubFactory<ViewStubAttributes>())
        }

        inline operator fun <reified TAttributes : ViewStubAttributes> invoke() = ViewStubFactory(TAttributes::class.java)
    }
}
