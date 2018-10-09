package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiAppCompatButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class EmojiAppCompatButtonFactory<TView : EmojiAppCompatButton, TAttributes : EmojiAppCompatButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiAppCompatButton"

    override fun createInstance(context: Context): View = EmojiAppCompatButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(EmojiAppCompatButtonFactory<EmojiAppCompatButton, EmojiAppCompatButtonAttributes>())
        }

        inline operator fun <reified TView : EmojiAppCompatButton, reified TAttributes : EmojiAppCompatButtonAttributes> invoke() = EmojiAppCompatButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
