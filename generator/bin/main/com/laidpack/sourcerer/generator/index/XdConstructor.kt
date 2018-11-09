package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.ConstructorDeclaration
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*

class XdConstructor(entity: Entity) : XdEntity(entity) {
    var name by xdRequiredStringProp()
    var declaredInType: XdDeclaredType by xdParent(XdDeclaredType::constructors)
    var isConstructorWithAttributeSetAsParameter by xdBooleanProp()

    var comment by xdStringProp()
    var line by xdRequiredIntProp()
    var isPublic by xdBooleanProp()
    val parameters by xdChildren0_N<XdConstructor, XdConstructorOrMethodParameter>(XdConstructorOrMethodParameter::constructor)
    var memberIndex by xdRequiredIntProp()

    companion object : XdNaturalEntityType<XdConstructor>() {
        fun createFromConstructorDeclaration(
                constructorDeclaration: ConstructorDeclaration,
                isConstructorWithAttributeSetAsParameter: Boolean,
                memberIndex: Int
        ): XdConstructor {
            return XdConstructor.new {
                this.name = constructorDeclaration.nameAsString
                this.memberIndex = memberIndex
                this.line = if (constructorDeclaration.range.isPresent) {
                    constructorDeclaration.range.get().begin.line
                } else -1
                this.comment = if(constructorDeclaration.comment.isPresent) {
                    constructorDeclaration.comment.get().content
                } else ""
                this.isPublic = constructorDeclaration.isPublic
                this.isConstructorWithAttributeSetAsParameter = isConstructorWithAttributeSetAsParameter
                constructorDeclaration.parameters.forEachIndexed { index, parameter ->
                    this.parameters.add(XdConstructorOrMethodParameter.createFromJavaParserParameter(parameter, index))
                }
            }
        }
    }
}