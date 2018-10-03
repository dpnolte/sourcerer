package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiEditText
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class EmojiEditTextFactory<TView : EmojiEditText, TAttributes : EmojiEditTextAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "emojiEditText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiEditText(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as EmojiEditText
        view.init {
            attributes.maxEmojiCount?.let {
                if (maxEmojiCount != it) {
                    maxEmojiCount = it
                }
            }
        }
    }
}
