package com.laidpack.generator.api

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
annotation class ViewElement(
        val elementType: String,
        val attributesClazz: KClass<*>
)

@Retention(AnnotationRetention.SOURCE)
annotation class ViewGroupElement(
        val elementType: String,
        val attributesClazz: KClass<*>,
        val layoutParamAttributesClazz: KClass<*>
)

@Retention(AnnotationRetention.SOURCE)
annotation class LayoutParamsElement(
        val elementType: String,
        val attributesClazz: KClass<*>
)

