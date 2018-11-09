package com.laidpack.sourcerer.generator.index

import com.laidpack.sourcerer.generator.Store
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEnumEntity
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query
import kotlinx.dnq.xdRequiredStringProp

interface ConstructorExpression {
    fun toEntity(): XdConstructorExpression
}

enum class ViewConstructorExpression : ConstructorExpression {
    ContextOnly,
    ContextAndAttrs,
    ContextAttrsAndDefStyleAttr,
    AbstractView;

    override fun toEntity(): XdConstructorExpression {
        return XdConstructorExpression.query(
                XdConstructorExpression::presentation eq this.name
        ).first()
    }
}

enum class LayoutParamsConstructorExpression : ConstructorExpression {
    WidthAndHeight,
    CopySource,
    Empty,
    AbstractLayoutParams;

    override fun toEntity(): XdConstructorExpression {
        return XdConstructorExpression.query(
                XdConstructorExpression::presentation eq this.name
        ).first()
    }
}

class XdConstructorExpression(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdConstructorExpression>() {
        val ContextOnly by enumField { this.presentation = ViewConstructorExpression.ContextOnly.name }
        val ContextAndAttrs by enumField { this.presentation = ViewConstructorExpression.ContextAndAttrs.name }
        val ContextAttrsAndDefStyleAttr by enumField { this.presentation = ViewConstructorExpression.ContextAttrsAndDefStyleAttr.name }
        val AbstractView by enumField { this.presentation = ViewConstructorExpression.AbstractView.name }
        val WidthAndHeight by enumField { this.presentation = LayoutParamsConstructorExpression.WidthAndHeight.name }
        val CopySource by enumField { this.presentation = LayoutParamsConstructorExpression.CopySource.name }
        val Empty by enumField { this.presentation = LayoutParamsConstructorExpression.Empty.name }
        val AbstractLayoutParams by enumField { this.presentation = LayoutParamsConstructorExpression.AbstractLayoutParams.name }

    }
    var presentation by xdRequiredStringProp()

    fun toEnum(transaction: Boolean = true): ConstructorExpression {
        val block= {
            var constructorExpression : ConstructorExpression? = null
            for (enumValue in enumValues<ViewConstructorExpression>()) {
                if (enumValue.name == this.presentation) {
                    constructorExpression = enumValue
                    break
                }
            }
            if (constructorExpression == null) {
                for (enumValue in enumValues<LayoutParamsConstructorExpression>()) {
                    if (enumValue.name == this.presentation) {
                        constructorExpression = enumValue
                        break
                    }
                }
            }
            constructorExpression
                    ?: throw IllegalStateException("ConstructorExpression with name '${this.presentation}' could not be resolved")
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}