package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatRatingBar
import com.laidpack.sourcerer.generated.RatingBarFactory
import java.lang.Class
import kotlin.String

open class AppCompatRatingBarFactory<TView : AppCompatRatingBar, TAttributes : AppCompatRatingBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RatingBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatRatingBar(context)

    companion object {
        const val elementType: String = "appCompatRatingBar"

        inline operator fun <reified TView : AppCompatRatingBar, reified TAttributes : AppCompatRatingBarAttributes> invoke() = AppCompatRatingBarFactory(TView::class.java, TAttributes::class.java)
    }
}
