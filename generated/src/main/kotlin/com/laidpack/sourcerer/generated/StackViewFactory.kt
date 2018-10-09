package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.StackView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class StackViewFactory<TView : StackView, TAttributes : StackViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "stackView"

    override fun createInstance(context: Context): View = StackView(context)

    companion object {
        init {
            InflaterComponent.addFactory(StackViewFactory<StackView, StackViewAttributes>())
        }

        inline operator fun <reified TView : StackView, reified TAttributes : StackViewAttributes> invoke() = StackViewFactory(TView::class.java, TAttributes::class.java)
    }
}
