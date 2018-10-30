package com.laidpack.sourcerer.generated.emoji.appcompat

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatTextView
import com.laidpack.sourcerer.generated.appcompat.AppCompatTextViewFactory
import java.lang.Class
import kotlin.String

open class EmojiAppCompatTextViewFactory<TView : EmojiAppCompatTextView, TAttributes : EmojiAppCompatTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiAppCompatTextView(context)

    companion object {
        const val elementType: String = "emojiAppCompatTextView"

        inline operator fun <reified TView : EmojiAppCompatTextView, reified TAttributes : EmojiAppCompatTextViewAttributes> invoke() = EmojiAppCompatTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
