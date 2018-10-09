package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class SwitchCompatFactory<TView : SwitchCompat, TAttributes : SwitchCompatAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "switchCompat"

    override fun createInstance(context: Context): View = SwitchCompat(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SwitchCompat) {
            view.init {
                attributes.thumbTint?.let {
                    val immutableThumbTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (thumbTintList != immutableThumbTint) {
                        thumbTintList = immutableThumbTint
                    }
                }
                attributes.thumbTintMode?.let {
                    val immutableThumbTintMode = it.value.toPorterDuffMode()
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
    }

    companion object {
        init {
            InflaterComponent.addFactory(SwitchCompatFactory<SwitchCompat, SwitchCompatAttributes>())
        }

        inline operator fun <reified TView : SwitchCompat, reified TAttributes : SwitchCompatAttributes> invoke() = SwitchCompatFactory(TView::class.java, TAttributes::class.java)
    }
}
