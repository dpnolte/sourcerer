package com.laidpack.sourcerer.service

import android.util.Log
import com.laidpack.sourcerer.service.adapters.GeneratedAdaptersFactory
import com.laidpack.sourcerer.service.adapters.StrictAdaptersFactory
import com.squareup.moshi.*
import java.lang.NullPointerException
import kotlin.reflect.KClass


interface SerializerComponent<TAdapter> {
    fun deserialize(type: KClass<*>, json: String): Any
    fun registerAdapter(subjectType: KClass<*>, adapterType: KClass<*>): SerializerComponent<TAdapter>
    fun addAdapter(adapter: Any): SerializerComponent<TAdapter>
    fun buildAdapters() : SerializerComponent<TAdapter>
    fun getAdapter(subjectType: KClass<*>): TAdapter
    fun getAttributeAdapter(elementName: String): TAdapter

    companion object {
        val instance by lazy { serviceLocator().serializerService }
        inline fun <reified T : Any> deserialize(json: String): T {
            return instance.deserialize(T::class, json)
        }
    }
}


class SerializerModule : SerializerComponent<JsonAdapter<out Any>> {
    private val registeredAdapters = mutableSetOf<KClass<*>>()
    private val elementNameToSubjectType = HashMap<String, KClass<*>>()
    private lateinit var moshi: Moshi
    private var builder = Moshi.Builder()

    init {
        if (BuildConfig.DEBUG) {
            builder.add(StrictAdaptersFactory())
        }
    }

    override fun deserialize(type: KClass<*>, json: String): Any {
        val adapter = this.getAdapter(type)
        return adapter.fromJson(json)
                ?: throw NullPointerException("Adapter for '$type' resulted in null")
    }

    override fun addAdapter(adapter: Any): SerializerComponent<JsonAdapter<out Any>> {
        builder.add(adapter)
        return this
    }

    override fun registerAdapter(subjectType: KClass<*>, adapterType: KClass<*>): SerializerComponent<JsonAdapter<out Any>> {
        // if it is derived from jsonadapter, then it is an auto generated adapter, add it to factory..
        val type = Types.getRawType(subjectType::class.javaObjectType)
        val annotation = type.getAnnotation(JsonClass::class.java)
        if (adapterType.java.superclass == JsonAdapter::class.java) {
            builder.add(GeneratedAdaptersFactory(subjectType, adapterType))
            registeredAdapters.add(subjectType)
        } else {
            // type is not yet an adapter.. instantiate it, and add to moshi builder
            try {
                val instance = adapterType.java.newInstance()
                builder.add(instance)
                registeredAdapters.add(subjectType)

            } catch (e: InstantiationException) {
                val msg = "Could not instantiate $adapterType as json adapter. Does it have a parameterless constructor? \n\n${e.message}\n\n${e.stackTrace}"
                if (BuildConfig.DEBUG) throw InstantiationException(msg)
                else Log.e(TAG, msg)
            }
        }
        return this
    }

    override fun buildAdapters(): SerializerComponent<JsonAdapter<out Any>> {
        moshi = builder.build()

        return this
    }

    override fun getAdapter(subjectType: KClass<*>): JsonAdapter<out Any> {
        if (!registeredAdapters.contains(subjectType)) {
            val msg = "Deserialization failed: class '$subjectType' is not registered as json adapter"
            if (BuildConfig.DEBUG) throw IllegalArgumentException(msg)
            else Log.e(TAG, msg)
        }
        return moshi.adapter(subjectType.javaObjectType) as JsonAdapter<out Any>
    }

    override fun getAttributeAdapter(elementName: String): JsonAdapter<out Any> {
        val subjectType = elementNameToSubjectType[elementName]
            ?: throw IllegalArgumentException("Layout element '$elementName' has no registered adapter @ serializer service")

        return getAdapter(subjectType)
    }


    companion object {
        const val TAG = "SerializerModule"
    }
}

