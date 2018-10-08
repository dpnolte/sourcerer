package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.SeekBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SeekBarFactory<TView : SeekBar, TAttributes : SeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsSeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "seekBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SeekBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(SeekBarFactory<SeekBar, SeekBarAttributes>())
        }

        inline operator fun <reified TView : SeekBar, reified TAttributes : SeekBarAttributes> invoke() = SeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
