package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.MediaController
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class MediaControllerFactory<TView : MediaController, TAttributes : MediaControllerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "mediaController"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = MediaController(context)

    companion object {
        init {
            InflaterComponent.addFactory(MediaControllerFactory<MediaController, MediaControllerAttributes>())
        }

        inline operator fun <reified TView : MediaController, reified TAttributes : MediaControllerAttributes> invoke() = MediaControllerFactory(TView::class.java, TAttributes::class.java)
    }
}
