package com.laidpack.sourcerer.generated.media.widget

import android.content.Context
import android.os.Build
import android.view.View
import androidx.media.widget.MediaControlView2
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = MediaControlView2Factory.elementType,
        attributesClazz = MediaControlView2Attributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class MediaControlView2Factory<TView : MediaControlView2, TAttributes : MediaControlView2Attributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return MediaControlView2(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        const val elementType: String = "mediaControlView2"

        inline operator fun <reified TView : MediaControlView2, reified TAttributes : MediaControlView2Attributes> invoke() = MediaControlView2Factory(TView::class.java, TAttributes::class.java)
    }
}
