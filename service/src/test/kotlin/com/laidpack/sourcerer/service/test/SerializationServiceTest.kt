package com.laidpack.sourcerer.service.test

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import com.laidpack.sourcerer.service.LayoutMap
import com.laidpack.sourcerer.service.SerializerModule
import com.laidpack.sourcerer.service.adapters.DimensionAdapter
import com.laidpack.sourcerer.service.adapters.LayoutAdapterFactory
import com.laidpack.sourcerer.service.adapters.LayoutMapAdapter
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.*
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SerializationServiceTest {
    var mutableSerializerService : SerializerModule? = null
    val serializerService : SerializerModule
        get() = mutableSerializerService as SerializerModule

    @Before
    fun setUp() {
        mutableSerializerService =  SerializerModule(listOf(
                DimensionAdapter(
                        Mockito.mock(DisplayMetrics::class.java)
                ) { unit, value, metrics ->
                    when (unit) {
                        TypedValue.COMPLEX_UNIT_SP -> 2 * value
                        TypedValue.COMPLEX_UNIT_MM -> 0.5f * value
                        else -> throw NotImplementedError("Not implemented for testing")
                    }
                }

        ))
    }

    @Test
    fun `return success when registering and deserializing DummyElement`() {
        serializerService.registerAdapter(DummyElement::class, DummyElementAdapter::class).buildAdapters()
        val json = "{ \"a\": \"aap\", \"b\": 2 }"
        val result = serializerService.deserialize(DummyElement::class, json)

        result shouldBeInstanceOf DummyElement::class
        val element = result as DummyElement
        element.a shouldBeEqualTo "aap"
        element.b shouldEqualTo 2
    }

    @Test
    fun `return success when registering and deserializing LayoutMap and DummyElement as Attributes`() {
        serializerService.registerAdapter(DummyElement::class, DummyElementAdapter::class, "dummyElement").buildAdapters()
        val json = """[ { "id": "test", "children": [], "element": "dummyElement", "attributes":{ "a": "aap", "b": 2 }} ]"""
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 1
        val element = map.elements.values.first()
        element.id shouldBeEqualTo "test"
        element.elementName shouldBeEqualTo "dummyElement"
        val attributes = element.attributes as DummyElement
        attributes.a shouldBeEqualTo "aap"
        attributes.b shouldEqualTo 2
    }


    @Test
    fun `return success when using dummy element with dimension qualifier`() {
        serializerService.registerAdapter(DummyDimensionElement::class, DummyDimensionAdapter::class, "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "10mm" }},
            |   { "id": "test3", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "8" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result should {result is LayoutMap}
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 3
        val expectedValues = listOf(24, 5 , 8) // see setup
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyDimensionElement::class
            val attrs = element.attributes as DummyDimensionElement
            attrs.dimension shouldEqualTo expectedValues[index]
        }
    }




    data class DummyElement(val a: String, val b: Int) : IAttributes
    class DummyElementAdapter {
        private val options: JsonReader.Options =
                JsonReader.Options.of("a", "b")
        @FromJson
        fun fromJson(reader: JsonReader): DummyElement? {
            reader.beginObject()
            var a : String? = null
            var b : Int? = null
            while (reader.hasNext()) {
                when (reader.selectName(options)) {
                    0 -> a = reader.nextString() ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> b = reader.nextInt() //?: throw JsonDataException("Non-null value 'elementName' was null at ${reader.path}")
                    -1 -> {
                        throw JsonDataException("Invalid property at ${reader.path}")
                    }
                }
            }
            reader.endObject()
            return DummyElement(a as String, b as Int)
        }

        @ToJson
        fun toJson(writer: JsonWriter, value: DummyElement?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class DummyDimensionElement(
            @field:DimensionQualifier
            val dimension: Int,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyDimensionAdapter {
        private val options: JsonReader.Options =
                JsonReader.Options.of("a", "b")
        @FromJson
        fun fromJson(reader: JsonReader): DummyElement? {
            reader.beginObject()
            var a : String? = null
            var b : Int? = null
            while (reader.hasNext()) {
                when (reader.selectName(options)) {
                    0 -> a = reader.nextString() ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> b = reader.nextInt() //?: throw JsonDataException("Non-null value 'elementName' was null at ${reader.path}")
                    -1 -> {
                        throw JsonDataException("Invalid property at ${reader.path}")
                    }
                }
            }
            reader.endObject()
            return DummyElement(a as String, b as Int)
        }

        @ToJson
        fun toJson(writer: JsonWriter, value: DummyElement?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}