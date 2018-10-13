package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AbsListViewAttributes : AdapterViewAttributes(), IAttributes {
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var listSelector: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    var stackFromBottom: Boolean? = null

    var scrollingCache: Boolean? = null

    var textFilterEnabled: Boolean? = null

    var transcriptMode: TranscriptModeEnum? = null

    @field:ColorQualifier
    var cacheColorHint: Int? = null

    var fastScrollEnabled: Boolean? = null

    var smoothScrollbar: Boolean? = null

    var choiceMode: ChoiceModeEnum? = null

    var fastScrollAlwaysVisible: Boolean? = null
}

enum class TranscriptModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "disabled")
    Disabled("disabled", 0),

    @Json(name = "normal")
    Normal("normal", 1),

    @Json(name = "alwaysScroll")
    AlwaysScroll("alwaysScroll", 2);
}

enum class ChoiceModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "singleChoice")
    SingleChoice("singleChoice", 1),

    @Json(name = "multipleChoice")
    MultipleChoice("multipleChoice", 2),

    @Json(name = "multipleChoiceModal")
    MultipleChoiceModal("multipleChoiceModal", 3);
}
