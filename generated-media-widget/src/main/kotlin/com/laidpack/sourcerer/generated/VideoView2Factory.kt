package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import androidx.media.widget.VideoView2
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class VideoView2Factory<TView : VideoView2, TAttributes : VideoView2Attributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "videoView2"

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return VideoView2(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(VideoView2Factory<VideoView2, VideoView2Attributes>())
        }

        inline operator fun <reified TView : VideoView2, reified TAttributes : VideoView2Attributes> invoke() = VideoView2Factory(TView::class.java, TAttributes::class.java)
    }
}
