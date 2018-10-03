package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class VideoView2Attributes : ViewGroupAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(VideoView2Attributes::class, VideoView2AttributesJsonAdapter::class, "videoView2")
        }
    }
}
