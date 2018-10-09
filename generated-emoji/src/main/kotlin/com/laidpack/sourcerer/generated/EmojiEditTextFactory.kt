package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiEditText
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class EmojiEditTextFactory<TView : EmojiEditText, TAttributes : EmojiEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiEditText"

    override fun createInstance(context: Context): View = EmojiEditText(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is EmojiEditText) {
            view.init {
                attributes.maxEmojiCount?.let {
                    if (maxEmojiCount != it) {
                        maxEmojiCount = it
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(EmojiEditTextFactory<EmojiEditText, EmojiEditTextAttributes>())
        }

        inline operator fun <reified TView : EmojiEditText, reified TAttributes : EmojiEditTextAttributes> invoke() = EmojiEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
