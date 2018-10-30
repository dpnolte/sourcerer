package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.CompoundButtonFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class SwitchCompatFactory<TView : SwitchCompat, TAttributes : SwitchCompatAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CompoundButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SwitchCompat(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SwitchCompat) {
            view.apply {
                attributes.thumbTint?.let {
                    val localThumbTint = ColorStateList.valueOf(it)
                    if (thumbTintList != localThumbTint) {
                        thumbTintList = localThumbTint
                    }
                }
                attributes.thumbTintMode?.let {
                    val localThumbTintMode = it.value.toPorterDuffMode()
                    if (thumbTintMode != localThumbTintMode) {
                        thumbTintMode = localThumbTintMode
                    }
                }
                attributes.track?.let {
                    val localTrack = ContextCompat.getDrawable(context, it) as Drawable
                    if (trackDrawable != localTrack) {
                        trackDrawable = localTrack
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
                attributes.thumbTextPadding?.let {
                    if (thumbTextPadding != it) {
                        thumbTextPadding = it
                    }
                }
                attributes.switchTextAppearance?.let {
                    setSwitchTextAppearance(context, it)
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
        const val elementType: String = "switchCompat"

        inline operator fun <reified TView : SwitchCompat, reified TAttributes : SwitchCompatAttributes> invoke() = SwitchCompatFactory(TView::class.java, TAttributes::class.java)
    }
}
