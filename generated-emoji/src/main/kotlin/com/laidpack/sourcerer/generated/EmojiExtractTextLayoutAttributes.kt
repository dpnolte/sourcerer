package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class EmojiExtractTextLayoutAttributes : LinearLayoutAttributes() {
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
