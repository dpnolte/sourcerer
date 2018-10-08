package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.CircularProgressLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CircularProgressLayoutFactory<TView : CircularProgressLayout, TAttributes : CircularProgressLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularProgressLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CircularProgressLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularProgressLayoutFactory<CircularProgressLayout, CircularProgressLayoutAttributes>())
        }

        inline operator fun <reified TView : CircularProgressLayout, reified TAttributes : CircularProgressLayoutAttributes> invoke() = CircularProgressLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
