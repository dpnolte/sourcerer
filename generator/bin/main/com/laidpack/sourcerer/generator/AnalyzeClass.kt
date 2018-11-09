
package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.*
import kotlinx.dnq.query.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    //val input = "WearableDrawerView" // simple name
    val input = "com.google.android.material.chip.ChipGroup" // canonical name
    SourcererEnvironment.printFlowInterpreterTrace = true
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val widgetRegistry = WidgetRegistry.create(env)
    val sourcerer = Sourcerer(env)
    //Store.deleteSourcererResults()

    val indexedClasses = when {
        input.contains(".") ->  {
            val indexedClass = DeclaredTypeRegistry[input]
            if (indexedClass != null) listOf(indexedClass) else listOf()
        }
        else -> DeclaredTypeRegistry.findBySimpleName(input)
    }
    when {
        indexedClasses.size > 1 -> {
            val classNames = Store.transactional {
                indexedClasses.joinToString("\n") { "- ${it.canonicalName}" }
            }
            throw IllegalArgumentException("Multiple classes with (simple) name '$input' indexed.\nClasses:\n$classNames")
        }
        indexedClasses.size == 1 -> {
            val xdTargetType= indexedClasses.first()

            val xdTypesInFile = FileRegistry.getClassesInFile(xdTargetType)
            val xdDerivedTypes = mutableListOf<XdDeclaredType>()
            println("Following types will be invalidated:")
            for (xdClassInFile in xdTypesInFile) {
                println(xdClassInFile.entityId.toString() + ": " + xdClassInFile.targetClassName)
                xdDerivedTypes.addAll(
                        Store.transactional {
                            XdDeclaredType.query(
                                    XdDeclaredType::superClasses contains xdClassInFile
                            ).toList().sortedByDescending { xdDerivedClass -> xdDerivedClass.superClasses.size() }
                        }
                )
            }
            // if any attributes used by a derived class, invalidate these classes as well
            if (xdDerivedTypes.isNotEmpty()) {
                println("Following derived types will be invalidated:")
                for (xdDerivedClass in xdDerivedTypes) {
                    println(xdDerivedClass.entityId.toString() + ": " + xdDerivedClass.targetClassName)
                    FileRegistry.deleteFileIndexByClass(xdDerivedClass)
                }
            }
            FileRegistry.deleteFileIndexByClass(xdTargetType)
            //Store.deleteSourcererResult(simpleName)
        }
        else -> {
            // check if all files are deleted
            val allFilesRemoved = Store.transactional { XdFile.all().size() } == 0
            if (!allFilesRemoved) {
                throw java.lang.IllegalArgumentException("No class found with (simple) name '$input'")
            }
        }
    }

    TypeIndexer.scan(widgetRegistry, reindexAll = false)
    val xdTargetType = when {
        input.contains(".") -> {
            DeclaredTypeRegistry[input] ?: throw IllegalStateException("Class '$input' not found")
        }
        else -> DeclaredTypeRegistry.findBySimpleName(input).first()
    }
    val typeDeclaration = xdTargetType.getTypeDeclaration()
    if (typeDeclaration is ClassOrInterfaceDeclaration) {
        sourcerer.generateFactoriesForClass(xdTargetType)
        BaseNodeHandler.printExecutionTimes()
    } else {
        TypeBodyPeeker.peek(typeDeclaration, xdTargetType)
    }
}

