package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.AutoCompleteTextViewFactory
import java.lang.Class
import kotlin.String

open class AppCompatAutoCompleteTextViewFactory<TView : AppCompatAutoCompleteTextView, TAttributes : AppCompatAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AutoCompleteTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatAutoCompleteTextView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AppCompatAutoCompleteTextView) {
            view.apply {
                attributes.popupBackground?.let {
                    val localPopupBackground = ContextCompat.getDrawable(context, it) as Drawable
                    setDropDownBackgroundDrawable(localPopupBackground)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appCompatAutoCompleteTextView"

        inline operator fun <reified TView : AppCompatAutoCompleteTextView, reified TAttributes : AppCompatAutoCompleteTextViewAttributes> invoke() = AppCompatAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
