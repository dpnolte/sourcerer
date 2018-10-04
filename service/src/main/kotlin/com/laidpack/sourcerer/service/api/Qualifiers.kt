package com.laidpack.sourcerer.service.api

import com.squareup.moshi.JsonQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class ColorQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DimensionQualifier

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class MultiFormatQualifier (val formats : Array<Format>)

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class ReferenceQualifier