package com.laidpack.sourcerer.generator.peeker

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*

class XdParameter(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdParameter>()

    var name by xdRequiredStringProp()
    var index by xdRequiredIntProp()
    var describedType by xdRequiredStringProp()
    var constructor by xdMultiParent(XdConstructor::parameters)
    var method by xdMultiParent(XdMethod::parameters)
}