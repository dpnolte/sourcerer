package com.laidpack.sourcerer.generated.emoji.appcompat

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatEditText
import com.laidpack.sourcerer.generated.appcompat.AppCompatEditTextFactory
import java.lang.Class
import kotlin.String

open class EmojiAppCompatEditTextFactory<TView : EmojiAppCompatEditText, TAttributes : EmojiAppCompatEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatEditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiAppCompatEditText(context)

    companion object {
        const val elementType: String = "emojiAppCompatEditText"

        inline operator fun <reified TView : EmojiAppCompatEditText, reified TAttributes : EmojiAppCompatEditTextAttributes> invoke() = EmojiAppCompatEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
