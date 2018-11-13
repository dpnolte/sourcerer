package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = CompoundButtonFactory.elementType,
        attributesClazz = CompoundButtonAttributes::class
)
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
                        val localButtonTint = ColorStateList.valueOf(it)
                        if (buttonTintList != localButtonTint) {
                            buttonTintList = localButtonTint
                        }
                    }
                    attributes.buttonTintMode?.let {
                        val localButtonTintMode = it.value.toPorterDuffMode()
                        if (buttonTintMode != localButtonTintMode) {
                            buttonTintMode = localButtonTintMode
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    attributes.button?.let {
                        val localButton = ContextCompat.getDrawable(context, it) as Drawable
                        if (buttonDrawable != localButton) {
                            buttonDrawable = localButton
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
