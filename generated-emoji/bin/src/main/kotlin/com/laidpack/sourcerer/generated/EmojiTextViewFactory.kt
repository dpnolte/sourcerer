package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class EmojiTextViewFactory<TView : EmojiTextView, TAttributes : EmojiTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiTextView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = EmojiTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(EmojiTextViewFactory<EmojiTextView, EmojiTextViewAttributes>())
        }

        inline operator fun <reified TView : EmojiTextView, reified TAttributes : EmojiTextViewAttributes> invoke() = EmojiTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
