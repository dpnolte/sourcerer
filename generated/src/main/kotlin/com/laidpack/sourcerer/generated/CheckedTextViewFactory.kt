package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = CheckedTextViewFactory.elementType,
        attributesClazz = CheckedTextViewAttributes::class
)
open class CheckedTextViewFactory<TView : CheckedTextView, TAttributes : CheckedTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CheckedTextView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is CheckedTextView) {
            view.apply {
                attributes.checked?.let {
                    if (isChecked != it) {
                        isChecked = it
                    }
                }
                attributes.checkMark?.let {
                    val localCheckMark = ContextCompat.getDrawable(context, it) as Drawable
                    if (checkMarkDrawable != localCheckMark) {
                        setCheckMarkDrawable(localCheckMark)
                    }
                }
                attributes.checkMarkTint?.let {
                    val localCheckMarkTint = ColorStateList.valueOf(it)
                    if (checkMarkTintList != localCheckMarkTint) {
                        checkMarkTintList = localCheckMarkTint
                    }
                }
                attributes.checkMarkTintMode?.let {
                    val localCheckMarkTintMode = it.value.toPorterDuffMode()
                    if (checkMarkTintMode != localCheckMarkTintMode) {
                        checkMarkTintMode = localCheckMarkTintMode
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "checkedTextView"

        inline operator fun <reified TView : CheckedTextView, reified TAttributes : CheckedTextViewAttributes> invoke() = CheckedTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
