package com.laidpack.sourcerer.services.adapters

import com.laidpack.sourcerer.services.api.*
import com.squareup.moshi.*
import java.lang.reflect.Type

// TODO.. move this to a jsonfactory? There we are supposed to delegate to adapters and we can
// let's check this in a different module when we have our view factories
// see also here as reference: https://stackoverflow.com/questions/49490641/passing-information-between-moshi-custom-type-adaptors

internal class MultiFormatAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val annotation = annotations.find {
            it.annotationClass == qualifierType
        } ?: return null

        val multiFormatQualifier = annotation as MultiFormatQualifier
        return MultiFormatAdapter(getSortedFormats(multiFormatQualifier), moshi)

    }

    private fun getSortedFormats(multiFormatQualifier: MultiFormatQualifier): Set<Format> {
        return multiFormatQualifier.formats.sortedWith (
                compareBy(
                        { it != Format.Reference },
                        { it != Format.Color },
                        { it != Format.Dimension }
                )
        ).toSet()
    }

    companion object {
        val qualifierType = MultiFormatQualifier::class
    }
}


internal class MultiFormatAdapter(private val allowedFormats: Set<Format>, private val moshi: Moshi) : JsonAdapter<MultiFormat>() {
    override fun fromJson(reader: JsonReader): MultiFormat? {
        val multiFormatValue = MultiFormat(allowedFormats)
        val jsonValue = reader.readJsonValue()
        val stringValue = jsonValue.toString()
        val delegates = MultiFormat.getAdaptersMap(moshi)
        for (format in allowedFormats) {
            try {
                val delegateProvider = delegates[format] ?: continue
                val result = delegateProvider().fromJsonValue(jsonValue) ?: continue
                multiFormatValue.setValue(format, result)
                break
            } catch (e: JsonDataException) {
                continue
            }
        }
        if (!multiFormatValue.hasAnyValue) {
            throw JsonDataException("Could not convert '$stringValue' into one of the following formats: '${allowedFormats.joinToString()}'")
        }

        return multiFormatValue
    }

    override fun toJson(writer: JsonWriter, value: MultiFormat?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}