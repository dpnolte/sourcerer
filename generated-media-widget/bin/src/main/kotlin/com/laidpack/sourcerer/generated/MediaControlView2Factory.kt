package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.media.widget.MediaControlView2
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class MediaControlView2Factory<TView : MediaControlView2, TAttributes : MediaControlView2Attributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "mediaControlView2"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = MediaControlView2(context)

    companion object {
        init {
            InflaterComponent.addFactory(MediaControlView2Factory<MediaControlView2, MediaControlView2Attributes>())
        }

        inline operator fun <reified TView : MediaControlView2, reified TAttributes : MediaControlView2Attributes> invoke() = MediaControlView2Factory(TView::class.java, TAttributes::class.java)
    }
}
