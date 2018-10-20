package com.laidpack.sourcerer.generator.target

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.resources.*
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.query.*

open class Attribute(
        val className: ClassName,
        val name: String,
        val formats: MutableList<StyleableAttributeFormat> = mutableListOf(),
        val flags: MutableList<StyleableAttributeFlag> = mutableListOf(),
        val enumValues: MutableList<StyleableAttributeEnumValue> = mutableListOf(),
        var resolvedStatus: ResolvedStatus = ResolvedStatus.NONE
) {
    var resolvedByInterpreter: String = "None"
    var localVariableName = ""
    val setterHashCodes = mutableSetOf<Int>()
    val typesPerSetter = mutableMapOf<Int /* setter hash code */, AttributeTypesForSetter>()
    fun resolvedTypesPerSetter(setterHashCode: Int): AttributeTypesForSetter {
        return typesPerSetter[setterHashCode]
                ?: throw IllegalStateException("Attribute $name has no types defined for setter $setterHashCode")
    }
    val typesForFirstSetter : AttributeTypesForSetter
        get() = typesPerSetter.values.first()

    val getters : MutableList<Getter> = mutableListOf()
    val getter : Getter
        get() {
            if (getters.size != 1) throw IllegalStateException(if (getters.size == 0) "There are no getters" else "There are multiple getters")
            return getters.first()
        }

    var oneFormatRequiresMultipleSetters = false

    val formatsUsedBySetters : Set<StyleableAttributeFormat> by lazy {
        val formats = mutableSetOf<StyleableAttributeFormat>()
        for (typesForSetter in typesPerSetter.values) {
            for(format in typesForSetter.formats) {
                if (!formats.contains(format)) {
                    formats.add(format)
                }
            }
        }
        formats
    }

    var mutableAttributeDeclaredInSuperClass : Attribute? = null
    var mutableDeclarationClassName: ClassName = className
    val attributeDeclaredInSuperClass : Attribute
        get() = mutableAttributeDeclaredInSuperClass as Attribute
    val isDeclaredInSuperClass: Boolean
        get() = mutableAttributeDeclaredInSuperClass != null

    fun toEntity(
            setters: Map</* hash code */ Int, XdSetter>,
            sourcererResult: XdSourcererResult
    ): XdAttribute {
        val xdAttribute = XdAttribute.new()
        xdAttribute.name = this.name
        xdAttribute.classCanonicalName = this.className.canonicalName
        xdAttribute.formats.addAll(this.formats.map { it.toEntity() })
        xdAttribute.flags.addAll(this.flags.map { it.toEntity() })
        xdAttribute.enumValues.addAll(this.enumValues.map { it.toEntity() })
        xdAttribute.resolvedStatus = this.resolvedStatus.toEntity()
        if (this.localVariableName.isNotBlank()) xdAttribute.localVariableName = this.localVariableName
        xdAttribute.setters.addAll(setters.values)
        xdAttribute.sourcererResult = sourcererResult
        xdAttribute.attributeDeclaredInSuperClass = if (isDeclaredInSuperClass) {
            XdAttribute.query(
                    XdAttribute::classCanonicalName eq mutableDeclarationClassName.canonicalName
            ).firstOrNull()
                ?: throw IllegalStateException("attribute '$name' with class $mutableDeclarationClassName not found")
        } else null

        for (typePerSetter in this.typesPerSetter) {
            xdAttribute.typesPerSetter.add(
                    typePerSetter.value.toEntity(
                            xdAttribute,
                            setters[typePerSetter.key] as XdSetter
                    )
            )
        }
        for (getter in this.getters) {
            xdAttribute.getters.add(getter.toEntity(xdAttribute))
        }

        return xdAttribute
    }
}

class XdAttribute (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdAttribute>()

    var name by xdRequiredStringProp()
    var classCanonicalName by xdRequiredStringProp()
    val formats by xdLink0_N(XdFormat)
    val flags by xdLink0_N(XdStyleableAttributeFlag)
    val enumValues by xdLink0_N(XdStyleableAttributeEnumValue)
    var resolvedStatus by xdLink1(XdResolvedStatus)

    var localVariableName by xdStringProp()
    val setters : XdMutableQuery<XdSetter> by xdLink0_N(XdSetter)
    val typesPerSetter by xdChildren0_N(XdAttributeTypesForSetter::attribute)
    val getters by xdChildren0_N(XdGetter::attribute)
    var sourcererResult : XdSourcererResult by xdParent(XdSourcererResult::attributes)
    var attributeDeclaredInSuperClass : XdAttribute? by xdLink0_1(XdAttribute)

    fun toSnapshot(transaction: Boolean = true): Attribute {
        val block = {
            val attr = Attribute(
                    ClassName.bestGuess(this.classCanonicalName),
                    this.name,
                    this.formats.asSequence().map { xdFormat ->
                        xdFormat.toEnum(false)
                    }.toMutableList(),
                    this.flags.asSequence().map { xdFlag ->
                        xdFlag.toSnapshot(false)
                    }.toMutableList(),
                    this.enumValues.asSequence().map { xdEnumValue ->
                        xdEnumValue.toSnapshot(false)
                    }.toMutableList(),
                    this.resolvedStatus.toEnum(false)
            )
            attr.localVariableName = this.localVariableName ?: ""
            this.typesPerSetter.toList().forEach { xdAttributeTypes ->
                val setterHashCode = xdAttributeTypes.setter.toSnapshot(false).hashCode()
                attr.setterHashCodes.add(setterHashCode)
                attr.typesPerSetter[setterHashCode] = xdAttributeTypes.toSnapshot(false)
            }

            attr.getters.addAll(
                    this.getters.toList().map { xdGetter ->
                        xdGetter.toSnapshot(false)
                    }
            )
            if (attributeDeclaredInSuperClass != null) {
                val attributeDeclaredInSuperClass = this.attributeDeclaredInSuperClass as XdAttribute
                attr.mutableAttributeDeclaredInSuperClass = attributeDeclaredInSuperClass.toSnapshot()
                attr.mutableDeclarationClassName = ClassName.bestGuess(attributeDeclaredInSuperClass.classCanonicalName)

            }
            attr
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}


enum class ResolvedStatus(val value: Int = 0) {
    NONE (0),
    IDENTIFIED_IN_XML (1),
    IDENTIFIED_IN_SOURCE (2),
    SETTER_DEFINED (3 );

    fun toEntity(): XdResolvedStatus {
        return XdResolvedStatus.query(
                XdResolvedStatus::presentation eq this.name
        ).first()
    }
}
class XdResolvedStatus(entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdResolvedStatus>() {
        val NONE by enumField { presentation = ResolvedStatus.NONE.name }
        val IDENTIFIED_IN_XML by enumField { presentation = ResolvedStatus.IDENTIFIED_IN_XML.name }
        val IDENTIFIED_IN_SOURCE by enumField { presentation = ResolvedStatus.IDENTIFIED_IN_SOURCE.name }
        val SETTER_DEFINED by enumField { presentation = ResolvedStatus.SETTER_DEFINED.name }
    }
    var presentation by xdRequiredStringProp()

    fun toEnum(transaction: Boolean = true): ResolvedStatus {
        val block = {
            enumValueOf<ResolvedStatus>(this.presentation)
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}