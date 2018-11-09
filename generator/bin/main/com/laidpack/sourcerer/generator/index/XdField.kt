package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.StringLiteralExpr
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.javadoc.JavaDocAttributeToMethodMatcher
import com.laidpack.sourcerer.generator.target.Setter
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.XdMutableQuery

class XdField(entity: Entity) : XdEntity(entity), XdMember {

    override var name by xdRequiredStringProp()
    override var declaredInType: XdDeclaredType by xdParent(XdDeclaredType::declaredFields)
    override val classes: XdMutableQuery<XdDeclaredType> by xdLink1_N(XdDeclaredType::fields, onDelete = OnDeletePolicy.CLEAR)

    override var comment by xdStringProp()
    override var line by xdRequiredIntProp()
    override var isPublic by xdBooleanProp()
    override var isStatic by xdBooleanProp()
    var isFinal by xdBooleanProp()
    override var canBeSetter by xdBooleanProp()
    override var canBeGetter by xdBooleanProp()
    override var memberIndex by xdRequiredIntProp()
    override var accessorHashCode by xdRequiredIntProp()
    override var hasAnyJavaDocAttributeBlockTag by xdBooleanProp()
    override var blockTags by xdSetProp<XdField, String>()
    override var attributeNamesFromBlockTags by xdSetProp<XdField, String>()
    override var nullable by xdBooleanProp()
    var initializingIntValue by xdIntProp()
    var initializingStringValue by xdStringProp()


    var savedDescribedType by xdStringProp()
    val describedType by lazy {
        Store.transactional {
            if (savedDescribedType == null) {
                val classDeclaration = declaredInType.getClassOrInterfaceDeclaration()
                val fieldDeclaration = classDeclaration.members[memberIndex] as FieldDeclaration
                savedDescribedType = fieldDeclaration.describeType(name)
            }
            savedDescribedType as String
        }
    }

    companion object : XdNaturalEntityType<XdField>() {
        fun createFromFieldDeclaration(
                fieldDeclaration: FieldDeclaration,
                memberIndex: Int,
                classCategory: ClassCategory?,
                className: ClassName
        ): List<XdField> {

            val fields = mutableListOf<XdField>()
            var hasAnyJavaDocAttributeTagBlocks = false
            var attributeNamesFromBlockTags = setOf<String>()
            var blockTags = setOf<String>()
            if (classCategory != null) {
                val optionalJavadoc = fieldDeclaration.javadoc
                if (optionalJavadoc.isPresent) {
                    val javadoc = optionalJavadoc.get()
                    blockTags = javadoc.blockTags.asSequence().map { it.tagName }.toSet()
                    attributeNamesFromBlockTags = javadoc.blockTags
                            .asSequence()
                            .filter { it.tagName == "attr" }
                            .map {
                                JavaDocAttributeToMethodMatcher.getAttributeNameFromBlockTag(
                                        it,
                                        classCategory,
                                        className
                                )
                            }
                            .toSet()
                    hasAnyJavaDocAttributeTagBlocks = attributeNamesFromBlockTags.isNotEmpty()
                }
            }
            val nullable = fieldDeclaration.annotations.any { it.nameAsString == "Nullable" }
            for (variableDeclarator in fieldDeclaration.variables) {
                fields.add( XdField.new {
                    this.name = variableDeclarator.nameAsString
                    this.memberIndex = memberIndex
                    //this.describedType = variableDeclarator.describeType()
                    this.comment = if (fieldDeclaration.comment.isPresent) {
                        fieldDeclaration.comment.get().content
                    } else ""
                    this.line = if (fieldDeclaration.begin.isPresent) {
                        fieldDeclaration.begin.get().line
                    } else -1
                    this.isPublic = fieldDeclaration.isPublic
                    this.isStatic = fieldDeclaration.isStatic
                    this.isFinal = fieldDeclaration.isFinal
                    this.canBeGetter = classCategory != null && this.isPublic
                    this.canBeSetter = classCategory != null && this.isPublic
                    this.accessorHashCode = Setter.getHashCodeFromField(fieldDeclaration)
                    this.hasAnyJavaDocAttributeBlockTag = hasAnyJavaDocAttributeTagBlocks
                    this.attributeNamesFromBlockTags = attributeNamesFromBlockTags
                    this.blockTags = blockTags
                    this.nullable = nullable
                    if (variableDeclarator.initializer.isPresent) {
                        val initializer = variableDeclarator.initializer.get()
                        if (initializer is IntegerLiteralExpr) {
                            this.initializingIntValue = initializer.asInt()
                        } else if (initializer is StringLiteralExpr) {
                            this.initializingStringValue = initializer.asString()
                        }
                    }
                })
            }
            return fields
        }
    }
}