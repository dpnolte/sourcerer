package com.laidpack.sourcerer.service.test

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.service.InflaterModule
import com.laidpack.sourcerer.service.LayoutMap
import com.laidpack.sourcerer.service.LayoutProperties
import com.laidpack.sourcerer.service.api.BaseLayoutParamsFactory
import com.laidpack.sourcerer.service.api.BaseViewFactory
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.LayoutElement
import com.nhaarman.mockito_kotlin.eq
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class InflaterServiceTest {
    private var mutableInflaterService : InflaterModule? = null
    private val inflaterService : InflaterModule
        get() = mutableInflaterService as InflaterModule
    private lateinit var mockedActivity : Activity

    @Before
    fun setUp() {
        mutableInflaterService = InflaterModule()
        mockedActivity = Mockito.mock (Activity::class.java)
    }

    @Test
    fun  `success - add single view`() {
        val mockedView = Mockito.mock(View::class.java)
        val factory = DummyElementFactory<View, SerializationServiceTest.DummyElement>(mockedView)
        inflaterService.addFactory(factory)
        val layoutMap = LayoutMap(
            mapOf("test" to LayoutProperties("test", "dummyElement", SerializationServiceTest.DummyElement("aap", 2), listOf())),
            "test"
        )

        inflaterService.inflate(mockedActivity, layoutMap)
        Verify on mockedActivity that mockedActivity.addContentView(eq(mockedView), any()) was called
        VerifyNoFurtherInteractions on mockedActivity
    }

    @Test
    fun  `success - add multiple views and factories`() {
        val mockedRootView = Mockito.mock(ViewGroup::class.java)
        val rootFactory =
                DummyElementFactory<View, SerializationServiceTest.DummyElement>(mockedRootView)
        inflaterService.addFactory(rootFactory)
        val mockedChildView = Mockito.mock(View::class.java)
        val childFactory =
                AnotherDummyElementFactory<View, SerializationServiceTest.DummyElement>(mockedChildView)
        inflaterService.addFactory(childFactory)


        val layoutMap = LayoutMap(
                mapOf(
                        "root" to LayoutProperties(
                                "root",
                                "dummyElement",
                                SerializationServiceTest.DummyElement("aap", 2),
                                listOf("child")
                        ),
                        "child" to LayoutProperties(
                            "child",
                            "dummyElement2",
                            SerializationServiceTest.DummyElement("noot", 3),
                            listOf()
                        )
                ),
                "root"
        )

        inflaterService.inflate(mockedActivity, layoutMap)
        Verify on mockedActivity that mockedActivity.addContentView(eq(mockedRootView), any()) was called
        VerifyNoFurtherInteractions on mockedActivity
        Verify on mockedRootView that mockedRootView.addView(eq(mockedChildView)) was called
        VerifyNotCalled on mockedRootView that mockedRootView.setLayoutParams(any())
        VerifyNoFurtherInteractions on mockedRootView
        VerifyNoFurtherInteractions on mockedChildView
    }

    @Test
    fun  `success - add with LayoutParams`() {
        // root view
        val mockedRootView = Mockito.mock(ViewGroup::class.java)
        val rootFactory =
                DummyElementFactory<View, SerializationServiceTest.DummyElement>(mockedRootView)
        inflaterService.addFactory(rootFactory)
        val rootNode = LayoutProperties(
                "root",
                "dummyElement",
                SerializationServiceTest.DummyElement("aap", 2),
                listOf("child")
        )
        rootNode.layoutParamsElementName = "dummyLayoutParams"
        rootNode.layoutAttributes = SerializationServiceTest.DefaultLayoutParamsAttributes(true)

        // child view
        val mockedChildView = Mockito.mock(View::class.java)
        val childFactory =
                AnotherDummyElementFactory<View, SerializationServiceTest.DummyElement>(mockedChildView)
        inflaterService.addFactory(childFactory)
        val childNode = LayoutProperties(
                "child",
                "dummyElement2",
                SerializationServiceTest.DummyElement("noot", 3),
                listOf()
        )
        childNode.layoutParamsElementName = "dummyLayoutParams"
        childNode.layoutAttributes = SerializationServiceTest.DefaultLayoutParamsAttributes(false)

        // layout params
        val mockedLayoutParams = Mockito.mock(ViewGroup.LayoutParams::class.java)
        val lpFactory =
                DummyLayoutParamsFactory<ViewGroup.LayoutParams, SerializationServiceTest.DefaultLayoutParamsAttributes>(mockedLayoutParams)
        inflaterService.addFactory(lpFactory)


        val layoutMap = LayoutMap(
                mapOf(
                        "root" to rootNode,
                        "child" to childNode
                ),
                "root"
        )

        inflaterService.inflate(mockedActivity, layoutMap)
        Verify on mockedActivity that mockedActivity.addContentView(eq(mockedRootView), eq(mockedLayoutParams)) was called
        VerifyNoFurtherInteractions on mockedActivity
        Verify on mockedRootView that mockedRootView.addView(eq(mockedChildView)) was called
        VerifyNoFurtherInteractions on mockedRootView
        Verify on mockedChildView that mockedChildView.setLayoutParams(mockedLayoutParams) was called
        VerifyNoFurtherInteractions on mockedChildView
    }
}

class AnotherDummyElementFactory<TView: View, TAttributes: IAttributes>(
        mockedView: View,
        instanceType: Class<TView>,
        attributesType: Class<TAttributes>
): DummyElementFactory<TView, TAttributes>(mockedView, instanceType, attributesType) {
    override val elementName: String = "dummyElement2"
    companion object {
        inline operator fun <reified TView : View, reified TAttributes: IAttributes>invoke(mockedView: View)
                = AnotherDummyElementFactory(mockedView, TView::class.java, TAttributes::class.java)
    }
}

open class DummyElementFactory<TView: View, TAttributes: IAttributes>(
        private val mockedView: View,
        instanceType: Class<TView>,
        attributesType: Class<TAttributes>
) : BaseViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override fun createInstance(context: Context): View {
        return mockedView
    }

    override fun init(view: TView, context: Context, attributes: TAttributes) {
        // do nothing
    }
    override val elementName: String = "dummyElement"

    companion object {
        inline operator fun <reified TView : View, reified TAttributes: IAttributes>invoke(mockedView: View)
                = DummyElementFactory(mockedView, TView::class.java, TAttributes::class.java)
    }
}

class DummyLayoutParamsFactory<TLayoutParams: ViewGroup.LayoutParams, TAttributes: IAttributes>(
        private val mockedLayoutParams: ViewGroup.LayoutParams,
        instanceType: Class<TLayoutParams>,
        attributesType: Class<TAttributes>
) : BaseLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override fun createInstance(context: Context): ViewGroup.LayoutParams {
        return mockedLayoutParams
    }

    override fun init(view: TLayoutParams, context: Context, attributes: TAttributes) {
        // do nothing
    }
    override val elementName: String = "dummyLayoutParams"

    companion object {
        inline operator fun
                <reified TLayoutParams : ViewGroup.LayoutParams, reified TAttributes: IAttributes>
                invoke(mockedLayoutParams: ViewGroup.LayoutParams)
                = DummyLayoutParamsFactory(mockedLayoutParams, TLayoutParams::class.java, TAttributes::class.java)
    }
}