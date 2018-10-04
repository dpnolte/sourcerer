package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.media.widget.MediaControlView2
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class MediaControlView2Factory<TView : MediaControlView2, TAttributes : MediaControlView2Attributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "mediaControlView2"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = MediaControlView2(context)
}
