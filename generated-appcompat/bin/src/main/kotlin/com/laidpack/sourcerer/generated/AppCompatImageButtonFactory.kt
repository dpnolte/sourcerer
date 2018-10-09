package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatImageButtonFactory<TView : AppCompatImageButton, TAttributes : AppCompatImageButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatImageButton"

    override fun createInstance(context: Context): View = AppCompatImageButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatImageButtonFactory<AppCompatImageButton, AppCompatImageButtonAttributes>())
        }

        inline operator fun <reified TView : AppCompatImageButton, reified TAttributes : AppCompatImageButtonAttributes> invoke() = AppCompatImageButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
