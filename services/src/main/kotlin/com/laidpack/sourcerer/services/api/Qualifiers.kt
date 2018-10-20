package com.laidpack.sourcerer.services.api

import com.squareup.moshi.JsonQualifier
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class ColorQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DimensionQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class MultiFormatQualifier (
        val formats : Array<Format>,
        val enumType: KClass<out AttributeEnum> = MissingType::class,
        val flagsType: KClass<out AttributeEnum> = MissingType::class
)
interface MissingType : AttributeEnum

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class ReferenceQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class FlagsQualifier(
        val flagsType: KClass<out AttributeEnum>
)
