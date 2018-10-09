package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class EmojiButtonFactory<TView : EmojiButton, TAttributes : EmojiButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiButton"

    override fun createInstance(context: Context): View = EmojiButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(EmojiButtonFactory<EmojiButton, EmojiButtonAttributes>())
        }

        inline operator fun <reified TView : EmojiButton, reified TAttributes : EmojiButtonAttributes> invoke() = EmojiButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
