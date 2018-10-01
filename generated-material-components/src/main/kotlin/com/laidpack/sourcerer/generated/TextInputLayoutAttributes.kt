package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextInputLayoutAttributes : LinearLayoutAttributes() {
    var hintEnabled: Boolean? = null

    var hintAnimationEnabled: Boolean? = null

    var helperTextEnabled: Boolean? = null

    var errorEnabled: Boolean? = null

    var counterEnabled: Boolean? = null

    var counterMaxLength: Int? = null

    var passwordToggleEnabled: Boolean? = null

    @field:ReferenceQualifier
    var passwordToggleDrawable: Int? = null

    var passwordToggleContentDescription: String? = null
}
