package com.laidpack.sourcerer.service.test

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import com.laidpack.sourcerer.service.LayoutMap
import com.laidpack.sourcerer.service.SerializerModule
import com.laidpack.sourcerer.service.adapters.*
import com.laidpack.sourcerer.service.api.*
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
        val dimensionAdapter = DimensionAdapter(
                Mockito.mock(DisplayMetrics::class.java)
        ) { unit, value, metrics ->
            when (unit) {
                TypedValue.COMPLEX_UNIT_SP -> 2 * value
                TypedValue.COMPLEX_UNIT_MM -> 0.5f * value
                else -> throw NotImplementedError("Not implemented for testing")
            }
        }
        val referenceAdapter = ReferenceAdapter(
            { name, c, type ->
                when (type) {
                    "drawable" -> 100
                    "style" -> 999
                    else -> throw NotImplementedError("Not implemented for testing")
                }
            }
        )
        referenceAdapter.context = Mockito.mock(Context::class.java)
        val colorAdapter = ColorAdapter {
            it.substring(1).toInt()
        }
        mutableSerializerService =  SerializerModule(listOf(
                dimensionAdapter,
                referenceAdapter,
                colorAdapter
        ))
    }

    @Test
    fun `return success when registering and deserializing DummyElement`() {
        serializerService.registerAdapter(DummyElement::class, DummyElementAdapter()).buildAdapters()
        val json = "{ \"a\": \"aap\", \"b\": 2 }"
        val result = serializerService.deserialize(DummyElement::class, json)

        result shouldBeInstanceOf DummyElement::class
        val element = result as DummyElement
        element.a shouldBeEqualTo "aap"
        element.b shouldEqualTo 2
    }

    @Test
    fun `return success when registering and deserializing LayoutMap and DummyElement as Attributes`() {
        serializerService.registerAdapter(DummyElement::class, DummyElementAdapter(), "dummyElement").buildAdapters()
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
        serializerService.registerAdapter(DummyDimensionElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "10mm" }},
            |   { "id": "test3", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "8" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 3
        val expectedValues = listOf(24, 5 , 8) // see setup
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyDimensionElement::class
            val attrs = element.attributes as DummyDimensionElement
            attrs.dimension shouldEqualTo expectedValues[index]
        }
    }

    @Test
    fun `return success when using dummy element with reference qualifier`() {
        serializerService.registerAdapter(DummyReferenceElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": [], "element": "dummyElement2", "attributes":{ "reference": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "reference": "@drawable:R.nav_icon" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 2
        val expectedValues = listOf(999, 100) // see setup
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyReferenceElement::class
            val attrs = element.attributes as DummyReferenceElement
            attrs.reference shouldEqualTo expectedValues[index]
        }
    }

    @Test
    fun `return success when using dummy element with color qualifier`() {
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": [], "element": "dummyElement2", "attributes":{ "color": "#123456" }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "color": "#333" }},
            |   { "id": "test3", "children": [], "element": "dummyElement2", "attributes":{ "color": "#99123456" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 3
        val expectedValues = listOf(123456, 333333, 99123456) // see setup
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyColorElement::class
            val attrs = element.attributes as DummyColorElement
            attrs.color shouldEqualTo expectedValues[index]
        }
    }

    @Test
    fun `return success when using dummy element with multi-format qualifier`() {
        serializerService.registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": [], "element": "dummyElement2", "attributes":{ "booleanOrString": true }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "booleanOrString": "String" }},
            |   { "id": "test3", "children": [], "element": "dummyElement2", "attributes":{ "booleanOrString": "true" }},
            |   { "id": "test4", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "test5", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12" }},
            |   { "id": "test6", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12sp" }},
            |   { "id": "test7", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "#123" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 7
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatElement::class
            val attrs = element.attributes as DummyMultiFormatElement
            when (index) {
                0 -> attrs.booleanOrString.boolean.shouldBeTrue()
                1 -> attrs.booleanOrString.string shouldBeEqualTo "String"
                2 -> attrs.booleanOrString.string shouldBeEqualTo "true"
                3 -> attrs.referenceColorOrDimension.reference `should be equal to` 999 // see setup
                4 -> attrs.referenceColorOrDimension.dimension `should be equal to` 12
                5 -> attrs.referenceColorOrDimension.dimension `should be equal to` 24
                6 -> attrs.referenceColorOrDimension.color `should be equal to` 112233
            }
        }
    }

    class DummyElement(val a: String, val b: Int) : IAttributes
    class DummyDimensionElement(
            @field:DimensionQualifier
            val dimension: Int,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyReferenceElement(
            @field:ReferenceQualifier
            val reference: Int,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyColorElement(
            @field:ColorQualifier
            val color: Int,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyMultiFormatElement(
            @field:MultiFormatQualifier([Format.Boolean, Format.String])
            val booleanOrString: MultiFormat = MultiFormat(),
            @field:MultiFormatQualifier([Format.Dimension, Format.Color, Format.Reference])
            val referenceColorOrDimension: MultiFormat = MultiFormat(),
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes

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

}