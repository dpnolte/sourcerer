package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ImageViewFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AppCompatImageViewFactory.elementType,
        attributesClazz = AppCompatImageViewAttributes::class
)
open class AppCompatImageViewFactory<TView : AppCompatImageView, TAttributes : AppCompatImageViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatImageView(context)

    companion object {
        const val elementType: String = "appCompatImageView"

        inline operator fun <reified TView : AppCompatImageView, reified TAttributes : AppCompatImageViewAttributes> invoke() = AppCompatImageViewFactory(TView::class.java, TAttributes::class.java)
    }
}
