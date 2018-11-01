package com.laidpack.sourcerer.generator.javadoc

import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.index.ClassInfo
import com.laidpack.sourcerer.generator.index.XdMethod
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Parameter
import com.laidpack.sourcerer.generator.target.Setter

class JavaDocForSetterInterpreter(
        xdMethod: XdMethod,
        classInfo: ClassInfo,
        attrManager: AttributeManager
) : JavaDocAttributeToMethodMatcher(xdMethod, classInfo, attrManager::isAttributeDefined, attrManager) {
    private val attributes = mutableMapOf<String, Attribute>()
    private val setters = mutableMapOf<Int, Setter>()

    fun interpret(earlierIdentifiedSetters: Map<Int, Setter>): Interpretation {
        val result = match()
        if (result.success) {
            val setter = attrManager.getSetter(earlierIdentifiedSetters, xdMethod)
            val attributesToParameters = mutableMapOf<Attribute, Int>()
            for (match in result.matches) {
                val attribute = handleMatch(setter, match.attributeName, match.parameter) ?: continue
                attributesToParameters[attribute] = match.parameter.index
            }
            if (attributesToParameters.isNotEmpty()) {
                attrManager.linkAttributesAndSetter(attributesToParameters, setter)
            }
        }

        if (!isEachParameterMapped(setters)) {
            setters.clear()
            attributes.clear()
        }
        return Interpretation(attributes, setters)
    }

    private fun handleMatch(setter: Setter, attributeName: String, parameter: Parameter): Attribute? {
        val attribute = Attribute(classInfo.xdDeclaredType.targetClassName, attributeName)
        attribute.typesPerSetter[setter.hashCode()] = AttributeTypesForSetter(parameter.describedType)
        return if (ensureGetterCanBeResolved(attribute, setter, parameter.index)) {
            attributes[attribute.name] = attribute
            setters[setter.hashCode()] = setter
            attribute
        } else {
            null
        }
    }

    private fun ensureGetterCanBeResolved(attribute: Attribute, setter: Setter, parameterIndex: Int): Boolean {
        return attrManager.canResolveGetter(attribute, setter, parameterIndex)
    }

    private fun isEachParameterMapped(setters: Map<Int, Setter>): Boolean {
        setters.values.forEach {setter ->
            if (setter.parameters.size != setter.callSignatureMaps.size(attributes.values.first())) {
                return false
            }
        }
        return true
    }

}