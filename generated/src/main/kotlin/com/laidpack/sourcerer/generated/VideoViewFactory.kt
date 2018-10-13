package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.VideoView
import java.lang.Class
import kotlin.String

open class VideoViewFactory<TView : VideoView, TAttributes : VideoViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = VideoView(context)

    companion object {
        const val elementType: String = "videoView"

        inline operator fun <reified TView : VideoView, reified TAttributes : VideoViewAttributes> invoke() = VideoViewFactory(TView::class.java, TAttributes::class.java)
    }
}
