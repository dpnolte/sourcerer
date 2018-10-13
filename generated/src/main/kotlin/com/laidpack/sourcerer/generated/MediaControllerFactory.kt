package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.MediaController
import java.lang.Class
import kotlin.String

open class MediaControllerFactory<TView : MediaController, TAttributes : MediaControllerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = MediaController(context)

    companion object {
        const val elementType: String = "mediaController"

        inline operator fun <reified TView : MediaController, reified TAttributes : MediaControllerAttributes> invoke() = MediaControllerFactory(TView::class.java, TAttributes::class.java)
    }
}
