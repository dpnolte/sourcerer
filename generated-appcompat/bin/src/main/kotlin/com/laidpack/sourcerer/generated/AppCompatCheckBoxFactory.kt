package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatCheckBoxFactory<TView : AppCompatCheckBox, TAttributes : AppCompatCheckBoxAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatCheckBox"

    override fun createInstance(context: Context): View = AppCompatCheckBox(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatCheckBoxFactory<AppCompatCheckBox, AppCompatCheckBoxAttributes>())
        }

        inline operator fun <reified TView : AppCompatCheckBox, reified TAttributes : AppCompatCheckBoxAttributes> invoke() = AppCompatCheckBoxFactory(TView::class.java, TAttributes::class.java)
    }
}
