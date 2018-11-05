package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatRadioButton
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.RadioButtonFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AppCompatRadioButtonFactory.elementType,
        attributesClazz = AppCompatRadioButtonAttributes::class
)
open class AppCompatRadioButtonFactory<TView : AppCompatRadioButton, TAttributes : AppCompatRadioButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RadioButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatRadioButton(context)

    companion object {
        const val elementType: String = "appCompatRadioButton"

        inline operator fun <reified TView : AppCompatRadioButton, reified TAttributes : AppCompatRadioButtonAttributes> invoke() = AppCompatRadioButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
