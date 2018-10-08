package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatEditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class EmojiAppCompatEditTextFactory<TView : EmojiAppCompatEditText, TAttributes : EmojiAppCompatEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiAppCompatEditText"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = EmojiAppCompatEditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(EmojiAppCompatEditTextFactory<EmojiAppCompatEditText, EmojiAppCompatEditTextAttributes>())
        }

        inline operator fun <reified TView : EmojiAppCompatEditText, reified TAttributes : EmojiAppCompatEditTextAttributes> invoke() = EmojiAppCompatEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
