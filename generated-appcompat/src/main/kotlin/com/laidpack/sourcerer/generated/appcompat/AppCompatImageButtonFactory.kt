package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ImageButtonFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AppCompatImageButtonFactory.elementType,
        attributesClazz = AppCompatImageButtonAttributes::class
)
open class AppCompatImageButtonFactory<TView : AppCompatImageButton, TAttributes : AppCompatImageButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatImageButton(context)

    companion object {
        const val elementType: String = "appCompatImageButton"

        inline operator fun <reified TView : AppCompatImageButton, reified TAttributes : AppCompatImageButtonAttributes> invoke() = AppCompatImageButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
