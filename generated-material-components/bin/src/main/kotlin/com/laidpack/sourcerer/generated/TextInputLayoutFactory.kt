package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TextInputLayoutFactory<TView : TextInputLayout, TAttributes : TextInputLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textInputLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TextInputLayout(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
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
                if (isPasswordVisibilityToggleEnabled != it) {
                    isPasswordVisibilityToggleEnabled = it
                }
            }
            attributes.passwordToggleContentDescription?.let {
                if (passwordVisibilityToggleContentDescription != it) {
                    passwordVisibilityToggleContentDescription = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(TextInputLayoutFactory<TextInputLayout, TextInputLayoutAttributes>())
        }

        inline operator fun <reified TView : TextInputLayout, reified TAttributes : TextInputLayoutAttributes> invoke() = TextInputLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
