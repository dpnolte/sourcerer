package com.laidpack.sourcerer.generator.target

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.XdMutableQuery
import kotlinx.dnq.query.addAll
import kotlinx.dnq.query.toList

data class CodeBlock (
        val setters: List<Setter>,
        val attributes: Map<String, Attribute>,
        var minimumApiLevel: Int = 0
) {
    val setter = setters.first()
    val hasOnlyOneSetter = setters.size == 1

    fun toEntity(
            sourcererResult: XdSourcererResult,
            setters: Map<Int /* hash code */, XdSetter>,
            attributes: Map<String /* name */, XdAttribute>
            ): XdCodeBlock {
        val xdCodeBlock = XdCodeBlock.new()
        xdCodeBlock.attributes.addAll(
                this.attributes.map { pair ->
                    attributes[pair.key] as XdAttribute
                }
        )
        xdCodeBlock.setters.addAll(
                this.setters.map {setter ->
                    setters[setter.hashCode()] as XdSetter
                }
        )
        xdCodeBlock.minimumApiLevel = this.minimumApiLevel
        xdCodeBlock.sourcererResult = sourcererResult
        return xdCodeBlock
    }
}

class XdCodeBlock(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdCodeBlock>()

    val setters : XdMutableQuery<XdSetter> by xdLink1_N(XdSetter)
    val attributes by xdLink1_N(XdAttribute)
    var minimumApiLevel by xdIntProp()
    var sourcererResult : XdSourcererResult by xdParent(XdSourcererResult::codeBlocks)

    fun toSnapshot(providedSetters: Map<Int, Setter>? = null, providedAttributes: Map<String, Attribute>? = null, transaction: Boolean = true): CodeBlock {
        val block = {
            val setters = if (providedSetters != null) {
                this.setters.toList().map {xdSetter ->
                    val hashCode = xdSetter.toSnapshot(false).hashCode()
                    providedSetters[hashCode] as Setter
                }
            } else {
                this.setters.toList().map {xdSetter ->
                    xdSetter.toSnapshot(false)
                }
            }
            val attributes = if (providedAttributes != null) {
                this.attributes.toList().associate {xdAttribute ->
                    Pair(xdAttribute.name, providedAttributes[xdAttribute.name] as Attribute)
                }
            } else {
                this.attributes.toList().associate {xdAttribute ->
                    Pair(xdAttribute.name, xdAttribute.toSnapshot(false))
                }
            }
            CodeBlock(
                    setters,
                    attributes,
                    this.minimumApiLevel ?: 0
            )
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}