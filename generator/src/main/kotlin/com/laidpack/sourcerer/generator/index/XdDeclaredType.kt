package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.EnumDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.descendantsAndPatshOfType
import com.laidpack.sourcerer.generator.resources.Widget
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.link.OnDeletePolicy
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query

class XdDeclaredType(
        entity: Entity
) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdDeclaredType>()
    var canonicalName by xdRequiredStringProp(unique = true, trimmed = true)
    var simpleName by xdRequiredStringProp(trimmed = true)
    private var mutableTargetClass: ClassName? = null
    val targetClassName : ClassName
        get() {
            if (mutableTargetClass == null) {
                Store.transactional {
                    val packageName = xdPackage.packageName
                    val simpleNames = canonicalName.substring(packageName.length + 1).split(".")
                    mutableTargetClass = when (simpleNames.size) {
                        1 -> ClassName(packageName, simpleName)
                        else -> ClassName(
                                packageName,
                                simpleNames.first(),
                                *simpleNames.subList(1, simpleNames.size).toTypedArray()
                        )
                    }
                }
            }
            return mutableTargetClass as ClassName
        }

    var classCategory by xdLink0_1(XdClassCategory)
    var isViewGroup by xdBooleanProp()
    var isNestedType by xdBooleanProp()
    var isInterface by xdBooleanProp()
    var isEnum by xdBooleanProp()
    var isClass by xdBooleanProp()
    var resolvedClassSymbol by xdBooleanProp()
    var resolvedWidgetSymbol by xdBooleanProp()
    var resolvedBody by xdBooleanProp()
    var resolvedSuperClasses by xdBooleanProp()
    var nodeHashCode: Int by xdRequiredIntProp(unique = true)
    var file : XdFile by xdParent(XdFile::classes)
    var sourcererResult by xdChild0_1(XdSourcererResult::targetClass)
    var xdPackage : XdPackage by xdLink1(XdPackage::classes)
    var superClass by xdLink0_1(XdDeclaredType)
    val superClasses by xdLink0_N(XdDeclaredType)
    val methods by xdLink0_N(XdMethod::classes, onDelete = OnDeletePolicy.CLEAR)
    val declaredMethods by xdChildren0_N(XdMethod::declaredInType)
    val constructors by xdChildren0_N(XdConstructor::declaredInType)
    val fields by xdLink0_N(XdField::classes, onDelete = OnDeletePolicy.CLEAR)
    val declaredFields by xdChildren0_N(XdField::declaredInType)
    val enumEntries by xdChildren0_N(XdEnumEntry::declaredInType)
    val intDefAnnotations by xdChildren0_N(XdIntDefAnnotation::declaredInType)
    var constructorExpression by xdLink0_1(XdConstructorExpression)
    var widgetAsBeingView : Widget? by xdLink0_1(Widget::viewClass)
    var widgetAsBeingLayoutParams : Widget? by xdLink0_1(Widget::layoutParamClasses)
    val path by lazy { Store.transactional { pathString.split(",").map { part -> part.toInt() } }}
    var pathString by xdRequiredStringProp()
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
    fun getClassOrInterfaceDeclaration(): ClassOrInterfaceDeclaration {
        val typeDeclaration = getTypeDeclaration()
        return typeDeclaration as? ClassOrInterfaceDeclaration
                ?: throw IllegalStateException("Type declaration is not a ClassOrInterfaceDeclaration. Actual type: '${typeDeclaration::class.java.canonicalName}'. Node: '${typeDeclaration.nameAsString}'")
    }
    fun getEnumDeclaration(): EnumDeclaration {
        val typeDeclaration = getTypeDeclaration()
        return typeDeclaration as? EnumDeclaration
                ?: throw IllegalStateException("Type declaration is not a EnumDeclaration. Actual type: '${typeDeclaration::class.java.canonicalName}'. Node: '${typeDeclaration.nameAsString}'")
    }
    fun getTypeDeclaration(): TypeDeclaration<*> {
        return Store.transactional {
            val cu = FileRegistry.deserializeCompilationUnit(file.url, file.compilationUnitBlob)
            val mutablePath = path.toMutableList()
            var currentNode : Node = cu
            if (mutablePath.isEmpty()) throwIllegalPathException(cu, currentNode, "No path has been set")
            try {
                while (mutablePath.isNotEmpty()) {
                    currentNode = currentNode.childNodes[mutablePath[0]]
                    mutablePath.removeAt(0)
                }
            } catch (e: IndexOutOfBoundsException) {
                throwIllegalPathException(cu, currentNode, "Index ${mutablePath[0]} out of bounds")
            }
            return@transactional currentNode as? TypeDeclaration<*>
                    ?: throwIllegalPathException(cu, currentNode, "Invalid type. Expected 'TypeDeclaration', actual: '${currentNode::class.java.canonicalName}'")
        }
    }

    private fun throwIllegalPathException(cu: CompilationUnit, currentNode: Node, message: String): ClassOrInterfaceDeclaration {
        val pair = cu.descendantsAndPatshOfType(
                TypeDeclaration::class.java
        ) { c -> c.nameAsString == simpleName}.firstOrNull()
        val correctPath = pair?.second?.joinToString() ?: "missing path"
        throw IllegalStateException("Path ${path.joinToString { "[$it]" }} for '$targetClassName' is illegal. $message. Last valid node: '${(currentNode as TypeDeclaration<*>).nameAsString}'. Correct path should have been: $correctPath")
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