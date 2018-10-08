package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AbsSeekBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AbsSeekBarFactory<TView : AbsSeekBar, TAttributes : AbsSeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ProgressBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "absSeekBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AbsSeekBar is abstract and cannot be instantiated")
    }

    companion object {
        init {
            InflaterComponent.addFactory(AbsSeekBarFactory<AbsSeekBar, AbsSeekBarAttributes>())
        }

        inline operator fun <reified TView : AbsSeekBar, reified TAttributes : AbsSeekBarAttributes> invoke() = AbsSeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
