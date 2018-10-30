package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

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
                    if (isHintEnabled != it) {
                        isHintEnabled = it
                    }
                }
                attributes.hintAnimationEnabled?.let {
                    if (isHintAnimationEnabled != it) {
                        isHintAnimationEnabled = it
                    }
                }
                attributes.helperTextEnabled?.let {
                    if (isHelperTextEnabled != it) {
                        isHelperTextEnabled = it
                    }
                }
                attributes.errorEnabled?.let {
                    if (isErrorEnabled != it) {
                        isErrorEnabled = it
                    }
                }
                attributes.counterEnabled?.let {
                    if (isCounterEnabled != it) {
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
                    setPasswordVisibilityToggleDrawable(it)
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
