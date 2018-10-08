package com.laidpack.sourcerer.generator.target

import com.laidpack.sourcerer.generator.Store
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList

data class Getter(
        val name: String,
        val describedReturnType: String,
        val parameters: List<Parameter>,
        val line: Int,
        val isField: Boolean = false
        ) {
    val propertyName = when {
        isField -> name
        name.startsWith("get") -> name.substring(3).decapitalize()
        name.startsWith("is") -> name
        else -> "$name()"

    }

    var mutableTypeName: TypeName? = null
    val typeName : TypeName
        get() = mutableTypeName as TypeName
    val hasTypeName: Boolean
        get() = mutableTypeName != null

    var mutableResolvedType: TypeName? = null
    val resolvedType : TypeName
        get() = mutableResolvedType as TypeName
    val hasResolvedType: Boolean
        get() = mutableResolvedType != null

    fun toEntity(xdAttribute: XdAttribute): XdGetter {
        val xdGetter = XdGetter.new()
        xdGetter.name = this.name
        xdGetter.describedReturnType = this.describedReturnType
        xdGetter.line = this.line
        xdGetter.isField = this.isField
        if (this.hasTypeName) xdGetter.typeNameAsString = this.typeName.toString()
        if (this.hasResolvedType) xdGetter.resolvedTypeAsString = this.resolvedType.toString()
        xdGetter.attribute = xdAttribute
        xdGetter.parameters.addAll(
                this.parameters.map {
                    it.toEntity(xdGetter)
                }
        )
        return xdGetter
    }

}

class XdGetter(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdGetter>()

    var name by xdRequiredStringProp()
    var describedReturnType by xdRequiredStringProp()
    val parameters by xdChildren0_N(XdParameter::getter)
    var line by xdRequiredIntProp()
    var isField by xdBooleanProp()
    var typeNameAsString by xdStringProp()
    var resolvedTypeAsString by xdStringProp()
    var attribute : XdAttribute by xdParent(XdAttribute::getters)

    fun toSnapshot(transaction: Boolean = true): Getter {
        val block = {
            val getter = Getter(
                    this.name,
                    this.describedReturnType,
                    this.parameters.toList().map { xdParam ->
                        xdParam.toSnapshot(false)
                    },
                    this.line,
                    this.isField
            )
            if (typeNameAsString != null) {
                getter.mutableTypeName = ClassName.bestGuess(this.typeNameAsString as String)
            }
            if (resolvedTypeAsString != null) {
                getter.mutableResolvedType = ClassName.bestGuess(this.resolvedTypeAsString as String)
            }
            getter
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}