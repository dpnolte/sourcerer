package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewSwitcher
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ViewSwitcherFactory<TView : ViewSwitcher, TAttributes : ViewSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewAnimatorFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "viewSwitcher"

    override fun createInstance(context: Context): View = ViewSwitcher(context)

    companion object {
        init {
            InflaterComponent.addFactory(ViewSwitcherFactory<ViewSwitcher, ViewSwitcherAttributes>())
        }

        inline operator fun <reified TView : ViewSwitcher, reified TAttributes : ViewSwitcherAttributes> invoke() = ViewSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
