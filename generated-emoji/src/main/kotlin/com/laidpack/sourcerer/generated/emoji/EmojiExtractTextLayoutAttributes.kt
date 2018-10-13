package com.laidpack.sourcerer.generated.emoji

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.LinearLayoutAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class EmojiExtractTextLayoutAttributes : LinearLayoutAttributes(), IAttributes {
    var emojiReplaceStrategy: EmojiReplaceStrategyEnum? = null
}

enum class EmojiReplaceStrategyEnum(val attributeName: String, val value: Int) {
    @Json(name = "defaultStrategy")
    DefaultStrategy("defaultStrategy", 0),

    @Json(name = "all")
    All("all", 1),

    @Json(name = "nonExistent")
    NonExistent("nonExistent", 2);
}
