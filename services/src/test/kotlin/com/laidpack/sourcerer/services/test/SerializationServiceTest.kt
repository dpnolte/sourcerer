package com.laidpack.sourcerer.services.test

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import com.laidpack.sourcerer.services.layout.LayoutMap
import com.laidpack.sourcerer.services.layout.LayoutProperties
import com.laidpack.sourcerer.services.SerializerModule
import com.laidpack.sourcerer.services.adapters.*
import com.laidpack.sourcerer.services.api.*
import com.squareup.moshi.*
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.ref.WeakReference

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
            { name, c, type, p ->
                when (type) {
                    "drawable" -> 100
                    "style" -> 999
                    else -> throw NotImplementedError("Not implemented for testing")
                }
            }
        )
        val mockedContext = Mockito.mock(Context::class.java)
        referenceAdapter.context = WeakReference(mockedContext)
        val colorAdapter = ColorAdapter {
            it.substring(1).toInt()
        }
        mutableSerializerService =  SerializerModule(
                dimensionAdapter,
                referenceAdapter,
                colorAdapter,
                MultiFormatAdapterFactory(),
                FlagsAdapterFactory()
        )
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
        val json = """[ { "id": "test", "children": [], "type": "dummyElement", "attributes":{ "a": "aap", "b": 2 }} ]"""
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 1
        val element = map.first()
        element.id shouldBeEqualTo "test"
        element.type shouldBeEqualTo "dummyElement"
        val attributes = element.attributes as DummyElement
        attributes.a shouldBeEqualTo "aap"
        attributes.b shouldEqualTo 2
    }

    @Test
    fun `failure - having multiple root elements`() {
        serializerService.registerAdapter(DummyDimensionElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": [], "type": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": [], "type": "dummyElement2", "attributes":{ "dimension": "10mm" }}
            |]""".trimMargin()
        val deserialize = {serializerService.deserialize(LayoutMap::class, json)}

        deserialize shouldThrow JsonDataException::class
    }


    @Test
    fun `success - element with dimension qualifier`() {
        serializerService.registerAdapter(DummyDimensionElement::class, DummyElementAdapter(), "dummyElement2")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": ["test2"], "type": "dummyElement2", "attributes":{ "dimension": "12sp" }},
            |   { "id": "test2", "children": ["test3"], "type": "dummyElement2", "attributes":{ "dimension": "10mm" }},
            |   { "id": "test3", "children": [], "type": "dummyElement2", "attributes":{ "dimension": "8" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 3
        val expectedValues = listOf(24, 5 , 8) // see setup
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyDimensionElement::class
            val attrs = element.attributes as DummyDimensionElement
            attrs.dimension shouldBe expectedValues[index]
        }
    }

    @Test
    fun `success - element with reference qualifier`() {
        serializerService.registerAdapter(DummyReferenceElement::class, DummyElementAdapter(), "dummyElement2")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "test", "children": ["test2"], "type": "dummyElement2", "attributes":{ "reference": "@style/TextAppearance_DeviceDefault_Small" }},
            |   { "id": "test2", "children": [], "type": "dummyElement2", "attributes":{ "reference": "@drawable/nav_icon" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 2
        val expectedValues = listOf(999, 100) // see setup
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyReferenceElement::class
            val attrs = element.attributes as DummyReferenceElement
            attrs.reference as Int shouldEqualTo expectedValues[index]
        }
    }

    @Test
    fun `success - element with color qualifier`() {
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement2")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "type": "dummyElement2", "attributes":{ "color": "#123456" }},
            |   { "id": "test2", "children": ["test3"], "type": "dummyElement2", "attributes":{ "color": "#333" }},
            |   { "id": "test3", "children": [], "type": "dummyElement2", "attributes":{ "color": "#99123456" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 3
        val expectedValues = listOf(123456, 333333, 99123456) // see setup
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyColorElement::class
            val attrs = element.attributes as DummyColorElement
            attrs.color as Int shouldEqualTo expectedValues[index]
        }
    }

    @Test
    fun `success - element with multi-format qualifier`() {
        serializerService.registerAdapter(DummyMultiFormatElement::class, DummyElementAdapter(), "dummyElement2")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "type": "dummyElement2", "attributes":{ "booleanOrString": true }},
            |   { "id": "test2", "children": ["test3"], "type": "dummyElement2", "attributes":{ "booleanOrString": "String" }},
            |   { "id": "test3", "children": ["test4"], "type": "dummyElement2", "attributes":{ "booleanOrString": "true" }},
            |   { "id": "test4", "children": ["test5"], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "@style/R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "test5", "children": ["test6"], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12" }},
            |   { "id": "test6", "children": ["test7"], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "12sp" }},
            |   { "id": "test7", "children": [], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "#123" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 7
        map.forEachIndexed { index, element ->
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
                .associateViewGroupWithLayoutParams("dummyElement2", "layoutParams")
                .buildAdapters()
        val json = """
            |[
            |   { "id": "lpTest1", "children": ["lpTest2"], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "@style/R.TextAppearance_DeviceDefault_Small" }},
            |   { "id": "lpTest2", "children": [], "type": "dummyElement2", "attributes":{ "referenceColorOrDimension": "#123", "layout_height": 300, "layout_width": 200  }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 2
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatElement::class
            val attrs = element.attributes as DummyMultiFormatElement
            when (index) {
                0 -> {
                    attrs.referenceColorOrDimension.reference `should be equal to` 999
                } // see setup
                1 -> {
                    attrs.referenceColorOrDimension.color `should be equal to` 112233
                    element.layoutParamsType shouldBe "layoutParams"
                    element.layoutAttributes shouldBeInstanceOf LayoutParamsAttributes::class
                    val layoutAttrs = element.layoutAttributes as LayoutParamsAttributes
                    layoutAttrs.height shouldEqualTo 300
                    layoutAttrs.width shouldEqualTo 200
                }
            }
        }
    }

    @Test
    fun `success - layout map tracks elements per type`() {
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement3").buildAdapters()
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        serializerService.registerAdapter(DummyColorElement::class, DummyElementAdapter(), "dummyElement").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "type": "dummyElement", "attributes":{ "color": "#123456" }},
            |   { "id": "test2", "children": ["test3"], "type": "dummyElement", "attributes":{ "color": "#333" }},
            |   { "id": "test3", "children": ["test4"], "type": "dummyElement2", "attributes":{ "color": "#99123456" }},
            |   { "id": "test4", "children": [], "type": "dummyElement3", "attributes":{ "color": "#99123456" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 4
        map.typeOf("dummyElement").size shouldBe 2
        map["dummyElement", 1].id shouldBeEqualTo "test2"
        map["dummyElement2", 0].id shouldBeEqualTo "test3"
        map["dummyElement3", 0].id shouldBeEqualTo "test4"
        map.firstTypeOf("dummyElement") shouldBeInstanceOf LayoutProperties::class
        map.firstTypeOf("dummyElement").id shouldBeEqualTo "test1"
    }

    @Test
    fun `success - mulitformat value with enum and dimensions`() {
        serializerService.registerAdapter(DummyMultiFormatWithEnumElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "type": "dummyElement2", "attributes":{ "dimensionOrEnum": "value1" }},
            |   { "id": "test2", "children": ["test3"], "type": "dummyElement2", "attributes":{ "dimensionOrEnum": "12sp" }},
            |   { "id": "test3", "children": [], "type": "dummyElement2", "attributes":{ "dimensionOrEnum": "value2" }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 3
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatWithEnumElement::class
            val attrs = element.attributes as DummyMultiFormatWithEnumElement
            when (index) {
                0 -> attrs.dimensionOrEnum.enum shouldEqualTo 1
                1 -> attrs.dimensionOrEnum.dimension shouldEqualTo 24
                2 -> attrs.dimensionOrEnum.enum shouldEqualTo 2
            }
        }
    }

    @Test
    fun `success - mulitformat value with flags and dimensions`() {
        serializerService.registerAdapter(DummyMultiFormatWithFlagsElement::class, DummyElementAdapter(), "dummyElement2").buildAdapters()
        val json = """
            |[
            |   { "id": "test1", "children": ["test2"], "type": "dummyElement2", "attributes":{ "dimensionOrFlags": "value1" }},
            |   { "id": "test2", "children": ["test3", "test4"], "type": "dummyElement2", "attributes":{ "dimensionOrFlags": "12sp" }},
            |   { "id": "test3", "children": [], "type": "dummyElement2", "attributes":{ "dimensionOrFlags": "value1|value2" }},
            |   { "id": "test4", "children": [], "type": "dummyElement2", "attributes":{ "dimensionOrFlags": ["value1", "value2", "value1"] }}
            |]""".trimMargin()
        val result = serializerService.deserialize(LayoutMap::class, json)

        result shouldBeInstanceOf LayoutMap::class
        val map = result as LayoutMap
        map.size shouldEqualTo 4
        map.forEachIndexed { index, element ->
            element.attributes shouldBeInstanceOf DummyMultiFormatWithFlagsElement::class
            val attrs = element.attributes as DummyMultiFormatWithFlagsElement
            when (index) {
                0 -> attrs.dimensionOrFlags.flags shouldEqualTo 1
                1 -> attrs.dimensionOrFlags.dimension shouldEqualTo 24
                2 -> attrs.dimensionOrFlags.flags shouldEqualTo (1.or(2))
                3 -> attrs.dimensionOrFlags.flags shouldEqualTo (1.or(2))
            }
        }
    }


    class LayoutParamsAttributes(val width: Int, val height: Int) : IAttributes
    class DummyElement(val a: String, val b: Int) : IAttributes
    class DummyDimensionElement(
            @field:DimensionQualifier
            val dimension: Int?,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyReferenceElement(
            @field:ReferenceQualifier
            val reference: Int?,
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    class DummyColorElement(
            @field:ColorQualifier
            val color: Int?,
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
    class DummyMultiFormatWithEnumElement(
            @field:MultiFormatQualifier(formats = [Format.Dimension, Format.Enum], enumType = TestEnum::class)
            val dimensionOrEnum: MultiFormat = MultiFormat(),
            val a: String = "test",
            val b: Int = 0
    ) : IAttributes
    enum class TestEnum(override val key: String, override val value: Int) : AttributeEnum {
        @Json(name = "value1")
        Value1("value1", 1),
        @Json(name = "value2")
        Value2("value2", 2)
    }
    class DummyMultiFormatWithFlagsElement(
            @field:MultiFormatQualifier(formats = [Format.Dimension, Format.Flags], flagsType = TestEnum::class)
            val dimensionOrFlags: MultiFormat = MultiFormat(),
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
                    1 -> b = reader.nextInt() //?: throw JsonDataException("Non-null value 'elementType' was null at ${reader.path}")
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
                    1 -> height = reader.nextInt() //?: throw JsonDataException("Non-null value 'elementType' was null at ${reader.path}")
                    -1 -> {
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()
            return LayoutParamsAttributes(width ?: 0, height ?: 0)
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