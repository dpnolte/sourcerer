package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
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

    @field:ReferenceQualifier
    var passwordToggleDrawable: Int? = null

    var passwordToggleContentDescription: String? = null

    companion object {
        init {
            SourcererService.registerAdapter(TextInputLayoutAttributes::class, TextInputLayoutAttributesJsonAdapter::class, "textInputLayout")
        }
    }
}
