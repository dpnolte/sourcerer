package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class SwitchCompatFactory<TView : SwitchCompat, TAttributes : SwitchCompatAttributes> : ButtonFactory<TView, TAttributes>() {
    override val elementName: String = "switchCompat"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SwitchCompat(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as SwitchCompat
        view.init {
            attributes.thumbTint?.let {
                val immutableThumbTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (thumbTintList != immutableThumbTint) {
                    setThumbTintList(immutableThumbTint)
                }
            }
            attributes.thumbTintMode?.let {
                val immutableThumbTintMode = it.value.toPorterDuffMode()
                if (thumbTintMode != immutableThumbTintMode) {
                    setThumbTintMode(immutableThumbTintMode)
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
                    setTrackTintList(immutableTrackTint)
                }
            }
            attributes.trackTintMode?.let {
                val immutableTrackTintMode = it.value.toPorterDuffMode()
                if (trackTintMode != immutableTrackTintMode) {
                    setTrackTintMode(immutableTrackTintMode)
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
