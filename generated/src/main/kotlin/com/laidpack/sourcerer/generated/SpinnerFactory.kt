package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.core.content.ContextCompat
import java.lang.Class
import kotlin.String

open class SpinnerFactory<TView : Spinner, TAttributes : SpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Spinner(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Spinner) {
            view.apply {
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.popupBackground?.let {
                        val immutablePopupBackground = ContextCompat.getDrawable(context, it) as Drawable
                        if (popupBackground != immutablePopupBackground) {
                            setPopupBackgroundDrawable(immutablePopupBackground)
                        }
                    }
                    attributes.dropDownWidth?.let {
                        if (dropDownWidth != it) {
                            dropDownWidth = it
                        }
                    }
                    attributes.gravity?.let {
                        if (gravity != it) {
                            gravity = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "spinner"

        inline operator fun <reified TView : Spinner, reified TAttributes : SpinnerAttributes> invoke() = SpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
