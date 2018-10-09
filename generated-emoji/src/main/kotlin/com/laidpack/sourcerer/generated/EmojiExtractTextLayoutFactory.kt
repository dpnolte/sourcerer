package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.emoji.widget.EmojiExtractTextLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class EmojiExtractTextLayoutFactory<TView : EmojiExtractTextLayout, TAttributes : EmojiExtractTextLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "emojiExtractTextLayout"

    override fun createInstance(context: Context): View = EmojiExtractTextLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is EmojiExtractTextLayout) {
            view.init {
                attributes.emojiReplaceStrategy?.let {
                    if (emojiReplaceStrategy != it.value) {
                        emojiReplaceStrategy = it.value
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(EmojiExtractTextLayoutFactory<EmojiExtractTextLayout, EmojiExtractTextLayoutAttributes>())
        }

        inline operator fun <reified TView : EmojiExtractTextLayout, reified TAttributes : EmojiExtractTextLayoutAttributes> invoke() = EmojiExtractTextLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
