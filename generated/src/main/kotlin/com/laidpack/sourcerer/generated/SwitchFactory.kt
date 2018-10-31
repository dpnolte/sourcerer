package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Switch
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class SwitchFactory<TView : Switch, TAttributes : SwitchAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CompoundButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Switch(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Switch) {
            view.apply {
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
                attributes.switchTextAppearance?.let {
                    setSwitchTextAppearance(context, it)
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.thumb?.let {
                        val localThumb = ContextCompat.getDrawable(context, it) as Drawable
                        if (thumbDrawable != localThumb) {
                            thumbDrawable = localThumb
                        }
                    }
                    attributes.track?.let {
                        val localTrack = ContextCompat.getDrawable(context, it) as Drawable
                        if (trackDrawable != localTrack) {
                            trackDrawable = localTrack
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
                }
                if (Build.VERSION.SDK_INT >= 21) {
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
                if (Build.VERSION.SDK_INT >= 23) {
                    attributes.thumbTint?.let {
                        val localThumbTint = ColorStateList.valueOf(it)
                        if (thumbTintList != localThumbTint) {
                            thumbTintList = localThumbTint
                        }
                    }
                    attributes.thumbTintMode?.let {
                        val localThumbTintMode = it.toPorterDuffMode()
                        if (thumbTintMode != localThumbTintMode) {
                            thumbTintMode = localThumbTintMode
                        }
                    }
                    attributes.trackTint?.let {
                        val localTrackTint = ColorStateList.valueOf(it)
                        if (trackTintList != localTrackTint) {
                            trackTintList = localTrackTint
                        }
                    }
                    attributes.trackTintMode?.let {
                        val localTrackTintMode = it.value.toPorterDuffMode()
                        if (trackTintMode != localTrackTintMode) {
                            trackTintMode = localTrackTintMode
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "switch"

        inline operator fun <reified TView : Switch, reified TAttributes : SwitchAttributes> invoke() = SwitchFactory(TView::class.java, TAttributes::class.java)
    }
}
