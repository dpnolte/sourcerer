package com.laidpack.sourcerer.generated.emoji

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiEditText
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.EditTextFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = EmojiEditTextFactory.elementType,
        attributesClazz = EmojiEditTextAttributes::class
)
open class EmojiEditTextFactory<TView : EmojiEditText, TAttributes : EmojiEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiEditText(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is EmojiEditText) {
            view.apply {
                attributes.maxEmojiCount?.let {
                    if (maxEmojiCount != it) {
                        maxEmojiCount = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "emojiEditText"

        inline operator fun <reified TView : EmojiEditText, reified TAttributes : EmojiEditTextAttributes> invoke() = EmojiEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
