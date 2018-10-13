package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class LinearLayoutCompatFactory<TView : LinearLayoutCompat, TAttributes : LinearLayoutCompatAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = LinearLayoutCompat(context)

    companion object {
        const val elementType: String = "linearLayoutCompat"

        inline operator fun <reified TView : LinearLayoutCompat, reified TAttributes : LinearLayoutCompatAttributes> invoke() = LinearLayoutCompatFactory(TView::class.java, TAttributes::class.java)
    }
}
