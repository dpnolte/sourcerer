package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.VideoView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class VideoViewFactory<TView : VideoView, TAttributes : VideoViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "videoView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = VideoView(context)

    companion object {
        init {
            InflaterComponent.addFactory(VideoViewFactory<VideoView, VideoViewAttributes>())
        }

        inline operator fun <reified TView : VideoView, reified TAttributes : VideoViewAttributes> invoke() = VideoViewFactory(TView::class.java, TAttributes::class.java)
    }
}
