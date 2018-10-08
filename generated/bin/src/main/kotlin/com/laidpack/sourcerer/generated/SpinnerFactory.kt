package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SpinnerFactory<TView : Spinner, TAttributes : SpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "spinner"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = Spinner(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
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

    companion object {
        init {
            InflaterComponent.addFactory(SpinnerFactory<Spinner, SpinnerAttributes>())
        }

        inline operator fun <reified TView : Spinner, reified TAttributes : SpinnerAttributes> invoke() = SpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
