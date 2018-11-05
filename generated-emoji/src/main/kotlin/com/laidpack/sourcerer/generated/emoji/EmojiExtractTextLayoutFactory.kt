package com.laidpack.sourcerer.generated.emoji

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiExtractTextLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = EmojiExtractTextLayoutFactory.elementType,
        attributesClazz = EmojiExtractTextLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
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
                if (attributes.emojiReplaceStrategy.hasInteger || attributes.emojiReplaceStrategy.hasEnum) {
                    val localEmojiReplaceStrategy = when {
                        attributes.emojiReplaceStrategy.hasInteger -> attributes.emojiReplaceStrategy.integer
                        else -> attributes.emojiReplaceStrategy.enum
                    }
                    if (emojiReplaceStrategy != localEmojiReplaceStrategy) {
                        emojiReplaceStrategy = localEmojiReplaceStrategy
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
