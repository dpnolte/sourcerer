package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.ConstructorDeclaration
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration


enum class CallableType {
    Method,
    Constructor
}

interface CallableInfo {
    val callableDeclaration: CallableDeclaration<*>
}

data class ConstructorInfo(
        val constructorDeclaration: ConstructorDeclaration
): CallableInfo {
    val resolvedConstructorDeclaration: ResolvedConstructorDeclaration by lazy {
        constructorDeclaration.resolve()
    }
    override val callableDeclaration: CallableDeclaration<*>
        get() = constructorDeclaration
}

