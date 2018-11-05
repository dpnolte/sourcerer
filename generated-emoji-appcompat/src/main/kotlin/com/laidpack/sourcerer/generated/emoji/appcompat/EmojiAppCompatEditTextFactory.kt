package com.laidpack.sourcerer.generated.emoji.appcompat

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatEditText
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.appcompat.AppCompatEditTextFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = EmojiAppCompatEditTextFactory.elementType,
        attributesClazz = EmojiAppCompatEditTextAttributes::class
)
open class EmojiAppCompatEditTextFactory<TView : EmojiAppCompatEditText, TAttributes : EmojiAppCompatEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatEditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiAppCompatEditText(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is EmojiAppCompatEditText) {
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
        const val elementType: String = "emojiAppCompatEditText"

        inline operator fun <reified TView : EmojiAppCompatEditText, reified TAttributes : EmojiAppCompatEditTextAttributes> invoke() = EmojiAppCompatEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
