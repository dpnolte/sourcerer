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
        flagsType: KClass<out AttributeEnum>
) : JsonAdapter<FlagsAccumulator>() {
    private val allPossibleFlagValues = flagsType.java.enumConstants.associateBy { it.key }

    override fun fromJson(reader: JsonReader): FlagsAccumulator? {
        val token = reader.peek()
        return when (token) {
            JsonReader.Token.STRING -> accumulateFlagsFromString(reader.nextString())
            JsonReader.Token.BEGIN_ARRAY -> accumulateFlagsFromSet(arrayToStringSet(reader))
            else -> throw JsonDataException("Invalid flags format format. Expected either string or array, received ${token.name}.")
        }
    }

    private fun accumulateFlagsFromString(input: String): FlagsAccumulator {
        val stringValues = input.split("|")
        return accumulateFlags(stringValues)
    }

    private fun accumulateFlagsFromSet(input: Set<String>): FlagsAccumulator {
        return accumulateFlags(input)
    }

    private fun accumulateFlags(flags: Collection<String>): FlagsAccumulator {
        var accumulatedValue = 0
        val flagValues = mutableListOf<AttributeEnum>()
        for (stringValue in flags) {
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

    private fun arrayToStringSet(reader: JsonReader): Set<String> {
        val result = mutableSetOf<String>()
        reader.beginArray()
        while(reader.hasNext()) {
            val flag = reader.nextString()
            if (!result.contains(flag)) {
                result.add(flag)
            }
        }
        reader.endArray()
        return result
    }

    override fun toJson(writer: JsonWriter, value: FlagsAccumulator?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}