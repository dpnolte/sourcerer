package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.resources.Widget
import com.squareup.kotlinpoet.ClassName

data class ClassSymbolDescription (
        val targetClassName: ClassName,
        val widget: Widget?,
        val classCategory : ClassCategory,
        val isViewGroup: Boolean,
        val classDeclarationProvider: () -> ClassOrInterfaceDeclaration,
        val superClassNames: List<ClassName>,
        val xdClass: XdClass
)
