package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatSeekBar
import com.laidpack.sourcerer.generated.SeekBarFactory
import java.lang.Class
import kotlin.String

open class AppCompatSeekBarFactory<TView : AppCompatSeekBar, TAttributes : AppCompatSeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : SeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatSeekBar(context)

    companion object {
        const val elementType: String = "appCompatSeekBar"

        inline operator fun <reified TView : AppCompatSeekBar, reified TAttributes : AppCompatSeekBarAttributes> invoke() = AppCompatSeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
