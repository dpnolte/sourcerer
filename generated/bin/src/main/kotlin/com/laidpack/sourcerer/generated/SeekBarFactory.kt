package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.SeekBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class SeekBarFactory<TView : SeekBar, TAttributes : SeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsSeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "seekBar"

    override fun createInstance(context: Context): View = SeekBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(SeekBarFactory<SeekBar, SeekBarAttributes>())
        }

        inline operator fun <reified TView : SeekBar, reified TAttributes : SeekBarAttributes> invoke() = SeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
