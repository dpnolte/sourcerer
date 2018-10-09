package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatSeekBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatSeekBarFactory<TView : AppCompatSeekBar, TAttributes : AppCompatSeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : SeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatSeekBar"

    override fun createInstance(context: Context): View = AppCompatSeekBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatSeekBarFactory<AppCompatSeekBar, AppCompatSeekBarAttributes>())
        }

        inline operator fun <reified TView : AppCompatSeekBar, reified TAttributes : AppCompatSeekBarAttributes> invoke() = AppCompatSeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
