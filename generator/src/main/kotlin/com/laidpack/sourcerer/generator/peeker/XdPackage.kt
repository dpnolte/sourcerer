package com.laidpack.sourcerer.generator.peeker

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEntity
import kotlinx.dnq.XdNaturalEntityType
import kotlinx.dnq.xdLink0_N
import kotlinx.dnq.xdRequiredStringProp

class XdPackage(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdPackage>()
    var packageName by xdRequiredStringProp(unique = true, trimmed = true)
    val classes  by xdLink0_N(XdClass::xdPackage)
}