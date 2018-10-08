package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.GuidedActionEditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class GuidedActionEditTextFactory<TView : GuidedActionEditText, TAttributes : GuidedActionEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "guidedActionEditText"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = GuidedActionEditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(GuidedActionEditTextFactory<GuidedActionEditText, GuidedActionEditTextAttributes>())
        }

        inline operator fun <reified TView : GuidedActionEditText, reified TAttributes : GuidedActionEditTextAttributes> invoke() = GuidedActionEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
