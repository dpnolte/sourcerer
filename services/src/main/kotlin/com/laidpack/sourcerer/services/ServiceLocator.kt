package com.laidpack.sourcerer.services

import android.content.Context
import android.view.ViewGroup
import kotlin.reflect.KClass

interface SourcererComponent {

    val serializer: SerializerComponent
    val inflater: InflaterComponent

    companion object {
        lateinit var instance: SourcererComponent

        fun bootstrap(
                context: Context,
                defaultLayoutParamType: KClass<*> = ViewGroup.LayoutParams::class
        ): SourcererComponent {
            val serializer = SerializerComponent.bootstrap(context, defaultLayoutParamType)
            val inflater = InflaterComponent.bootstrap()
            val serviceBootstrapper = ServicesBootstrapper()
            serviceBootstrapper.bootstrap(serializer, inflater)
            serializer.buildAdapters()
            instance = SourcererModule(
                    serializer,
                    inflater
            )
            return instance
        }

    }
}

internal fun serviceLocator(): SourcererComponent = SourcererComponent.instance

class SourcererModule (
        override val serializer: SerializerComponent,
        override val inflater: InflaterComponent
): SourcererComponent

