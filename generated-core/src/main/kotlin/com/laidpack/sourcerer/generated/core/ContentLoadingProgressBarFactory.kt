package com.laidpack.sourcerer.generated.core

import android.content.Context
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ProgressBarFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = ContentLoadingProgressBarFactory.elementType,
        attributesClazz = ContentLoadingProgressBarAttributes::class
)
open class ContentLoadingProgressBarFactory<TView : ContentLoadingProgressBar, TAttributes : ContentLoadingProgressBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ProgressBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ContentLoadingProgressBar(context)

    companion object {
        const val elementType: String = "contentLoadingProgressBar"

        inline operator fun <reified TView : ContentLoadingProgressBar, reified TAttributes : ContentLoadingProgressBarAttributes> invoke() = ContentLoadingProgressBarFactory(TView::class.java, TAttributes::class.java)
    }
}
