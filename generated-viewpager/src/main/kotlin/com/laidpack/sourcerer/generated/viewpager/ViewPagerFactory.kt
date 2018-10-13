package com.laidpack.sourcerer.generated.viewpager

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class ViewPagerFactory<TView : ViewPager, TAttributes : ViewPagerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ViewPager(context)

    companion object {
        const val elementType: String = "viewPager"

        inline operator fun <reified TView : ViewPager, reified TAttributes : ViewPagerAttributes> invoke() = ViewPagerFactory(TView::class.java, TAttributes::class.java)
    }
}
