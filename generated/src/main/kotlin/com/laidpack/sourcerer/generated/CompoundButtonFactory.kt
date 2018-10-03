package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import kotlin.String

open class CompoundButtonFactory<TView : CompoundButton, TAttributes : CompoundButtonAttributes> : ButtonFactory<TView, TAttributes>() {
    override val elementName: String = "compoundButton"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.CompoundButton is abstract and cannot be instantiated")
    }

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as CompoundButton
        view.init {
            attributes.checked?.let {
                if (isChecked != it) {
                    isChecked = it
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.buttonTint?.let {
                    val immutableButtonTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (buttonTintList != immutableButtonTint) {
                        buttonTintList = immutableButtonTint
                    }
                }
                attributes.buttonTintMode?.let {
                    val immutableButtonTintMode = it.value.toPorterDuffMode()
                    if (buttonTintMode != immutableButtonTintMode) {
                        buttonTintMode = immutableButtonTintMode
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 23) {
                attributes.button?.let {
                    val immutableButton = ContextCompat.getDrawable(context, it) as Drawable
                    if (buttonDrawable != immutableButton) {
                        buttonDrawable = immutableButton
                    }
                }
            }
        }
    }
}
