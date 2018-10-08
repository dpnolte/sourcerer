package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatRatingBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AppCompatRatingBarFactory<TView : AppCompatRatingBar, TAttributes : AppCompatRatingBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RatingBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatRatingBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = AppCompatRatingBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatRatingBarFactory<AppCompatRatingBar, AppCompatRatingBarAttributes>())
        }

        inline operator fun <reified TView : AppCompatRatingBar, reified TAttributes : AppCompatRatingBarAttributes> invoke() = AppCompatRatingBarFactory(TView::class.java, TAttributes::class.java)
    }
}
