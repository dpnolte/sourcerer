package com.laidpack.sourcerer.generator.peeker

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.XdMutableQuery

class XdConstructor(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdConstructor>()

    var name by xdRequiredStringProp()
    var declaredInClass: XdClass by xdParent(XdClass::constructors)
    var isConstructorWithAttributeSetAsParameter by xdBooleanProp()

    var comment by xdRequiredStringProp()
    var line by xdRequiredIntProp()
    val parameters by xdChildren0_N(XdParameter::constructor)
}