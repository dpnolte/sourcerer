package com.laidpack.sourcerer.generated.emoji

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiButton
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ButtonFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = EmojiButtonFactory.elementType,
        attributesClazz = EmojiButtonAttributes::class
)
open class EmojiButtonFactory<TView : EmojiButton, TAttributes : EmojiButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiButton(context)

    companion object {
        const val elementType: String = "emojiButton"

        inline operator fun <reified TView : EmojiButton, reified TAttributes : EmojiButtonAttributes> invoke() = EmojiButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
