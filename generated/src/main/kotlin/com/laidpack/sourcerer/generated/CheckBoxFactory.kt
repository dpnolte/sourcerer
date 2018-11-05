package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.CheckBox
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = CheckBoxFactory.elementType,
        attributesClazz = CheckBoxAttributes::class
)
open class CheckBoxFactory<TView : CheckBox, TAttributes : CheckBoxAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CompoundButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CheckBox(context)

    companion object {
        const val elementType: String = "checkBox"

        inline operator fun <reified TView : CheckBox, reified TAttributes : CheckBoxAttributes> invoke() = CheckBoxFactory(TView::class.java, TAttributes::class.java)
    }
}
