package com.laidpack.sourcerer.service

import android.content.Context
import kotlin.reflect.KClass

interface SourcererComponent {

    val serializerService: SerializerComponent
    val inflaterService: InflaterComponent

    companion object {
        lateinit var instance: SourcererComponent

        fun bootstrap(
                context: Context,
                defaultLayoutParamType: KClass<*>
        ) {
            val serializerModule = SerializerModule()
            serializerModule.assignContext(context)
            serializerModule.assignDefaultLayoutParameterDelegate(defaultLayoutParamType)
            instance = SourcererModule(
                    serializerModule,
                    InflaterModule()
            )

        }

    }
}

internal fun serviceLocator(): SourcererComponent = SourcererComponent.instance

class SourcererModule (
        override val serializerService: SerializerComponent,
        override val inflaterService: InflaterComponent
): SourcererComponent

