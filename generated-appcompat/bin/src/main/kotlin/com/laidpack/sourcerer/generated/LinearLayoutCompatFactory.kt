package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class LinearLayoutCompatFactory<TView : LinearLayoutCompat, TAttributes : LinearLayoutCompatAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "linearLayoutCompat"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = LinearLayoutCompat(context)

    companion object {
        init {
            InflaterComponent.addFactory(LinearLayoutCompatFactory<LinearLayoutCompat, LinearLayoutCompatAttributes>())
        }

        inline operator fun <reified TView : LinearLayoutCompat, reified TAttributes : LinearLayoutCompatAttributes> invoke() = LinearLayoutCompatFactory(TView::class.java, TAttributes::class.java)
    }
}
