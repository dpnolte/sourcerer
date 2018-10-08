package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CompoundButtonFactory<TView : CompoundButton, TAttributes : CompoundButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "compoundButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.CompoundButton is abstract and cannot be instantiated")
    }

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.checked?.let {
                if (isChecked != it) {
                    isChecked = it
                }
            }
            attributes.button?.let {
                val immutableButton = ContextCompat.getDrawable(context, it) as Drawable
                if (buttonDrawable != immutableButton) {
                    buttonDrawable = immutableButton
                }
            }
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
    }

    companion object {
        init {
            InflaterComponent.addFactory(CompoundButtonFactory<CompoundButton, CompoundButtonAttributes>())
        }

        inline operator fun <reified TView : CompoundButton, reified TAttributes : CompoundButtonAttributes> invoke() = CompoundButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
