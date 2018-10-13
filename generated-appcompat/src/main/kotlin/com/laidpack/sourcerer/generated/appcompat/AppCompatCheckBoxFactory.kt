package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import com.laidpack.sourcerer.generated.CheckBoxFactory
import java.lang.Class
import kotlin.String

open class AppCompatCheckBoxFactory<TView : AppCompatCheckBox, TAttributes : AppCompatCheckBoxAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CheckBoxFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatCheckBox(context)

    companion object {
        const val elementType: String = "appCompatCheckBox"

        inline operator fun <reified TView : AppCompatCheckBox, reified TAttributes : AppCompatCheckBoxAttributes> invoke() = AppCompatCheckBoxFactory(TView::class.java, TAttributes::class.java)
    }
}
