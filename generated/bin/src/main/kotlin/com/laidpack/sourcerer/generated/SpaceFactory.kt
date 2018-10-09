package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.Space
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class SpaceFactory<TAttributes : SpaceAttributes>(attributesType: Class<TAttributes>) : ViewFactory<Space, TAttributes>(Space::class.java, attributesType) {
    override val elementName: String = "space"

    override fun createInstance(context: Context): View = Space(context)

    companion object {
        init {
            InflaterComponent.addFactory(SpaceFactory<SpaceAttributes>())
        }

        inline operator fun <reified TAttributes : SpaceAttributes> invoke() = SpaceFactory(TAttributes::class.java)
    }
}
