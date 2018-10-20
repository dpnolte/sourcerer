package com.laidpack.sourcerer.services.adapters

import com.laidpack.sourcerer.services.api.*
import com.squareup.moshi.*
import java.lang.reflect.Type
import kotlin.reflect.KClass


internal class FlagsAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val annotation = annotations.find {
            it.annotationClass == qualifierType
        } ?: return null

        val flagsQualifier = annotation as FlagsQualifier
        return FlagsAdapter(
                flagsQualifier.flagsType
        )

    }

    companion object {
        val qualifierType = FlagsQualifier::class
    }
}


internal class FlagsAdapter(
        private val flagsType: KClass<out AttributeEnum>
) : JsonAdapter<FlagsAccumulator>() {
    override fun fromJson(reader: JsonReader): FlagsAccumulator? {
        val providedString = reader.nextString()
        val allPossibleFlagValues = flagsType.java.enumConstants.associateBy { it.key }
        val stringValues = providedString.split("|")
        var accumulatedValue = 0
        val flagValues = mutableListOf<AttributeEnum>()
        for (stringValue in stringValues) {
            if (!allPossibleFlagValues.containsKey(stringValue)) {
                throw JsonDataException("'$stringValue' is not a valid flag key. Allowed keys: ${allPossibleFlagValues.keys.joinToString()}")
            }
            val flagValue = allPossibleFlagValues[stringValue] as AttributeEnum
            flagValues.add(flagValue)
            accumulatedValue = accumulatedValue.or(flagValue.value)

        }
        return FlagsAccumulator(
                accumulatedValue,
                flagValues
        )
    }

    override fun toJson(writer: JsonWriter, value: FlagsAccumulator?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}