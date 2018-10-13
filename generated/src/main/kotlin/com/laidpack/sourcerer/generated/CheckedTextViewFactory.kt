package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.lang.Class
import kotlin.String

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
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.checkMark?.let {
                        val immutableCheckMark = ContextCompat.getDrawable(context, it) as Drawable
                        if (checkMarkDrawable != immutableCheckMark) {
                            setCheckMarkDrawable(immutableCheckMark)
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.checkMarkTint?.let {
                        val immutableCheckMarkTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                        if (checkMarkTintList != immutableCheckMarkTint) {
                            checkMarkTintList = immutableCheckMarkTint
                        }
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
