package com.laidpack.sourcerer.service

import android.content.Context
import kotlin.reflect.KClass

interface SourcererComponent {

    val serializerService: SerializerComponent<*>

    companion object {
        lateinit var instance: SourcererComponent
    }
}

internal fun serviceLocator(): SourcererComponent = SourcererComponent.instance

class SourcererModule (
        override val serializerService: SerializerComponent<*>
): SourcererComponent


// public api
object SourcererService {
    private val serializerService by lazy { serviceLocator().serializerService }

    fun bootstrap(context: Context, serializerComponent: SerializerComponent<*>?) {
        val selectedSerializer = serializerComponent ?: SerializerModule(context)
        SourcererComponent.instance = SourcererModule(selectedSerializer)
    }

    fun registerAdapter(subjectType: KClass<*>, adapterType: KClass<*>, elementName: String? = null)
            = serializerService.registerAdapter(subjectType, adapterType, elementName)

    inline fun <reified T : Any> deserialize(json: String): T
        = SerializerComponent.deserialize(json)
}