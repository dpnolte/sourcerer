package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TextSwitcher
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class TextSwitcherFactory<TView : TextSwitcher, TAttributes : TextSwitcherAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewSwitcherFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textSwitcher"

    override fun createInstance(context: Context): View = TextSwitcher(context)

    companion object {
        init {
            InflaterComponent.addFactory(TextSwitcherFactory<TextSwitcher, TextSwitcherAttributes>())
        }

        inline operator fun <reified TView : TextSwitcher, reified TAttributes : TextSwitcherAttributes> invoke() = TextSwitcherFactory(TView::class.java, TAttributes::class.java)
    }
}
