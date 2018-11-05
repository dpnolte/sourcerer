package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TextInputLayoutFactory.elementType,
        attributesClazz = TextInputLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TextInputLayoutFactory<TView : TextInputLayout, TAttributes : TextInputLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextInputLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TextInputLayout) {
            view.apply {
                attributes.hintEnabled?.let {
                    if (isEnabled != it) {
                        isHintEnabled = it
                    }
                }
                attributes.hintAnimationEnabled?.let {
                    if (isEnabled != it) {
                        isHintAnimationEnabled = it
                    }
                }
                attributes.helperTextEnabled?.let {
                    if (isEnabled != it) {
                        isHelperTextEnabled = it
                    }
                }
                attributes.errorEnabled?.let {
                    if (isEnabled != it) {
                        isErrorEnabled = it
                    }
                }
                attributes.counterEnabled?.let {
                    if (isEnabled != it) {
                        isCounterEnabled = it
                    }
                }
                attributes.counterMaxLength?.let {
                    if (counterMaxLength != it) {
                        counterMaxLength = it
                    }
                }
                attributes.passwordToggleEnabled?.let {
                    if (isEnabled != it) {
                        isPasswordVisibilityToggleEnabled = it
                    }
                }
                attributes.passwordToggleDrawable?.let {
                    val localPasswordToggleDrawable = ContextCompat.getDrawable(context, it) as Drawable
                    if (passwordVisibilityToggleDrawable != localPasswordToggleDrawable) {
                        passwordVisibilityToggleDrawable = localPasswordToggleDrawable
                    }
                }
                attributes.passwordToggleContentDescription?.let {
                    if (passwordVisibilityToggleContentDescription != it) {
                        passwordVisibilityToggleContentDescription = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "textInputLayout"

        inline operator fun <reified TView : TextInputLayout, reified TAttributes : TextInputLayoutAttributes> invoke() = TextInputLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
