package com.laidpack.sourcerer.generated.emoji

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiTextView
import com.laidpack.sourcerer.generated.TextViewFactory
import java.lang.Class
import kotlin.String

open class EmojiTextViewFactory<TView : EmojiTextView, TAttributes : EmojiTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiTextView(context)

    companion object {
        const val elementType: String = "emojiTextView"

        inline operator fun <reified TView : EmojiTextView, reified TAttributes : EmojiTextViewAttributes> invoke() = EmojiTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
