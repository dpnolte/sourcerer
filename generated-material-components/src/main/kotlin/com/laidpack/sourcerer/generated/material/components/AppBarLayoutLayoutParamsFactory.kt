package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.sourcerer.generated.LinearLayoutLayoutParamsFactory
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = AppBarLayoutLayoutParamsFactory.elementType,
        attributesClazz = AppBarLayoutLayoutParamsAttributes::class
)
open class AppBarLayoutLayoutParamsFactory<TLayoutParams : AppBarLayout.LayoutParams, TAttributes : AppBarLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is AppBarLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_scrollFlags?.let {
                    val localLayoutScrollFlags = it.value
                    if (scrollFlags != localLayoutScrollFlags) {
                        scrollFlags = localLayoutScrollFlags
                    }
                }
                attributes.layout_scrollInterpolator?.let {
                    val localLayoutScrollInterpolator = AnimationUtils.loadInterpolator(context, it)
                    if (scrollInterpolator != localLayoutScrollInterpolator) {
                        scrollInterpolator = localLayoutScrollInterpolator
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String =
                "com.google.android.material.appbar.AppBarLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : AppBarLayout.LayoutParams, reified TAttributes : AppBarLayoutLayoutParamsAttributes> invoke() = AppBarLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
