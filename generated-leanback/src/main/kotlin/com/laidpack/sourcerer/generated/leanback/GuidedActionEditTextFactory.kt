package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.GuidedActionEditText
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.EditTextFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = GuidedActionEditTextFactory.elementType,
        attributesClazz = GuidedActionEditTextAttributes::class
)
open class GuidedActionEditTextFactory<TView : GuidedActionEditText, TAttributes : GuidedActionEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = GuidedActionEditText(context)

    companion object {
        const val elementType: String = "guidedActionEditText"

        inline operator fun <reified TView : GuidedActionEditText, reified TAttributes : GuidedActionEditTextAttributes> invoke() = GuidedActionEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
