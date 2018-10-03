package com.laidpack.sourcerer.service.api

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
annotation class MultiFormatQualifier (val formats : Array<KClass<*>>)

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class ReferenceQualifier