package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.LinearLayoutAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextInputLayoutAttributes : LinearLayoutAttributes(), IAttributes {
    var hintEnabled: Boolean? = null

    var hintAnimationEnabled: Boolean? = null

    var helperTextEnabled: Boolean? = null

    var errorEnabled: Boolean? = null

    var counterEnabled: Boolean? = null

    var counterMaxLength: Int? = null

    var passwordToggleEnabled: Boolean? = null

    var passwordToggleContentDescription: String? = null
}
