package com.laidpack.sourcerer.service

import com.squareup.moshi.JsonAdapter

interface SourcererComponent<TAdapter : Any> {

    val serializerService: SerializerComponent<TAdapter>

    companion object {
        lateinit var instance: SourcererComponent<Any>
    }
}

internal fun serviceLocator(): SourcererComponent<Any> = SourcererComponent.instance

class SourcererModule (
        override val serializerService: SerializerComponent<JsonAdapter<out Any>>
): SourcererComponent<JsonAdapter<out Any>>

