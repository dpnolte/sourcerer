package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Switch
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SwitchFactory<TView : Switch, TAttributes : SwitchAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "switch"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = Switch(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.thumb?.let {
                val immutableThumb = ContextCompat.getDrawable(context, it) as Drawable
                if (thumbDrawable != immutableThumb) {
                    thumbDrawable = immutableThumb
                }
            }
            attributes.thumbTint?.let {
                val immutableThumbTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (thumbTintList != immutableThumbTint) {
                    thumbTintList = immutableThumbTint
                }
            }
            attributes.thumbTintMode?.let {
                val immutableThumbTintMode = it.toPorterDuffMode()
                if (thumbTintMode != immutableThumbTintMode) {
                    thumbTintMode = immutableThumbTintMode
                }
            }
            attributes.track?.let {
                val immutableTrack = ContextCompat.getDrawable(context, it) as Drawable
                if (trackDrawable != immutableTrack) {
                    trackDrawable = immutableTrack
                }
            }
            attributes.trackTint?.let {
                val immutableTrackTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (trackTintList != immutableTrackTint) {
                    trackTintList = immutableTrackTint
                }
            }
            attributes.trackTintMode?.let {
                val immutableTrackTintMode = it.value.toPorterDuffMode()
                if (trackTintMode != immutableTrackTintMode) {
                    trackTintMode = immutableTrackTintMode
                }
            }
            attributes.textOn?.let {
                if (textOn != it) {
                    textOn = it
                }
            }
            attributes.textOff?.let {
                if (textOff != it) {
                    textOff = it
                }
            }
            attributes.thumbTextPadding?.let {
                if (thumbTextPadding != it) {
                    thumbTextPadding = it
                }
            }
            attributes.switchMinWidth?.let {
                if (switchMinWidth != it) {
                    switchMinWidth = it
                }
            }
            attributes.switchPadding?.let {
                if (switchPadding != it) {
                    switchPadding = it
                }
            }
            attributes.splitTrack?.let {
                if (splitTrack != it) {
                    splitTrack = it
                }
            }
            attributes.showText?.let {
                if (showText != it) {
                    showText = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(SwitchFactory<Switch, SwitchAttributes>())
        }

        inline operator fun <reified TView : Switch, reified TAttributes : SwitchAttributes> invoke() = SwitchFactory(TView::class.java, TAttributes::class.java)
    }
}
