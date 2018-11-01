package com.laidpack.sourcerer.generator.resources

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.FieldDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.index.*
import kotlinx.dnq.query.*

class AndroidResourceManager(private val env: SourcererEnvironment) {
    private fun decompileResourceClass(): String {
        return JavaDecompiler.decompileClass(
                env.sdkStructure.androidJarPath.toString(),
                resourceClassPath
        )
    }

    fun ensureResourceClassIsIndexed() {
        val indexed = Store.transactional {
            XdDeclaredType.query(
                    XdDeclaredType::canonicalName eq resourceCanonicalName
            ).any()
        }
        if (!indexed) {
            indexResourceClass()
        }
    }

    private fun indexResourceClass() {
        println("Indexing android.R.class:")
        println("-------------------------")
        val resourceClassContent = decompileResourceClass()
        val cu = JavaParser.parse(resourceClassContent)
        TypeIndexer.scanCompilationUnit(
                cu,
                env.sdkStructure.platformPath,
                env.sdkStructure.androidResourceClassUrl,
                checkIfTypeIsWidget = false
        )
    }

    fun getAttributeResourceIdByName(resourceName: String): Int {
        ensureResourceClassIsIndexed()
        return Store.transactional {
            val xdAttrClass =  XdDeclaredType.query(
                    XdDeclaredType::canonicalName eq attrCanonicalName
            ).first()
            val classDeclaration = xdAttrClass.getClassOrInterfaceDeclaration()
            if (!xdAttrClass.resolvedBody) {
                TypeBodyPeeker.peek(classDeclaration, xdAttrClass)
            }
            val xdField = xdAttrClass.fields.query(
                    XdField::name eq resourceName
            ).firstOrNull()
                ?: throw IllegalArgumentException("Resource name '$resourceName' is not declared in android.R.attr")
            return@transactional xdField.initializingIntValue
        }
    }

    fun getAttributeResourceNameById(resourceId: Int): String {
        ensureResourceClassIsIndexed()
        return Store.transactional {
            val xdAttrClass =  XdDeclaredType.query(
                    XdDeclaredType::canonicalName eq attrCanonicalName
            ).first()
            val classDeclaration = xdAttrClass.getClassOrInterfaceDeclaration()
            if (!xdAttrClass.resolvedBody) {
                TypeBodyPeeker.peek(classDeclaration, xdAttrClass)
            }
            val xdField = xdAttrClass.fields.query(
                    XdField::initializingIntValue eq resourceId
            ).firstOrNull()
                    ?: throw IllegalArgumentException("No field with value '$resourceId' is declared in android.R.attr")
            return@transactional xdField.name
        }
    }


    companion object {
        private const val resourceClassPath = "android/R.class"
        private const val resourceSimpleName = "R"
        const val resourceCanonicalName = "android.$resourceSimpleName"
        private const val attrCanonicalName = "$resourceCanonicalName.attr"
    }
}