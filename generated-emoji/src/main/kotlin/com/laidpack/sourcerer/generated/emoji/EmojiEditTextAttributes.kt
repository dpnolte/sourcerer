package com.laidpack.sourcerer.generated.emoji

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.EditTextAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class EmojiEditTextAttributes : EditTextAttributes(), IAttributes {
    var maxEmojiCount: Int? = null
}
