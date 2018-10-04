package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiAppCompatButton
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class EmojiAppCompatButtonFactory<TView : EmojiAppCompatButton, TAttributes : EmojiAppCompatButtonAttributes> : ButtonFactory<TView, TAttributes>() {
    override val elementName: String = "emojiAppCompatButton"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiAppCompatButton(context)
}
