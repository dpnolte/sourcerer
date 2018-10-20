package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RadioGroup
import java.lang.Class
import kotlin.String

open class RadioGroupFactory<TView : RadioGroup, TAttributes : RadioGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = RadioGroup(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is RadioGroup) {
            view.apply {
                attributes.orientation?.let {
                    if (orientation != it.value) {
                        orientation = it.value
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "radioGroup"

        inline operator fun <reified TView : RadioGroup, reified TAttributes : RadioGroupAttributes> invoke() = RadioGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
