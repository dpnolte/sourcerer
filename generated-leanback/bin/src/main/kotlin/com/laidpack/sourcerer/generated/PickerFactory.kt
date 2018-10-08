package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.picker.Picker
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class PickerFactory<TView : Picker, TAttributes : PickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "picker"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = Picker(context, null, 0)

    companion object {
        init {
            InflaterComponent.addFactory(PickerFactory<Picker, PickerAttributes>())
        }

        inline operator fun <reified TView : Picker, reified TAttributes : PickerAttributes> invoke() = PickerFactory(TView::class.java, TAttributes::class.java)
    }
}
