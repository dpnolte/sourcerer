package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import androidx.slice.widget.SliceView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class SliceViewFactory<TView : SliceView, TAttributes : SliceViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "sliceView"

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 19) {
            return SliceView(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(SliceViewFactory<SliceView, SliceViewAttributes>())
        }

        inline operator fun <reified TView : SliceView, reified TAttributes : SliceViewAttributes> invoke() = SliceViewFactory(TView::class.java, TAttributes::class.java)
    }
}
