package com.laidpack.sourcerer.generator.target

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.resources.XdFormat
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*

data class Parameter(
        val index: Int,
        val name: String,
        val describedType: String,
        val isNullable: Boolean,
        val annotations: List<String>,
        val defaultValue: String = "",
        val isVarArgs: Boolean = false
        ) {
    var format = StyleableAttributeFormat.Unspecified

    fun toEntity(xdGetter: XdGetter): XdParameter {
        val xdParameter = this.toEntity()
        xdParameter.getter = xdGetter
        return xdParameter
    }
    fun toEntity(xdSetter: XdSetter): XdParameter {
        val xdParameter = this.toEntity()
        xdParameter.setter = xdSetter
        return xdParameter
    }
    private fun toEntity(): XdParameter {
        val xdParameter = XdParameter.new()
        xdParameter.index = this.index
        xdParameter.name = this.name
        xdParameter.describedType = this.describedType
        xdParameter.isNullable = this.isNullable
        xdParameter.annotations = this.annotations.toSet()
        xdParameter.defaultValue = this.defaultValue
        xdParameter.isVarArgs = this.isVarArgs
        xdParameter.format = this.format.toEntity()
        return xdParameter
    }

    companion object {
        const val UNASSOCIATED_TO_PARAMETER = -1
    }
}

class XdParameter(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdParameter>()

    var index by xdRequiredIntProp()
    var name by xdRequiredStringProp()
    var describedType by xdRequiredStringProp()
    var isNullable by xdBooleanProp()
    var annotations by xdSetProp<XdParameter, String>()
    var defaultValue by xdStringProp()
    var isVarArgs by xdBooleanProp()
    var format by xdLink1(XdFormat)
    var setter : XdSetter? by xdMultiParent(XdSetter::parameters)
    var getter : XdGetter? by xdMultiParent(XdGetter::parameters)

    fun toSnapshot(transaction: Boolean = true): Parameter {
        val block = {
            val param = Parameter(
                    this.index,
                    this.name,
                    this.describedType,
                    this.isNullable,
                    this.annotations.toList(),
                    this.defaultValue ?: "",
                    this.isVarArgs
            )
            param.format = this.format.toEnum()
            param
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}