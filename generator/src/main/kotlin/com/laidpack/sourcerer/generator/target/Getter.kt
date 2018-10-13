package com.laidpack.sourcerer.generator.target

import com.github.javaparser.ast.body.FieldDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.peeker.describeType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList
import java.util.*

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

    val setterHashCodes = mutableSetOf<Int>()

    override fun hashCode(): Int {
        return Objects.hash(
                name,
                line,
                isField,
                *parameters.map { it.describedType }.toTypedArray()
        )
    }

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
        xdGetter.setterHashCodes = this.setterHashCodes
        return xdGetter
    }

    companion object {
        fun getHashCodeFromMethodInfo(method: MethodInfo): Int {
            return Objects.hash(
                    method.methodDeclaration.nameAsString,
                    method.methodDeclaration.begin.get().line,
                    /*isField*/ false,
                    *method.methodDeclaration.parameters.map {
                        it.describeType()
                    }.toTypedArray()
            )
        }
        fun getHashCodeFromField(field: FieldDeclaration): Int {
            return Objects.hash(
                    field.variables.first().nameAsString,
                    field.begin.get().line,
                    /*isField*/ true,
                    *listOf<String>().toTypedArray()
            )
        }
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
    var setterHashCodes by xdSetProp<XdGetter, Int>()

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
            getter.setterHashCodes.addAll(this.setterHashCodes)
            getter
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}