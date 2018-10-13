package com.laidpack.sourcerer.service

import kotlin.reflect.KClass

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


object SourcererService {
    private val serializerService by lazy { serviceLocator().serializer }

    fun registerAdapter(subjectType: KClass<*>, adapterType: KClass<*>, elementName: String? = null)
            = serializerService.registerAdapter(subjectType, adapterType, elementName)
}