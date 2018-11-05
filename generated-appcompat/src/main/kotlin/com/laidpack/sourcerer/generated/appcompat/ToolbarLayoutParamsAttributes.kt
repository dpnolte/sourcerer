package com.laidpack.sourcerer.generated.appcompat

import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class ToolbarLayoutParamsAttributes(layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)), layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum))) : ViewGroupLayoutParamsAttributes(layout_width = layout_width, layout_height = layout_height),
        IAttributes
