package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CheckedTextViewFactory<TView : CheckedTextView, TAttributes : CheckedTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "checkedTextView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CheckedTextView(context)

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
            attributes.checkMark?.let {
                val immutableCheckMark = ContextCompat.getDrawable(context, it) as Drawable
                if (checkMarkDrawable != immutableCheckMark) {
                    setCheckMarkDrawable(immutableCheckMark)
                }
            }
            attributes.checkMarkTint?.let {
                val immutableCheckMarkTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (checkMarkTintList != immutableCheckMarkTint) {
                    checkMarkTintList = immutableCheckMarkTint
                }
            }
            attributes.checkMarkTintMode?.let {
                val immutableCheckMarkTintMode = it.value.toPorterDuffMode()
                if (checkMarkTintMode != immutableCheckMarkTintMode) {
                    checkMarkTintMode = immutableCheckMarkTintMode
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(CheckedTextViewFactory<CheckedTextView, CheckedTextViewAttributes>())
        }

        inline operator fun <reified TView : CheckedTextView, reified TAttributes : CheckedTextViewAttributes> invoke() = CheckedTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
