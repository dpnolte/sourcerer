package com.laidpack.sourcerer.generator.target

import com.github.javaparser.ast.body.FieldDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.peeker.describeType
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList
import java.util.*

data class Setter(
        val name: String,
        val parameters: List<Parameter>,
        val line: Int,
        val isField: Boolean = false
) {
    var mutablePropertyName : String? = null
    val hasPropertyName : Boolean
        get() = mutablePropertyName != null
    val propertyName : String
        get() = mutablePropertyName as String
    val attributeToParameter = mutableMapOf<String, Int>()

    fun getParameterByAttribute(attr: Attribute): Parameter {
        if (!attributeToParameter.containsKey(attr.name)) throw IllegalArgumentException("${attr.name} is not mapped to a parameter @ $name")
        val parameterIndex = attributeToParameter[attr.name] as Int
        if (parameterIndex >= parameters.size) throw IndexOutOfBoundsException("index $parameterIndex mapped to ${attr.name} is out of bounds @ $name")
        return parameters[parameterIndex]
    }

    override fun hashCode(): Int {
        return Objects.hash(
                name,
                line,
                isField,
                *parameters.map { it.describedType }.toTypedArray()
        )
    }

    fun toEntity(
            sourcererResult: XdSourcererResult
    ): XdSetter {
        val xdSetter = XdSetter.new()
        xdSetter.name = this.name
        xdSetter.parameters.addAll(this.parameters.map {
            it.toEntity(xdSetter)
        })
        xdSetter.line = this.line
        xdSetter.isField = this.isField
        if (this.hasPropertyName) xdSetter.propertyName = this.propertyName
        xdSetter.sourcererResult = sourcererResult
        xdSetter.attributeToParameter.addAll(
                this.attributeToParameter.map {
                    XdAttributeToParameterIndex.new{
                        this.attributeName = it.key
                        this.parameterIndex = it.value
                    }
                }
        )
        return xdSetter
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

class XdSetter (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdSetter>()

    var name by xdRequiredStringProp()
    val parameters by xdChildren0_N(XdParameter::setter)
    var line by xdRequiredIntProp()
    var isField by xdBooleanProp()
    var propertyName by xdStringProp()
    var sourcererResult : XdSourcererResult by xdParent(XdSourcererResult::setters)
    val attributeToParameter by xdChildren0_N(XdAttributeToParameterIndex::setter)

    fun toSnapshot(transaction: Boolean = true): Setter {
        val block = {
            val setter = Setter(
                    this.name,
                    this.parameters.toList().map { xdParam ->
                        xdParam.toSnapshot(false)
                    },
                    this.line,
                    this.isField
            )
            setter.mutablePropertyName = this.propertyName
            for (a2p in attributeToParameter.toList()) {
                setter.attributeToParameter[a2p.attributeName] = a2p.parameterIndex
            }
            setter
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}

class XdAttributeToParameterIndex (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdAttributeToParameterIndex>()
    var attributeName by xdRequiredStringProp()
    var parameterIndex by xdRequiredIntProp()
    var setter : XdSetter by xdParent(XdSetter::attributeToParameter)
}