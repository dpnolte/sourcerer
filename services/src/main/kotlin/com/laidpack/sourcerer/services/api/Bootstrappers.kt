package com.laidpack.sourcerer.services.api

import com.laidpack.sourcerer.services.InflaterComponent
import com.laidpack.sourcerer.services.SerializerComponent

interface Bootstrapper {
    fun bootstrap(serializer: SerializerComponent, inflater: InflaterComponent)
}