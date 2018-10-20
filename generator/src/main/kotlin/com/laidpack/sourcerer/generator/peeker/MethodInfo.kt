package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.javadoc.description.JavadocDescription
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration

data class MethodInfo(
        val methodDeclaration: MethodDeclaration
): CallableInfo {
    val resolvedMethodDeclaration: ResolvedMethodDeclaration by lazy {
        methodDeclaration.resolve()
    }
    val javadoc: Javadoc by lazy {
        if (methodDeclaration.comment.isPresent) {
            JavaParser.parseJavadoc(methodDeclaration.comment.get().toString())
        } else {
            Javadoc(JavadocDescription())
        }
    }
    override val callableDeclaration: CallableDeclaration<*>
        get() = methodDeclaration

    val line : Int = methodDeclaration.begin.get().line
    fun describeReturnType(): String {
        return try {
            resolvedMethodDeclaration.returnType.describe()
        } catch (e: Exception) {
            val returnType = methodDeclaration.type
            if (returnType.isClassOrInterfaceType) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(returnType.asClassOrInterfaceType())
                indexedClass.targetClassName.canonicalName
            } else throw e
        }
    }
}

