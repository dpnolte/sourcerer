package com.laidpack.sourcerer.generated.viewpager

import android.content.Context
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class ViewPagerLayoutParamsFactory<TLayoutParams : ViewPager.LayoutParams, TAttributes : ViewPagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewPager.LayoutParams()

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is ViewPager.LayoutParams) {
            layoutParams.apply {
                attributes.layout_gravity?.let {
                    val localLayoutGravity = it.value
                    if (localLayoutGravity != gravity) {
                        gravity = localLayoutGravity
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "androidx.viewpager.widget.ViewPager.LayoutParams"

        inline operator fun <reified TLayoutParams : ViewPager.LayoutParams, reified TAttributes : ViewPagerLayoutParamsAttributes> invoke() = ViewPagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
