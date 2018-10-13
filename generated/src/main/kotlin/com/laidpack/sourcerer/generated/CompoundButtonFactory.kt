package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.lang.Class
import kotlin.String

open class CompoundButtonFactory<TView : CompoundButton, TAttributes : CompoundButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is CompoundButton) {
            view.apply {
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

    companion object {
        const val elementType: String = "compoundButton"

        inline operator fun <reified TView : CompoundButton, reified TAttributes : CompoundButtonAttributes> invoke() = CompoundButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
