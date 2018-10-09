package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatRadioButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatRadioButtonFactory<TView : AppCompatRadioButton, TAttributes : AppCompatRadioButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatRadioButton"

    override fun createInstance(context: Context): View = AppCompatRadioButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatRadioButtonFactory<AppCompatRadioButton, AppCompatRadioButtonAttributes>())
        }

        inline operator fun <reified TView : AppCompatRadioButton, reified TAttributes : AppCompatRadioButtonAttributes> invoke() = AppCompatRadioButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
