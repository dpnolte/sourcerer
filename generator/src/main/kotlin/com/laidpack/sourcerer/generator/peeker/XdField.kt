package com.laidpack.sourcerer.generator.peeker

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.XdMutableQuery

class XdField(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdMethod>()

    var name by xdRequiredStringProp()
    var declaredInClass: XdClass by xdParent(XdClass::declaredFields)
    val classes: XdMutableQuery<XdClass> by xdLink1_N(XdClass::fields, onDelete = OnDeletePolicy.CLEAR)

    var describedType by xdRequiredStringProp()
    var comment by xdRequiredStringProp()
    var line by xdRequiredIntProp()
    var isPublic by xdBooleanProp()
    var isStatic by xdBooleanProp()
}