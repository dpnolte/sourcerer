package com.laidpack.sourcerer.service

import android.content.Context
import android.util.Log
import com.laidpack.sourcerer.service.adapters.*
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.*
import java.lang.NullPointerException
import kotlin.reflect.KClass


interface SerializerComponent<TAdapter> {
    fun deserialize(type: KClass<*>, json: String): Any
    fun registerAdapter(subjectType: KClass<*>, adapterType: KClass<*>, elementName: String? = null): SerializerComponent<TAdapter>
    fun addAdapterFactory(adapter: Any): SerializerComponent<TAdapter>
    fun buildAdapters() : SerializerComponent<TAdapter>
    fun getAdapter(subjectType: KClass<*>): TAdapter
    fun getAttributeAdapter(elementName: String): TAdapter

    companion object {
        val instance by lazy { serviceLocator().serializerService }
        inline fun <reified T : Any> deserialize(json: String): T {
            return instance.deserialize(T::class, json) as T
        }
    }
}


class SerializerModule(
        defaultAdapters: List<Any> = listOf(
                DimensionAdapter(),
                ReferenceAdapter()
        )
) : SerializerComponent<JsonAdapter<out Any>> {
    private val registeredAdapters = mutableSetOf<KClass<*>>()
    private val elementNameToSubjectType = mutableMapOf<String, KClass<*>>()
    private lateinit var moshi: Moshi
    private var builder = Moshi.Builder()

    // add default adapters
    init {
        if (BuildConfig.DEBUG) {
            addAdapterFactory(StrictAdapterFactory())
        }
        registerAdapter(LayoutMap::class, LayoutMapAdapter::class)
        for(adapter in defaultAdapters) {
            builder.add(adapter)
        }
        addAdapterFactory(LayoutAdapterFactory {
            elementNameToSubjectType
        })
    }

    override fun deserialize(type: KClass<*>, json: String): Any {
        val adapter = this.getAdapter(type)
        return adapter.fromJson(json)
                ?: throw NullPointerException("Adapter for '$type' resulted in null")
    }

    override fun addAdapterFactory(adapter: Any): SerializerComponent<JsonAdapter<out Any>> {
        if (adapter !is JsonAdapter.Factory) throw IllegalArgumentException("adapter factory must be of type JsonAdapter.Factory")
        builder.add(adapter)
        return this
    }

    override fun registerAdapter(
            subjectType: KClass<*>,
            adapterType: KClass<*>,
            elementName: String?
    ): SerializerComponent<JsonAdapter<out Any>> {
        // if it is derived from jsonadapter, then it is an auto generated adapter, add it to factory..
        //val type = Types.getRawType(subjectType::class.javaObjectType)
        //val annotation = type.getAnnotation(JsonClass::class.java)
        // TODO: can't we solve this without reflection? e.g., using a provider fun
        val prebuiltAdapter = jsonAdapterType.isAssignableFrom(adapterType.java)
        val requiresMoshiAsParameter = prebuiltAdapter && try {
            adapterType.java.getDeclaredConstructor(Moshi::class.java)
            true
        } catch(e: NoSuchMethodException) {
            false
        }
        when {
            prebuiltAdapter && requiresMoshiAsParameter -> {
                addAdapterFactory(GeneratedAdapterFactory(subjectType, adapterType))
                registeredAdapters.add(subjectType)
            }
            else -> // type is not yet an adapter.. instantiate it, and add to moshi builder
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
        if (elementName != null) {
            elementNameToSubjectType[elementName] = subjectType
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
        return moshi.adapter(subjectType.java) as JsonAdapter<out Any>
    }

    override fun getAttributeAdapter(elementName: String): JsonAdapter<out Any> {
        val subjectType = elementNameToSubjectType[elementName]
            ?: throw IllegalArgumentException("Layout element '$elementName' has no registered adapter @ serializer service")

        return getAdapter(subjectType)
    }

    companion object {
        const val TAG = "SerializerModule"
        private val jsonAdapterType = JsonAdapter::class.java
    }
}

