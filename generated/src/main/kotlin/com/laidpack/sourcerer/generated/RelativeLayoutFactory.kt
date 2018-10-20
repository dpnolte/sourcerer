package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.RelativeLayout
import java.lang.Class
import kotlin.String

open class RelativeLayoutFactory<TView : RelativeLayout, TAttributes : RelativeLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = RelativeLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is RelativeLayout) {
            view.apply {
                attributes.ignoreGravity?.let {
                    setIgnoreGravity(it)
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.gravity?.let {
                        val localGravity = it.value
                        if (gravity != localGravity) {
                            gravity = localGravity
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "relativeLayout"

        inline operator fun <reified TView : RelativeLayout, reified TAttributes : RelativeLayoutAttributes> invoke() = RelativeLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
