package com.laidpack.sourcerer.generator.peeker

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.XdMutableQuery

class XdMethod(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdMethod>()

    var name by xdRequiredStringProp()
    var declaredInClass: XdClass by xdParent(XdClass::declaredMethods)
    val classes: XdMutableQuery<XdClass> by xdLink1_N(XdClass::methods, onDelete = OnDeletePolicy.CLEAR)

    var describedReturnType by xdRequiredStringProp()
    var comment by xdRequiredStringProp()
    var line by xdRequiredIntProp()
    val parameters: XdMutableQuery<XdParameter> by xdChildren0_N(XdParameter::method)
    var isPublic by xdBooleanProp()
    var isStatic by xdBooleanProp()
    var isMethodWithAttributeSetAsParameter by xdBooleanProp()
    var hasAnyJavaDocAttributeTagBlocks by xdBooleanProp()
}