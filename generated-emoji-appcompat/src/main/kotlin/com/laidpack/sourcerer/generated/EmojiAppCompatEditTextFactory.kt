package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiAppCompatEditText
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class EmojiAppCompatEditTextFactory<TView : EmojiAppCompatEditText, TAttributes : EmojiAppCompatEditTextAttributes> : EditTextFactory<TView, TAttributes>() {
    override val elementName: String = "emojiAppCompatEditText"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiAppCompatEditText(context)
}
