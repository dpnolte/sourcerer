package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class RemoteEditTextAttributes : EditTextAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(RemoteEditTextAttributes::class, RemoteEditTextAttributesJsonAdapter::class, "remoteEditText")
        }
    }
}
