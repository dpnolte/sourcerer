package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchEditText
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.EditTextFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = SearchEditTextFactory.elementType,
        attributesClazz = SearchEditTextAttributes::class
)
open class SearchEditTextFactory<TView : SearchEditText, TAttributes : SearchEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SearchEditText(context)

    companion object {
        const val elementType: String = "searchEditText"

        inline operator fun <reified TView : SearchEditText, reified TAttributes : SearchEditTextAttributes> invoke() = SearchEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
