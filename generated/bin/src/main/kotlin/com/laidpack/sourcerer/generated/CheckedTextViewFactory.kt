package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class CheckedTextViewFactory<TView : CheckedTextView, TAttributes : CheckedTextViewAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "checkedTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CheckedTextView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as CheckedTextView
        view.init {
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
                        setCheckMarkTintList(immutableCheckMarkTint)
                    }
                }
                attributes.checkMarkTintMode?.let {
                    val immutableCheckMarkTintMode = it.value.toPorterDuffMode()
                    if (checkMarkTintMode != immutableCheckMarkTintMode) {
                        setCheckMarkTintMode(immutableCheckMarkTintMode)
                    }
                }
            }
        }
    }
}
