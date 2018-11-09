package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.type.VoidType
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.javadoc.JavaDocAttributeToMethodMatcher
import com.laidpack.sourcerer.generator.target.Setter
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.*

class XdMethod(entity: Entity) : XdEntity(entity), XdMember {

    override var name by xdRequiredStringProp()
    override var declaredInType: XdDeclaredType by xdParent(XdDeclaredType::declaredMethods)
    override val classes: XdMutableQuery<XdDeclaredType> by xdLink1_N(XdDeclaredType::methods, onDelete = OnDeletePolicy.CLEAR)

    override var comment by xdStringProp()
    override var line by xdRequiredIntProp()
    val parameters: XdMutableQuery<XdConstructorOrMethodParameter> by xdChildren0_N(XdConstructorOrMethodParameter::method)
    override var isPublic by xdBooleanProp()
    override var isStatic by xdBooleanProp()
    var isVoid by xdBooleanProp()
    var isMethodWithAttributeSetAsParameter by xdBooleanProp()
    override var hasAnyJavaDocAttributeBlockTag by xdBooleanProp()
    override var canBeSetter by xdBooleanProp()
    override var canBeGetter by xdBooleanProp()
    override var memberIndex by xdRequiredIntProp()
    override var blockTags by xdSetProp<XdMethod, String>()
    override var attributeNamesFromBlockTags by xdSetProp<XdMethod, String>()
    override var nullable by xdBooleanProp()

    //var methodDeclarationBlob by xdRequiredBlobStringProp()
    var savedDescribedReturnType by xdStringProp()
    val describedReturnType: String by lazy {
        Store.transactional {
            if (savedDescribedReturnType == null) {
                //JavaParserFacade.get(JavaParserContext.combinedTypeSolver).
                val classDeclaration = declaredInType.getClassOrInterfaceDeclaration()
                val methodDeclaration = classDeclaration.members[memberIndex] as MethodDeclaration
                savedDescribedReturnType = methodDeclaration.describeReturnType()
            }
            savedDescribedReturnType as String
        }
    }

    var savedSetterHashCode by xdIntProp()
    override val accessorHashCode by lazy {
        Store.transactional {
            if (savedSetterHashCode == 0) {
                savedSetterHashCode = Setter.getHashCode(
                        name,
                        line,
                        parameters.asSequence().map { it.describedType }.toList()
                )
            }
            savedSetterHashCode
        }
    }

    companion object : XdNaturalEntityType<XdMethod>() {
        fun createFromMethodDeclaration(
                methodDeclaration: MethodDeclaration,
                isMethodWithAttributeSetAsParameter: Boolean,
                memberIndex: Int,
                classCategory: ClassCategory?,
                className: ClassName
        ): XdMethod {
            return XdMethod.new {
                this.name = methodDeclaration.nameAsString
                this.memberIndex = memberIndex
                this.nullable = methodDeclaration.annotations.any { it.nameAsString == "Nullable" }
                this.line = if (methodDeclaration.range.isPresent) {
                    methodDeclaration.range.get().begin.line
                } else -1
                this.comment = if(methodDeclaration.comment.isPresent) {
                    methodDeclaration.comment.get().content
                } else ""
                this.isMethodWithAttributeSetAsParameter = isMethodWithAttributeSetAsParameter

                methodDeclaration.parameters.forEachIndexed { index, parameter ->
                    this.parameters.add(XdConstructorOrMethodParameter.createFromJavaParserParameter(parameter, index))
                }
                this.isPublic = methodDeclaration.isPublic
                this.isStatic = methodDeclaration.isStatic
                this.isVoid = methodDeclaration.type is VoidType

                //methodDeclarationBlob = serializeMethodDeclaration(methodDeclaration)

                val optionalJavadoc = methodDeclaration.javadoc
                this.hasAnyJavaDocAttributeBlockTag = false
                var isEligibleMethod = isPublic
                if (classCategory != null) {
                    if (optionalJavadoc.isPresent) {
                        val javadoc = optionalJavadoc.get()
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
                        this.hasAnyJavaDocAttributeBlockTag = attributeNamesFromBlockTags.isNotEmpty()
                        blockTags = javadoc.blockTags.asSequence().map { it.tagName }.toSet()
                        isEligibleMethod = isEligibleMethod
                                && !javadoc.blockTags.any {
                            it.tagName == "hide" || it.tagName == "removed"
                        }
                    }

                    canBeSetter = isEligibleMethod && parameters.isNotEmpty
                    canBeGetter = isEligibleMethod && !isVoid && parameters.isEmpty
                }

            }
        }
    }
}