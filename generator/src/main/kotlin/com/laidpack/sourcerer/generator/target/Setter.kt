package com.laidpack.sourcerer.generator.target

import android.content.Context
import android.view.View
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.index.XdMethod
import com.laidpack.sourcerer.generator.index.describeType
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList
import java.util.*

data class Setter(
        val name: String,
        val parameters: List<Parameter>,
        val line: Int,
        val isField: Boolean = false,
        val isStaticSetter: Boolean = false,
        val scopeClassName: ClassName? = null /* only used for static calls */
) {
    var mutablePropertyName : String? = null
    val hasPropertyName : Boolean
        get() = mutablePropertyName != null
    val propertyName : String
        get() = mutablePropertyName as String

    val callSignatureMaps = CallSignatureMapList(this.name)
    fun addCallSignatureMap(attributesToParametersForCall: MutableMap<String, Int>) {
        callSignatureMaps.add(attributesToParametersForCall)
    }
    fun addCallSignatureMap(attr: Attribute, parameterIndex: Int, otherAttributes: List<String>) {
        callSignatureMaps.add(attr, parameterIndex, otherAttributes)
    }

    fun isEachParameterMapped(callSignatureMap: Map<String, Int>): Boolean {
        val mappedParamsByAttributes = callSignatureMap.values.toSet()
        parameters.forEachIndexed { index, parameter ->
            if (!mappedParamsByAttributes.contains(index)
                    && parameter.defaultValue.isBlank()
                    && parameter.describedType != contextCanonicalName
                    && parameter.describedType != viewCanonicalName
                    && !parameter.isNullable
            ) {
                return false
            }
        }
        return true
    }
    fun toString(callSignatureMap: Map<String, Int>): String {
        val reversedMap = callSignatureMap.entries.associateBy({ it.value }) { it.key }
        val descriptors = mutableListOf<String>()
        parameters.forEachIndexed { index, parameter ->
            when {
                reversedMap.containsKey(index) -> descriptors.add("${parameter.name}: ${reversedMap[index]}")
                parameter.describedType == contextCanonicalName -> descriptors.add("${parameter.name}: Context")
                parameter.defaultValue.isNotBlank() -> descriptors.add("${parameter.name}: ${parameter.defaultValue}")
                parameter.isNullable -> descriptors.add("${parameter.name}: null")
                else -> descriptors.add("${parameter.name}: !- missing ${parameter.describedType}")
            }
        }
        return "$name(${descriptors.joinToString()})"
    }

    fun getParameterByAttribute(attr: Attribute): Parameter {
        if (!callSignatureMaps.containsAttribute(attr.name)) throw IllegalArgumentException("${attr.name} is not mapped to a parameter @ $name")
        val parameterIndex = callSignatureMaps[attr.name]
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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Setter

        if (name != other.name) return false
        if (parameters != other.parameters) return false
        if (line != other.line) return false
        if (isField != other.isField) return false
        var i = -1
        if (parameters.any {
                    i += 1
                    it.describedType != other.parameters[i].describedType
        }) return false
        return true
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
        xdSetter.isStaticSetter = this.isStaticSetter
        xdSetter.scopeCanonicalName = this.scopeClassName?.canonicalName
        if (this.hasPropertyName) xdSetter.propertyName = this.propertyName
        xdSetter.sourcererResult = sourcererResult
        for (callSignatureMap in this.callSignatureMaps.attributesToParameters) {
            if (callSignatureMap.isEmpty()) {
                throw IllegalStateException("Setter's call signature map is defined but contains no attributes to parameters mapping. Setter: ${this.name}")
            }
            val xdCallSignatureMap = XdCallSignatureMap.new()
            for (pair in callSignatureMap) {
                val xdAttributeToParameterIndex = XdAttributeToParameterIndex.new()
                xdAttributeToParameterIndex.attributeName = pair.key
                xdAttributeToParameterIndex.parameterIndex = pair.value
                xdCallSignatureMap.attributesToParameters.add(xdAttributeToParameterIndex)
            }
            xdSetter.callSignatureMaps.add(xdCallSignatureMap)
        }
        return xdSetter
    }

    companion object {
        private val contextCanonicalName = Context::class.java.canonicalName
        private val viewCanonicalName = View::class.java.canonicalName
        fun getHashCodeFromMethodInfo(method: XdMethod): Int {
            return Store.transactional { method.accessorHashCode }
        }
        fun getHashCodeFromMethodDeclaration(methodDeclaration: MethodDeclaration): Int {
            return Objects.hash(
                    methodDeclaration.nameAsString,
                    methodDeclaration.begin.get().line,
                    /*isField*/ false,
                    *methodDeclaration.parameters.map {
                        it.describeType()
                    }.toTypedArray()
            )
        }
        fun getHashCode(name: String, line: Int, parameterTypes: List<String>): Int {
            return Objects.hash(
                    name,
                    line,
                    /*isField*/ false,
                    *parameterTypes.toTypedArray()
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
    var isStaticSetter by xdBooleanProp()
    var scopeCanonicalName by xdStringProp() /* only used for static calls */
    val callSignatureMaps
            by xdChildren0_N(XdCallSignatureMap::setter)


    fun toSnapshot(transaction: Boolean = true): Setter {
        val block = {

            val scopeCanonicalName = this.scopeCanonicalName
            val setter = Setter(
                    this.name,
                    this.parameters.toList().map { xdParam ->
                        xdParam.toSnapshot(false)
                    },
                    this.line,
                    this.isField,
                    this.isStaticSetter,
                    if (scopeCanonicalName != null) ClassName.bestGuess(scopeCanonicalName) else null
            )
            setter.mutablePropertyName = this.propertyName

            for (a2pList in callSignatureMaps.toList()) {
                val map = mutableMapOf<String, Int>()
                for(a2p in  a2pList.attributesToParameters.toList()) {
                    map[a2p.attributeName] = a2p.parameterIndex
                }
                setter.addCallSignatureMap(map)
            }
            setter
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}

class XdCallSignatureMap(entity: Entity): XdEntity(entity) {
    companion object : XdNaturalEntityType<XdCallSignatureMap>()
    val attributesToParameters
            by xdChildren1_N(XdAttributeToParameterIndex::callSignatureMap)
    val setter : XdSetter
            by xdParent(XdSetter::callSignatureMaps)
}

class XdAttributeToParameterIndex (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdAttributeToParameterIndex>()
    var attributeName by xdRequiredStringProp()
    var parameterIndex by xdRequiredIntProp()
    var callSignatureMap : XdCallSignatureMap
            by xdParent(XdCallSignatureMap::attributesToParameters)
}