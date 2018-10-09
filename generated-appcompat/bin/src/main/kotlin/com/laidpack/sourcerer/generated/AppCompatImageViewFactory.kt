package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatImageViewFactory<TView : AppCompatImageView, TAttributes : AppCompatImageViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatImageView"

    override fun createInstance(context: Context): View = AppCompatImageView(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatImageViewFactory<AppCompatImageView, AppCompatImageViewAttributes>())
        }

        inline operator fun <reified TView : AppCompatImageView, reified TAttributes : AppCompatImageViewAttributes> invoke() = AppCompatImageViewFactory(TView::class.java, TAttributes::class.java)
    }
}
