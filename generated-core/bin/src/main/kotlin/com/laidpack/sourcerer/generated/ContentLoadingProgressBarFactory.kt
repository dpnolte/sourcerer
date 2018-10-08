package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ContentLoadingProgressBarFactory<TView : ContentLoadingProgressBar, TAttributes : ContentLoadingProgressBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ProgressBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "contentLoadingProgressBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ContentLoadingProgressBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(ContentLoadingProgressBarFactory<ContentLoadingProgressBar, ContentLoadingProgressBarAttributes>())
        }

        inline operator fun <reified TView : ContentLoadingProgressBar, reified TAttributes : ContentLoadingProgressBarAttributes> invoke() = ContentLoadingProgressBarFactory(TView::class.java, TAttributes::class.java)
    }
}
