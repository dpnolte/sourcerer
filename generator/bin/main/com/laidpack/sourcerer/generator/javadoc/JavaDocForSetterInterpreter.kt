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

class JavaDocForSetterInterpreter(
        methodInfo: MethodInfo,
        classInfo: ClassInfo,
        attrManager: AttributeManager
) : JavaDocAttributeToMethodMatcher(methodInfo, classInfo, attrManager::isAttributeDefined, attrManager) {
    private val attributes = mutableMapOf<String, Attribute>()
    private val setters = mutableMapOf<Int, Setter>()

    fun interpret(): Interpretation {
        val result = match()
        if (result.success) {
            for (match in result.matches) {
                handleMatch(match.attributeName, match.parameter)
            }
        }

        if (!isEachParameterMapped(setters)) {
            setters.clear()
            attributes.clear()
        }
        return Interpretation(attributes, setters)
    }

    private fun handleMatch(attributeName: String, parameter: Parameter) {
        val attribute = Attribute(classInfo.targetClassName, attributeName)
        val setter = attrManager.getSetter(setters, methodInfo)
        attrManager.linkAttributeAndSetter(attribute, setter, parameter.index)
        attribute.typesPerSetter[setter.hashCode()] = AttributeTypesForSetter(listOf(parameter.describedType))
        if (ensureGetterCanBeResolved(attribute, setter)) {
            attributes[attribute.name] = attribute
            setters[setter.hashCode()] = setter
        } else {
            setter.attributeToParameter.remove(attributeName)
        }
    }

    private fun ensureGetterCanBeResolved(attribute: Attribute, setter: Setter): Boolean {
        return attrManager.canResolveGetter(attribute, setter)
    }

    private fun isEachParameterMapped(setters: Map<Int, Setter>): Boolean {
        setters.values.forEach {setter ->
            if (setter.parameters.size != setter.attributeToParameter.size) {
                return false
            }
        }
        return true
    }

}