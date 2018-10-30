package com.laidpack.sourcerer.generator.javadoc

import com.github.javaparser.javadoc.JavadocBlockTag
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.target.Parameter

open class JavaDocAttributeToMethodMatcher(
        protected val methodInfo: MethodInfo,
        protected val classInfo: ClassInfo,
        private val isThisTheTargetingAttribute: (attributeName: String) -> Boolean,
        protected val attrManager: AttributeManager
) {
    protected val parameters = attrManager.getParameters(methodInfo.methodDeclaration)
    private val parameterNames = parameters.map { it.name }.toSet()
    private val attributeBlockTags = methodInfo.javadoc.blockTags.filter { it.tagName == "attr" }
    private val hasOneAttributeBlockTag = attributeBlockTags.size == 1
    private val hasOneParameter = parameters.size == 1

    data class JavaDocAttributeMatch (
        val attributeName: String,
        val parameter: Parameter
    )
    data class JavaDocAttributeMatchResult (
            val success: Boolean,
            val matches: List<JavaDocAttributeMatch>
    )
    fun match(): JavaDocAttributeMatchResult {
        val matches = mutableListOf<JavaDocAttributeMatch>()
        for (blockTag in attributeBlockTags) {
            if (blockTag.tagName == "attr") {
                val attributeName = getAttributeName(blockTag)
                // assume match if we only have one parameter and one attribute block tag and we can find a matching getter
                if (hasOneAttributeBlockTag
                        && hasOneParameter
                        && isThisTheTargetingAttribute(attributeName)
                        && canAttributeTypeBeConvertedToParameterType(attributeName)) {
                    matches.add(JavaDocAttributeMatch(
                            attributeName,
                            parameters.first()
                    ))
                    // otherwise match based on name
                } else {
                    val parameter = guessMatchingParameterByName(attributeName)
                    if (parameter != null) {
                        matches.add(JavaDocAttributeMatch(
                                attributeName,
                                parameter
                        ))
                    }
                }

            }
        }
        return JavaDocAttributeMatchResult(matches.isNotEmpty(), matches)
    }


    private fun canAttributeTypeBeConvertedToParameterType(attributeName: String, providedParameter: Parameter? = null): Boolean {
        val parameter = providedParameter ?: if (hasOneParameter) parameters.first() else throw IllegalStateException("No parameter provided for multi parameter setter")
        val canBeAssigned = attrManager.canAttributeTypeBeConvertedToType(attributeName, parameter.describedType)
        if (!canBeAssigned) {
            println("\t\t\t! - Skipping potential setter ${methodInfo.methodDeclaration.nameAsString}, because attribute $attributeName cannot be assigned to parameter ${parameter.name}. Parameter type: ${parameter.describedType} vs. Attribute formats(s): ${attrManager.getAttributeFormats(attributeName).joinToString(", ")}")
        }
        return canBeAssigned
    }

    private fun getAttributeName(blockTag: JavadocBlockTag): String {
        return getAttributeNameFromBlockTag(blockTag, classInfo)
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
        val allowedConversions = DelegateGeneratorBase.transformFormatToTypeMap.keys
        val attributeNameRegex = Regex(".*?R\\.styleable.*?_(\\w+).*?", RegexOption.DOT_MATCHES_ALL)

        fun getAttributeNameFromBlockTag(blockTag: JavadocBlockTag, classInfo: ClassInfo): String {
            val content = blockTag.content.toText()
            var attributeName = when {
                attributeNameRegex.matches(content) -> {
                    val matchResult = attributeNameRegex.find(content) as MatchResult
                    matchResult.groupValues[1]
                }
                content.startsWith("name android:") -> {
                    content.substring(content.lastIndexOf(':'))
                }
                else -> throw IllegalStateException("attribute name regex no longer matches attribute name? Content: '$content'")
            }

            if (classInfo.classCategory == ClassCategory.LayoutParams) {
                val simpleName = classInfo.targetClassName.simpleName.replace("LayoutParams", "Layout")
                attributeName = attributeName.replace(simpleName + "_", "")
            }
            return attributeName
        }
    }
}