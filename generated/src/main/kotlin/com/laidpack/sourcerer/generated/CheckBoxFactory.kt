package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.CheckBox
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class CheckBoxFactory<TView : CheckBox, TAttributes : CheckBoxAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "checkBox"

    override fun createInstance(context: Context): View = CheckBox(context)

    companion object {
        init {
            InflaterComponent.addFactory(CheckBoxFactory<CheckBox, CheckBoxAttributes>())
        }

        inline operator fun <reified TView : CheckBox, reified TAttributes : CheckBoxAttributes> invoke() = CheckBoxFactory(TView::class.java, TAttributes::class.java)
    }
}
