package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.Space
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SpaceFactory<TView : Space, TAttributes : SpaceAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "space"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = Space(context)

    companion object {
        init {
            InflaterComponent.addFactory(SpaceFactory<Space, SpaceAttributes>())
        }

        inline operator fun <reified TView : Space, reified TAttributes : SpaceAttributes> invoke() = SpaceFactory(TView::class.java, TAttributes::class.java)
    }
}
