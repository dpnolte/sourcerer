package com.laidpack.sourcerer.service.test

import android.content.Context
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
    private var mutableSerializerService : SerializerModule? = null
    private val serializerService : SerializerModule
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
                DimensionQualifier::class to dimensionAdapter,
                ReferenceQualifier::class to referenceAdapter,
                ColorQualifier::class to colorAdapter,
                MultiFormat::class to MultiFormatAdapterFactory()
        ))
        serializerService.registerAdapter(DefaultLayoutParamsAttributes::class, DefaultLayoutParamsAdapter())
        serializerService.assignDefaultLayoutParameterDelegate(DefaultLayoutParamsAttributes::class)
    }

    @Test
    fun `success - registering and deserializing adapter`() {
        serializerService.registerAdapter(DummyElement::class, DummyElementAdapter()).buildAdapters()
        val json = "{ \"a\": \"aap\", \"b\": 2 }"
        val result = serializerService.deserialize(DummyElement::class, json)

        result shouldBeInstanceOf DummyElement::class
        val element = result as DummyElement
        element.a shouldBeEqualTo "aap"
        element.b shouldEqualTo 2
    }

    @Test
    fun `success - registering and deserializing LayoutMap and LayoutElement`() {
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
    fun `failure - having multiple root elements`() {
        serializerService.registerAdapter(DummyDimensionElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": [], "element": "dummyElement2", "attributes":{ "dimension": "10mm" }}
            |]""".trimMargin()
        val deserialize = {serializerService.deserialize(LayoutMap::class, json)}

        deserialize shouldThrow JsonDataException::class
    }


    @Test
    fun `success - element with dimension qualifier`() {
        serializerService.registerAdapter(DummyDimensionElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": ["test2"], "element": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": ["test3"], "element": "dummyElement2", "attributes":{ "dimension": "10mm" }},
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
    fun `success - element with reference qualifier`() {
        serializerService.registerAdapter(DummyReferenceElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": ["test2"], "element": "dummyElement2", "attributes":{ "reference": "R.TextAppearance_DeviceDefault_Small" }},
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
    fun `success - element with color qualifier`() {
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "element": "dummyElement2", "attributes":{ "color": "#123456" }},
            |   { "id": "test2", "children": ["test3"], "element": "dummyElement2", "attributes":{ "color": "#333" }},
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
    fun `success - element with multi-format qualifier`() {
        serializerService.registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "element": "dummyElement2", "attributes":{ "booleanOrString": true }},
            |   { "id": "test2", "children": ["test3"], "element": "dummyElement2", "attributes":{ "booleanOrString": "String" }},
            |   { "id": "test3", "children": ["test4"], "element": "dummyElement2", "attributes":{ "booleanOrString": "true" }},
            |   { "id": "test4", "children": ["test5"], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "test5", "children": ["test6"], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12" }},
            |   { "id": "test6", "children": ["test7"], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12sp" }},
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

    @Test
    fun `success - combining element and layout params attributes`() {
        serializerService.registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElement2")
                .registerAdapter(LayoutParamsAttributes::class, LayoutParamsAdapter(), "layoutParams")
                .associateThisViewGroupWithLayoutParams("dummyElement2", "layoutParams")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "lpTest1", "children": ["lpTest2"], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "lpTest2", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "#123", "layout_height": 300, "layout_width": 200  }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 2
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatElement::class
            val attrs = element.attributes as DummyMultiFormatElement
            when (index) {
                0 -> {
                    attrs.referenceColorOrDimension.reference `should be equal to` 999
                } // see setup
                1 -> {
                    attrs.referenceColorOrDimension.color `should be equal to` 112233
                    element.layoutParamsElementName shouldBe "layoutParams"
                    element.layoutAttributes shouldBeInstanceOf LayoutParamsAttributes::class
                    val layoutAttrs = element.layoutAttributes as LayoutParamsAttributes
                    layoutAttrs.height shouldEqualTo 300
                    layoutAttrs.width shouldEqualTo 200
                }
            }
        }
    }

    @Test
    fun `success - reverting to default layout params attributes`() {
        serializerService.registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElement2")
                .registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElementWithoutLinkedLP")
                .registerAdapter(LayoutParamsAttributes::class, LayoutParamsAdapter(), "layoutParams")
                .associateThisViewGroupWithLayoutParams("dummyElement2", "layoutParams")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "lpTest1", "children": ["lpTest2", "lpTest3"], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "lpTest2", "children": [], "element": "dummyElement2", "attributes":{ "referenceColorOrDimension": "#123", "layout_height": 300, "layout_width": 200  }},
            |   { "id": "lpTest3", "children": ["lpTest4"], "element": "dummyElementWithoutLinkedLP", "attributes":{ "referenceColorOrDimension": "R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "lpTest4", "children": [], "element": "dummyElementWithoutLinkedLP", "attributes":{ "referenceColorOrDimension": "#123", "layout_default": true }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.elements.size shouldEqualTo 4
        map.elements.values.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatElement::class
            val attrs = element.attributes as DummyMultiFormatElement
            when (index) {
                0 -> {
                    attrs.referenceColorOrDimension.reference `should be equal to` 999
                } // see setup
                1 -> {
                    attrs.referenceColorOrDimension.color `should be equal to` 112233
                    element.layoutParamsElementName shouldBe "layoutParams"
                    element.layoutAttributes shouldBeInstanceOf LayoutParamsAttributes::class
                    val layoutAttrs = element.layoutAttributes as LayoutParamsAttributes
                    layoutAttrs.height shouldEqualTo 300
                    layoutAttrs.width shouldEqualTo 200
                }
                2 -> {
                    attrs.referenceColorOrDimension.reference `should be equal to` 999
                }
                3 -> {
                    attrs.referenceColorOrDimension.color `should be equal to` 112233
                    (element.layoutParamsElementName as String) shouldBeEqualTo DefaultLayoutParamsAttributes::class.java.canonicalName as String
                    element.layoutAttributes shouldBeInstanceOf DefaultLayoutParamsAttributes::class
                    val layoutAttrs = element.layoutAttributes as DefaultLayoutParamsAttributes
                    layoutAttrs.default shouldBe true
                }
            }
        }
    }

    class LayoutParamsAttributes(val width: Int, val height: Int) : IAttributes
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

    class LayoutParamsAdapter {
        private val options: JsonReader.Options =
                JsonReader.Options.of("layout_width", "layout_height")
        @FromJson
        fun fromJson(reader: JsonReader): LayoutParamsAttributes? {
            reader.beginObject()
            var width : Int? = null
            var height : Int? = null
            while (reader.hasNext()) {
                when (reader.selectName(options)) {
                    0 -> width = reader.nextInt() // ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> height = reader.nextInt() //?: throw JsonDataException("Non-null value 'elementName' was null at ${reader.path}")
                    -1 -> {
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()
            return LayoutParamsAttributes(width as Int, height as Int)
        }

        @ToJson
        fun toJson(writer: JsonWriter, value: LayoutParamsAttributes?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class DefaultLayoutParamsAttributes(val default: Boolean?) : IAttributes
    class DefaultLayoutParamsAdapter : JsonAdapter<DefaultLayoutParamsAttributes>() {
        private val options: JsonReader.Options =
                JsonReader.Options.of("layout_default")
        override fun fromJson(reader: JsonReader): DefaultLayoutParamsAttributes? {
            reader.beginObject()
            var default : Boolean? = null
            while (reader.hasNext()) {
                when (reader.selectName(options)) {
                    0 -> default = reader.nextBoolean() // ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    -1 -> {
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()
            return DefaultLayoutParamsAttributes(default)
        }

        override fun toJson(writer: JsonWriter, value: DefaultLayoutParamsAttributes?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}