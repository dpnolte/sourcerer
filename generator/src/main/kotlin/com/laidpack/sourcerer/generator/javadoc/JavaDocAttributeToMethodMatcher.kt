package com.laidpack.sourcerer.generator.javadoc

import com.github.javaparser.javadoc.JavadocBlockTag
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Parameter
import com.laidpack.sourcerer.generator.target.Setter

open class JavaDocAttributeToMethodMatcher(
        private val methodInfo: MethodInfo,
        private val classInfo: ClassInfo,
        private val isThisTheTargetingAttribute: (attributeName: String) -> Boolean,
        private val attrManager: AttributeManager
) {
    private val parameters = attrManager.getParameters(methodInfo.methodDeclaration)
    private val parameterNames = parameters.map { it.name }.toSet()
    private val attributeBlockTags = methodInfo.javadoc.blockTags.filter { it.tagName == "attr" }
    private val hasOneAttributeBlockTag = attributeBlockTags.size == 1
    private val hasOneParameter = parameters.size == 1

    data class JavaDocAttributeMatchResult (
            val success: Boolean,
            val attributeName: String?  = null,
            val parameter: Parameter? = null
    )
    fun match(): JavaDocAttributeMatchResult {
        for (blockTag in attributeBlockTags) {
            if (blockTag.tagName == "attr") {
                val attributeName = getAttributeName(blockTag)
                // assume match if we only have one parameter and one attribute block tag and we can find a matching getter
                if (hasOneAttributeBlockTag
                        && hasOneParameter
                        && isThisTheTargetingAttribute(attributeName)
                        && canAttributeTypeBeConvertedToParameterType(attributeName)) {
                    return JavaDocAttributeMatchResult(
                            true,
                            attributeName,
                            parameters.first()
                    )
                    // otherwise match based on name
                } else {
                    val parameter = guessMatchingParameterByName(attributeName)
                    if (parameter != null) {
                        return JavaDocAttributeMatchResult(
                                true,
                                attributeName,
                                parameter
                        )
                    }
                }

            }
        }
        return JavaDocAttributeMatchResult(false)
    }


    private fun canAttributeTypeBeConvertedToParameterType(attributeName: String, providedParameter: Parameter? = null): Boolean {
        val parameter = providedParameter ?: if (hasOneParameter) parameters.first() else throw IllegalStateException("No parameter provided for multi parameter setter")
        val canBeAssigned = attrManager.canAttributeTypeBeConvertedToType(attributeName, parameter.describedType)
        if (!canBeAssigned) {
            println("Skipping potential setter ${methodInfo.methodDeclaration.nameAsString}, because attribute $attributeName cannot be assigned to parameter ${parameter.name}. Parameter type: ${parameter.describedType} vs. Attribute format(s): ${attrManager.getAttributeFormats(attributeName).joinToString(", ")}")
        }
        return canBeAssigned
    }

    private fun getAttributeName(blockTag: JavadocBlockTag): String {
        val content = blockTag.content.toText()
        var attributeName = when {
            attributeNameRegex.matches(content) -> {
                val matchResult = attributeNameRegex.find(content) as MatchResult
                matchResult.groupValues[1]
            }
            content.startsWith("name android:") -> {
                content.substring(content.lastIndexOf(':'))
            }
            else -> throw IllegalStateException("attribute name regex no longer matches attribute name?")
        }

        if (classInfo.classCategory == ClassCategory.LayoutParams) {
            val simpleName = classInfo.targetClassName.simpleName.replace("LayoutParams", "Layout")
            attributeName = attributeName.replace(simpleName + "_", "")
        }
        return attributeName
    }

    private fun guessMatchingParameterByName(attrName: String): Parameter? {
        val paramNameGuesses = mutableListOf(
                attrName
        )
        if (attrName.containsAnyCapitalChar()) {
            paramNameGuesses.add(attrName.substring(attrName.indexOfLastCapitalChar()).decapitalize())
        }
        if (attrName.startsWith("layout_")) {
            paramNameGuesses.add(attrName.substring(7))
        }
        for (paramNameGuess in paramNameGuesses) {
            if (parameterNames.contains(paramNameGuess)) {
                return parameters.first { it.name == paramNameGuess }
            }
        }
        return null
    }

    private fun String.containsAnyCapitalChar(): Boolean {
        return this.any { it.isUpperCase() }
    }
    private fun String.indexOfLastCapitalChar(): Int {
        var upperCharIndex = -1
        for(index in 0 until this.length) {
            val c = this[index]
            if (c.isUpperCase()) {
                upperCharIndex = index
            }
        }
        if (upperCharIndex == -1) throw IllegalStateException("string '$this' does not contain a upper char")
        return upperCharIndex
    }

    companion object  {
        val allowedConversions = DelegateGeneratorBase.transformingMethodTypes.keys
        val attributeNameRegex = Regex(".*?R\\.styleable.*?_(\\w+).*?", RegexOption.DOT_MATCHES_ALL)
    }
}