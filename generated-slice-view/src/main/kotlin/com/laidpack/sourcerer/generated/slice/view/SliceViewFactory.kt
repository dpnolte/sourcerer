package com.laidpack.sourcerer.generated.slice.view

import android.content.Context
import android.os.Build
import android.view.View
import androidx.slice.widget.SliceView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SliceViewFactory.elementType,
        attributesClazz = SliceViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class SliceViewFactory<TView : SliceView, TAttributes : SliceViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 19) {
            return SliceView(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        const val elementType: String = "sliceView"

        inline operator fun <reified TView : SliceView, reified TAttributes : SliceViewAttributes> invoke() = SliceViewFactory(TView::class.java, TAttributes::class.java)
    }
}
