package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiTextView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class EmojiTextViewFactory<TView : EmojiTextView, TAttributes : EmojiTextViewAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "emojiTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiTextView(context)
}
