package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.laidpack.sourcerer.generator.Store
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query

fun FieldDeclaration.describeType(variableName: String): String {
    val variable = this.variables.find { it.nameAsString == variableName }
        ?: throw java.lang.IllegalArgumentException("Field $this contains no variable '$variableName'")
    return variable.describeType()
}

fun VariableDeclarator.describeType(): String {
    return try {
        this.type.resolve().describe()
    } catch (e: Exception) {
        if (this.type.isClassOrInterfaceType) {
            val indexedClass = DeclaredSymbolResolver.resolveDeclaredType(this.type.asClassOrInterfaceType())
            indexedClass.targetClassName.canonicalName
        } else throw e
    }
}

fun MethodDeclaration.describeReturnType(): String {
    return try {
        this.resolve().returnType.describe()
    } catch (e: Exception) {
        val returnType = this.type
        if (returnType.isClassOrInterfaceType) {
            val indexedClass = DeclaredSymbolResolver.resolveDeclaredType(returnType.asClassOrInterfaceType())
            indexedClass.targetClassName.canonicalName
        } else throw e
    }
}

fun Parameter.describeType(): String {
    return try {
        when {
            this.isVarArgs -> this.resolve().describeType().replace("...", "[]")
            else -> this.resolve().describeType()
        }
    } catch (e: UnsolvedSymbolException) {
        if (this.type.isClassOrInterfaceType) {
            val indexedClass = DeclaredSymbolResolver.resolveDeclaredType(this.type.asClassOrInterfaceType())
            indexedClass.targetClassName.canonicalName
        } else throw e
    }
}

fun Expression.describeType(): String {
    return try {
        this.calculateResolvedType().describe()
    } catch (e: Exception) {
        val cu = this.findCompilationUnit()
        when {
            cu.isPresent && this is FieldAccessExpr && this.scope is NodeWithSimpleName<*> -> {
                var selectedScope = this.scope
                val fields = mutableListOf(this.nameAsString)
                while (selectedScope is FieldAccessExpr) {
                    fields.add(0, selectedScope.nameAsString)
                    selectedScope = selectedScope.scope
                }
                fields.add(0, (selectedScope as NodeWithSimpleName<*>).nameAsString)
                // TODO: include R.styleable into indexing.
                // This generated class is still used in support and materials design (but not in Android.jar)
                val indexForR = fields.indexOfFirst { fieldName -> fieldName == "R" }
                val indexForStylable = fields.indexOfFirst { fieldName -> fieldName == "styleable" }
                if (indexForR != -1 && (indexForR + 1) == indexForStylable) {
                    "int[]"
                } else {
                    val xdDeclaredType = DeclaredSymbolResolver.resolveDeclaredType(this.scope as NodeWithSimpleName<*>)
                    Store.transactional {
                        if (xdDeclaredType.isEnum) {
                            xdDeclaredType.canonicalName
                        } else {
                            if (!xdDeclaredType.resolvedBody) {
                                TypeBodyPeeker.peek(xdDeclaredType.getTypeDeclaration(), xdDeclaredType)
                            }
                            xdDeclaredType.fields.query(XdField::name eq this.nameAsString).first().describedType
                        }
                    }
                }
            }
            cu.isPresent && this is NodeWithSimpleName<*> -> {
                val xdClass = DeclaredSymbolResolver.resolveDeclaredType(this)
                xdClass.targetClassName.canonicalName
            }
            this is ObjectCreationExpr -> {
                val xdDeclaredType = DeclaredSymbolResolver.resolveDeclaredType(this.type)
                xdDeclaredType.targetClassName.canonicalName
            }
            this is BinaryExpr -> this.left.describeType()
            else -> throw UnsolvedSymbolException("Could not describe type for ${this::class.java.canonicalName} '$this'\nOringal message:$e")
        }
    }
}
