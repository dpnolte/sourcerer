package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class SpinnerFactory<TView : Spinner, TAttributes : SpinnerAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "spinner"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = Spinner(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as Spinner
        view.init {
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
