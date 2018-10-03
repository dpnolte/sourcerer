package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class EmojiEditTextAttributes : EditTextAttributes(), IAttributes {
    var maxEmojiCount: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(EmojiEditTextAttributes::class, EmojiEditTextAttributesJsonAdapter::class, "emojiEditText")
        }
    }
}
