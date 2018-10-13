package com.laidpack.sourcerer.generated

import android.widget.AbsSeekBar
import java.lang.Class
import kotlin.String

open class AbsSeekBarFactory<TView : AbsSeekBar, TAttributes : AbsSeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ProgressBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    companion object {
        const val elementType: String = "absSeekBar"

        inline operator fun <reified TView : AbsSeekBar, reified TAttributes : AbsSeekBarAttributes> invoke() = AbsSeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
