package com.laidpack.sourcerer.generator.index

import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.XdMutableQuery

class XdIntDefAnnotation(entity: Entity) : XdEntity(entity) {
    var declaredInType: XdDeclaredType by xdParent(XdDeclaredType::intDefAnnotations)
    val values: XdMutableQuery<XdIntDefAnnotationValue> by xdChildren1_N(XdIntDefAnnotationValue::annotation)
    var name by xdRequiredStringProp()

    companion object : XdNaturalEntityType<XdIntDefAnnotation>()
}

class XdIntDefAnnotationValue(entity: Entity) : XdEntity(entity) {
    var name by xdRequiredStringProp()
    var value by xdRequiredIntProp()
    var annotation: XdIntDefAnnotation by xdParent(XdIntDefAnnotation::values)
    companion object : XdNaturalEntityType<XdIntDefAnnotationValue>()
}