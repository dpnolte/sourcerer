package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.*
import kotlinx.dnq.query.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val input = "TabItem" // simple name
    //val input = "com.google.android.material.chip.ChipGroup" // canonical name
    SourcererEnvironment.printFlowInterpreterTrace = true
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val sourcerer = Sourcerer(env)

    val xdTargetType = when {
        input.contains(".") -> {
            DeclaredTypeRegistry[input] ?: throw IllegalStateException("Class '$input' not found")
        }
        else -> {
            val xdDeclaredTypes = DeclaredTypeRegistry.findBySimpleName(input)
            when (xdDeclaredTypes.size) {
                0 -> throw IllegalStateException("No class found with simple name $input")
                1 -> xdDeclaredTypes.first()
                else -> throw IllegalStateException("Multiple classes found with simple name $input\nFound classes:\n${xdDeclaredTypes.joinToString { "- ${it.targetClassName}\n" }}")
            }
        }
    }

    Store.transactional {
        clearClassInfo(xdTargetType)
    }

    val typeDeclaration = xdTargetType.getTypeDeclaration()
    if (typeDeclaration is ClassOrInterfaceDeclaration) {
        sourcerer.generateFactoriesForClass(xdTargetType)
        BaseNodeHandler.printExecutionTimes()
    } else {
        TypeBodyPeeker.peek(typeDeclaration, xdTargetType)
    }
}

fun clearClassInfo(xdDeclaredType: XdDeclaredType) {
    println("Removing class info for ${xdDeclaredType.entityId} - ${xdDeclaredType.targetClassName}")
    xdDeclaredType.resolvedBody = false
    xdDeclaredType.resolvedClassSymbol = false
    xdDeclaredType.resolvedWidgetSymbol = false

    val xdDependingTypes = xdDeclaredType.declaredMethods.toList().map {
        it.parameters.clear()
        it.classes.toList()
    }
            .flatten()
            .filter { it != xdDeclaredType }
            .toMutableSet()

    xdDependingTypes.addAll(xdDeclaredType.declaredMethods.toList().map {
        it.classes.toList()
    }
            .flatten()
            .filter { it != xdDeclaredType }
    )
    for (xdDependingType in xdDependingTypes.sortedByDescending { it.superClasses.size() }) {
        clearClassInfo(xdDependingType)
    }

    val sourcererResult = xdDeclaredType.sourcererResult
    sourcererResult?.let {
        it.attributes.toList().forEach { xdAttr ->
            xdAttr.formats.clear()
            xdAttr.enumValues.clear()
            xdAttr.setters.clear()
            xdAttr.getters.clear()
            xdAttr.typesPerSetter.clear()
        }
        it.attributes.clear()
        it.codeBlocks.toList().forEach { xdCodeBlock -> xdCodeBlock.setters.clear() }
        it.setters.toList().forEach { xdSetter ->
            xdSetter.callSignatureMaps.toList().forEach { xdCallSignatureMap ->
                xdCallSignatureMap.attributesToParameters.clear()
            }
            xdSetter.callSignatureMaps.clear()
            xdSetter.parameters.clear()
        }
        it.setters.clear()
        it.codeBlocks.clear()
    }
    xdDeclaredType.sourcererResult = null
    xdDeclaredType.constructors.toList().forEach { it.parameters.clear() }
    xdDeclaredType.constructors.clear()
    xdDeclaredType.methods.clear()
    xdDeclaredType.declaredMethods.clear()
    xdDeclaredType.fields.clear()
    xdDeclaredType.declaredFields.clear()
    xdDeclaredType.enumEntries.clear()
}
