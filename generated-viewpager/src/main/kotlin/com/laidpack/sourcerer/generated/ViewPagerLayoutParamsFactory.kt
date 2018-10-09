package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ViewPagerLayoutParamsFactory<TLayoutParams : ViewPager.LayoutParams, TAttributes : ViewPagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.viewpager.widget.ViewPager.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewPager.LayoutParams()

    companion object {
        init {
            InflaterComponent.addFactory(ViewPagerLayoutParamsFactory<ViewPager.LayoutParams, ViewPagerLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ViewPager.LayoutParams, reified TAttributes : ViewPagerLayoutParamsAttributes> invoke() = ViewPagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
