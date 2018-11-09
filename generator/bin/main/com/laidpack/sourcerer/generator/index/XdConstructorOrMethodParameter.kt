package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.ConstructorDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.laidpack.sourcerer.generator.Store
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*

class XdConstructorOrMethodParameter(entity: Entity) : XdEntity(entity) {
    var constructor: XdConstructor? by xdMultiParent(XdConstructor::parameters)
    var method: XdMethod? by xdMultiParent(XdMethod::parameters)
    var name by xdRequiredStringProp()
    var index by xdRequiredIntProp()
    var nullable by xdBooleanProp()
    var savedDescribedType by xdStringProp()
    var annotationNames by xdSetProp<XdConstructorOrMethodParameter, String>()
    val describedType: String by lazy {
       Store.transactional {
           if (savedDescribedType == null) {
               savedDescribedType = if (method != null) {
                   val m = method as XdMethod
                   val methodDeclaration =
                           m.declaredInType.getClassOrInterfaceDeclaration().members[m.memberIndex] as MethodDeclaration
                   val p = methodDeclaration.parameters[index] as Parameter
                   p.describeType()
               } else {
                   val c = constructor as XdConstructor
                   val constructorDeclaration =
                           c.declaredInType.getClassOrInterfaceDeclaration().members[c.memberIndex] as ConstructorDeclaration
                   val p = constructorDeclaration.parameters[index] as Parameter
                   p.describeType()
               }
           }
           savedDescribedType as String
       }
    }

    companion object : XdNaturalEntityType<XdConstructorOrMethodParameter>() {
        fun createFromJavaParserParameter(parameter: Parameter, index: Int): XdConstructorOrMethodParameter {
            return new {
                this.name = parameter.nameAsString
                this.index = index
                this.nullable = parameter.annotations.any { it.nameAsString == "Nullable" }
                this.annotationNames = parameter.annotations
                        .asSequence()
                        .map { it.nameAsString }
                        .toSet()
            }
        }
    }
}
