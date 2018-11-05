package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.Button
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = ButtonFactory.elementType,
        attributesClazz = ButtonAttributes::class
)
open class ButtonFactory<TView : Button, TAttributes : ButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Button(context)

    companion object {
        const val elementType: String = "button"

        inline operator fun <reified TView : Button, reified TAttributes : ButtonAttributes> invoke() = ButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
