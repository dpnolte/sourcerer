package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiAppCompatTextView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class EmojiAppCompatTextViewFactory<TView : EmojiAppCompatTextView, TAttributes : EmojiAppCompatTextViewAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "emojiAppCompatTextView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiAppCompatTextView(context)
}
