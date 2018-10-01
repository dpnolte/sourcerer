package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.emoji.widget.EmojiExtractTextLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class EmojiExtractTextLayoutFactory<TView : EmojiExtractTextLayout, TAttributes : EmojiExtractTextLayoutAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "emojiExtractTextLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = EmojiExtractTextLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as EmojiExtractTextLayout
        view.init {
            attributes.emojiReplaceStrategy?.let {
                if (emojiReplaceStrategy != it.value) {
                    emojiReplaceStrategy = it.value
                }
            }
        }
    }
}
