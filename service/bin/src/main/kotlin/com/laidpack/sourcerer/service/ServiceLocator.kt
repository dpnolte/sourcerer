package com.laidpack.sourcerer.service

interface SourcererComponent {

    val serializerService: SerializerComponent<*>

    companion object {
        lateinit var instance: SourcererComponent
    }
}

internal fun serviceLocator(): SourcererComponent = SourcererComponent.instance

class SourcererModule (
        override val serializerService: SerializerComponent<*> = SerializerModule()
): SourcererComponent