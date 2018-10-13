package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ImageButton
import java.lang.Class
import kotlin.String

open class ImageButtonFactory<TView : ImageButton, TAttributes : ImageButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ImageButton(context)

    companion object {
        const val elementType: String = "imageButton"

        inline operator fun <reified TView : ImageButton, reified TAttributes : ImageButtonAttributes> invoke() = ImageButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
