package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.Button
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ButtonFactory<TView : Button, TAttributes : ButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "button"

    override fun createInstance(context: Context): View = Button(context)

    companion object {
        init {
            InflaterComponent.addFactory(ButtonFactory<Button, ButtonAttributes>())
        }

        inline operator fun <reified TView : Button, reified TAttributes : ButtonAttributes> invoke() = ButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
