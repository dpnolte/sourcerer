package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewStub
import java.lang.Class
import kotlin.String

open class ViewStubFactory<TAttributes : ViewStubAttributes>(attributesType: Class<TAttributes>) : ViewFactory<ViewStub, TAttributes>(ViewStub::class.java, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ViewStub(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewStub) {
            view.apply {
                attributes.id?.let {
                    if (id != it) {
                        id = it
                    }
                }
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
        const val elementType: String = "viewStub"

        inline operator fun <reified TAttributes : ViewStubAttributes> invoke() = ViewStubFactory(TAttributes::class.java)
    }
}
