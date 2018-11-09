package com.laidpack.sourcerer.generator.resources.widgets

import com.laidpack.sourcerer.generator.Store
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.xdRequiredStringProp

enum class WidgetSource {
    Android,
    MaterialDesign,
    Support
}

class XdWidgetSource(entity: Entity) : XdEnumEntity(entity)  {
    companion object : XdEnumEntityType<XdWidgetSource>() {
        val Android by enumField { presentation = WidgetSource.Android.name }
        val MaterialDesign by enumField { presentation = WidgetSource.MaterialDesign.name }
        val Support by enumField { presentation = WidgetSource.Support.name }
    }
    var presentation by xdRequiredStringProp()
        private set

    fun toEnum(): WidgetSource {
        return Store.transactional {
            enumValueOf(presentation)
        }
    }
}