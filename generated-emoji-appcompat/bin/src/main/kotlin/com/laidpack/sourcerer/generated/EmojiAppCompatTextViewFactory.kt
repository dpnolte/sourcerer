package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class EmojiAppCompatTextViewFactory<TView : EmojiAppCompatTextView, TAttributes : EmojiAppCompatTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiAppCompatTextView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = EmojiAppCompatTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(EmojiAppCompatTextViewFactory<EmojiAppCompatTextView, EmojiAppCompatTextViewAttributes>())
        }

        inline operator fun <reified TView : EmojiAppCompatTextView, reified TAttributes : EmojiAppCompatTextViewAttributes> invoke() = EmojiAppCompatTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
