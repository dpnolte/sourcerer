package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.View
import android.widget.AbsSeekBar
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class AbsSeekBarFactory<TView : AbsSeekBar, TAttributes : AbsSeekBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ProgressBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AbsSeekBar) {
            view.apply {
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.thumbTint?.let {
                        val localThumbTint = ColorStateList.valueOf(it)
                        if (thumbTintList != localThumbTint) {
                            thumbTintList = localThumbTint
                        }
                    }
                    attributes.thumbTintMode?.let {
                        val localThumbTintMode = it.value.toPorterDuffMode()
                        if (thumbTintMode != localThumbTintMode) {
                            thumbTintMode = localThumbTintMode
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    attributes.tickMarkTint?.let {
                        val localTickMarkTint = ColorStateList.valueOf(it)
                        if (tickMarkTintList != localTickMarkTint) {
                            tickMarkTintList = localTickMarkTint
                        }
                    }
                    attributes.tickMarkTintMode?.let {
                        val localTickMarkTintMode = it.value.toPorterDuffMode()
                        if (tickMarkTintMode != localTickMarkTintMode) {
                            tickMarkTintMode = localTickMarkTintMode
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "absSeekBar"

        inline operator fun <reified TView : AbsSeekBar, reified TAttributes : AbsSeekBarAttributes> invoke() = AbsSeekBarFactory(TView::class.java, TAttributes::class.java)
    }
}
