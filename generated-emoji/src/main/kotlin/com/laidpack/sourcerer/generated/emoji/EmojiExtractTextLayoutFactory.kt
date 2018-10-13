package com.laidpack.sourcerer.generated.emoji

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiExtractTextLayout
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

open class EmojiExtractTextLayoutFactory<TView : EmojiExtractTextLayout, TAttributes : EmojiExtractTextLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EmojiExtractTextLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is EmojiExtractTextLayout) {
            view.apply {
                attributes.emojiReplaceStrategy?.let {
                    if (emojiReplaceStrategy != it.value) {
                        emojiReplaceStrategy = it.value
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "emojiExtractTextLayout"

        inline operator fun <reified TView : EmojiExtractTextLayout, reified TAttributes : EmojiExtractTextLayoutAttributes> invoke() = EmojiExtractTextLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
