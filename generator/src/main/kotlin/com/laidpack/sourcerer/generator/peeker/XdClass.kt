package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.firstDescendantOfType
import com.laidpack.sourcerer.generator.resources.Widget
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query

class XdClass(
        entity: Entity
) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdClass>()
    var canonicalName by xdRequiredStringProp(unique = true, trimmed = true)
    var simpleName by xdRequiredStringProp(trimmed = true)
    private var mutableTargetClass: ClassName? = null
    val targetClassName : ClassName
        get() {
            if (mutableTargetClass == null) {
                Store.transactional {
                    mutableTargetClass = ClassName.bestGuess(canonicalName)
                }
            }
            return mutableTargetClass as ClassName
        }

    var classCategory by xdLink0_1(XdClassCategory)
    var isViewGroup by xdBooleanProp()
    var isNestedType by xdBooleanProp()
    var resolvedClassSymbol by xdBooleanProp()
    var resolvedWidgetSymbol by xdBooleanProp()
    var nodeHashCode: Int by xdRequiredIntProp(unique = true)
    var file : XdFile by xdParent(XdFile::classes)
    var sourcererResult by xdChild0_1(XdSourcererResult::targetClass)
    var xdPackage : XdPackage by xdLink1(XdPackage::classes)
    val superClasses by xdLink0_N(XdClass)
    val methods by xdLink0_N(XdMethod::classes, onDelete = OnDeletePolicy.CLEAR)
    val declaredMethods by xdChildren0_N(XdMethod::declaredInClass)
    val constructors by xdChildren0_N(XdConstructor::declaredInClass)
    val fields by xdLink0_N(XdField::classes, onDelete = OnDeletePolicy.CLEAR)
    val declaredFields by xdChildren0_N(XdField::declaredInClass)
    var hasResolvedClassBody by xdBooleanProp()
    var widgetAsBeingView : Widget? by xdLink0_1(Widget::viewClass)
    var widgetAsBeingLayoutParams : Widget? by xdLink0_1(Widget::layoutParamClasses)
    val widget : Widget?
        get() {
            return Store.transactional {
                when {
                    widgetAsBeingView != null -> widgetAsBeingView
                    widgetAsBeingLayoutParams != null -> widgetAsBeingLayoutParams
                    else -> null
                }
            }
        }
    val classOrInterfaceDeclarationProvider : () -> ClassOrInterfaceDeclaration = {
            Store.transactional {
                val cu = FileRegistry.deserializeCompilationUnit(file.compilationUnitBlob)
                val optionalClassDeclaration = if (!isNestedType) {
                    cu.getClassByName(simpleName) // returns only top-level types, no nested classes
                } else null
                if (optionalClassDeclaration != null && optionalClassDeclaration.isPresent) {
                    optionalClassDeclaration.get()
                } else {
                    cu.firstDescendantOfType(ClassOrInterfaceDeclaration::class.java) { node ->
                        node.name.identifier == simpleName
                    } as ClassOrInterfaceDeclaration
                }
            }
        }
}

enum class ClassCategory {
    View,
    LayoutParams;

    fun toEntity(): XdClassCategory {
        return XdClassCategory.query(
                XdClassCategory::presentation eq this.name
        ).first()
    }
}

class XdClassCategory (entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdClassCategory>() {
        val LayoutParams by enumField { presentation = ClassCategory.LayoutParams.name}
        val View by enumField { presentation = ClassCategory.View.name}
    }

    var presentation by xdRequiredStringProp(unique = true, trimmed = true)
    fun toEnum(transaction: Boolean = true): ClassCategory {
        val block =  {
            enumValueOf<ClassCategory>(this.presentation)
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}