package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.MultiAutoCompleteTextViewFactory
import java.lang.Class
import kotlin.String

open class AppCompatMultiAutoCompleteTextViewFactory<TView : AppCompatMultiAutoCompleteTextView, TAttributes : AppCompatMultiAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : MultiAutoCompleteTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatMultiAutoCompleteTextView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AppCompatMultiAutoCompleteTextView) {
            view.apply {
                attributes.popupBackground?.let {
                    val localPopupBackground = ContextCompat.getDrawable(context, it) as Drawable
                    if (dropDownBackground != localPopupBackground) {
                        setDropDownBackgroundDrawable(localPopupBackground)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appCompatMultiAutoCompleteTextView"

        inline operator fun <reified TView : AppCompatMultiAutoCompleteTextView, reified TAttributes : AppCompatMultiAutoCompleteTextViewAttributes> invoke() = AppCompatMultiAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
