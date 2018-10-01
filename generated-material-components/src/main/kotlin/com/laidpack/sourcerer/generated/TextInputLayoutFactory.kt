package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class TextInputLayoutFactory<TView : TextInputLayout, TAttributes : TextInputLayoutAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "textInputLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TextInputLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as TextInputLayout
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
            attributes.passwordToggleDrawable?.let {
                val immutablePasswordToggleDrawable = ContextCompat.getDrawable(context, it) as Drawable
                if (passwordVisibilityToggleDrawable != immutablePasswordToggleDrawable) {
                    setPasswordVisibilityToggleDrawable(immutablePasswordToggleDrawable)
                }
            }
            attributes.passwordToggleContentDescription?.let {
                if (passwordVisibilityToggleContentDescription != it) {
                    setPasswordVisibilityToggleContentDescription(it)
                }
            }
        }
    }
}
