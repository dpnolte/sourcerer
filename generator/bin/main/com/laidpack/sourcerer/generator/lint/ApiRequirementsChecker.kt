package com.laidpack.sourcerer.generator.lint

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.firstOrNull
import kotlinx.dnq.query.query

class ApiRequirementsChecker(env: SourcererEnvironment, private val classRegistry: ClassRegistry) {
    private val apiDetector = ApiDetector(env.stubAppProjectDir, classRegistry)
    private val minSdkVersion = env.minSdkVersion

    fun canCheckRequirements(classInfo: ClassInfo): Boolean {
        return try {
            apiDetector.getClassVersion(classInfo)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
    fun checkMinApiRequirements(classInfo: ClassInfo, codeBlocks : List<CodeBlock>): ClassApiRequirements {
        val classMinApiLevel = apiDetector.getClassVersion(classInfo)
        val fallbackClassName = getFallbackClassNameIfNeeded(classInfo, classMinApiLevel)
        val codeBlockMinApiLevelList = mutableListOf<Int>()
        for (codeBlock in codeBlocks) {
            var minApiLevelForCodeBlock = ApiDetector.UNKNOWN_OR_VERSION_1
            for (attribute in codeBlock.attributes.values) {
                for (getter in attribute.getters) {
                    val getterMinApiLevel = apiDetector.getGetterVersion(classInfo, getter)
                    if (getterMinApiLevel > minApiLevelForCodeBlock) {
                        minApiLevelForCodeBlock = getterMinApiLevel
                    }
                }
            }
            for (setter in codeBlock.setters.values) {
                val setterMinApiLevel = apiDetector.getSetterVersion(classInfo, setter)
                if (setterMinApiLevel > minApiLevelForCodeBlock) {
                    minApiLevelForCodeBlock = setterMinApiLevel
                }
            }
            codeBlockMinApiLevelList.add(minApiLevelForCodeBlock)
        }

        return ClassApiRequirements(
                classInfo.targetClassName,
                fallbackClassName,
                classMinApiLevel,
                codeBlockMinApiLevelList
        )
    }

    data class ClassApiRequirements(
            val className: ClassName,
            val fallbackClassName: ClassName?,
            val classMinApiLevel: Int,
            val codeBlockMinApiLevelList: List<Int>
    ) {

        fun assignMinApiLevelRequirementsToCodeBlocks(codeBlocks: List<CodeBlock>) {
            if (codeBlockMinApiLevelList.size != codeBlocks.size) {
                throw IllegalArgumentException("Number of code block api requirements doesn't match up with number of code blocks in provided attributesToParameters")
            }
            codeBlocks.forEachIndexed { index, codeBlock ->
                codeBlock.minimumApiLevel = codeBlockMinApiLevelList[index]
            }
        }
    }

    private fun getFallbackClassNameIfNeeded(classInfo: ClassInfo, classMinApiLevel: Int): ClassName? {
        if (classMinApiLevel > minSdkVersion) {
            for (superClassName in classInfo.superClassNames) {
                val superClassMinApiLevel = getSavedMinApiLevelIfAvailable(superClassName)
                        ?: apiDetector.getClassVersion(classRegistry[superClassName] as ClassInfo)
                if (superClassMinApiLevel <= minSdkVersion) {
                    return superClassName
                }
            }
        }
        return null
    }

    private fun getSavedMinApiLevelIfAvailable(className: ClassName): Int? {
        return Store.transactional {
            XdSourcererResult.query(
                    XdSourcererResult::targetClassCanonicalName eq className.canonicalName
            ).firstOrNull()?.minimumApiLevel
        }
    }
}