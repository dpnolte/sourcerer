package com.laidpack.sourcerer.generated.media.widget

import android.content.Context
import android.os.Build
import android.view.View
import androidx.media.widget.VideoView2
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = VideoView2Factory.elementType,
        attributesClazz = VideoView2Attributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class VideoView2Factory<TView : VideoView2, TAttributes : VideoView2Attributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return VideoView2(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        const val elementType: String = "videoView2"

        inline operator fun <reified TView : VideoView2, reified TAttributes : VideoView2Attributes> invoke() = VideoView2Factory(TView::class.java, TAttributes::class.java)
    }
}
