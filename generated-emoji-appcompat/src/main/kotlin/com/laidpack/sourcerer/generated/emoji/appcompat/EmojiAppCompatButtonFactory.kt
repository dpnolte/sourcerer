package com.laidpack.sourcerer.generated.emoji.appcompat

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatButton
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.appcompat.AppCompatButtonFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = EmojiAppCompatButtonFactory.elementType,
        attributesClazz = EmojiAppCompatButtonAttributes::class
)
open class EmojiAppCompatButtonFactory<TView : EmojiAppCompatButton, TAttributes : EmojiAppCompatButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiAppCompatButton(context)

    companion object {
        const val elementType: String = "emojiAppCompatButton"

        inline operator fun <reified TView : EmojiAppCompatButton, reified TAttributes : EmojiAppCompatButtonAttributes> invoke() = EmojiAppCompatButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
