package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter

class CodeBlockCollector(private val classInfo: ClassInfo, private val attributeManager: AttributeManager) {
    fun reflectOnCodeBlockSociety(attributes: Map<String, Attribute>, setters: Map<Int, Setter>): List<CodeBlock> {
        val codeBlocks = mutableListOf<CodeBlock>()

        // Find all attributes that share setters..
        // example: methodA(attr1, attr2) + methodB(attr2, attr3) --> code block should contain attr1-3 and setters method A & B
        val processedAttributes = mutableSetOf<String>()
        for(attribute in attributes.values) {
            if (processedAttributes.contains(attribute.name)) continue

            // is attribute declared in super class? ensure attribute type of super class definition is used
            attributeManager.assignAnyAttributeDeclaredInSuperClass(attribute, classInfo)
            if (attribute.isDeclaredInSuperClass) {
                attributeManager.replaceAttributeFormatFromTo(attribute.attributeDeclaredInSuperClass, attribute)
            }

            val codeBlockSetters = mutableMapOf<Int, Setter>()
            val codeBlockAttributes = mutableMapOf<String, Attribute>()
            recursiveCodeBlockFind(
                    attribute,
                    processedAttributes,
                    codeBlockSetters,
                    codeBlockAttributes,
                    setters,
                    attributes
            )
            if (codeBlockAttributes.size == 1 && codeBlockSetters.size > 1) {
                val singleAttribute = codeBlockAttributes.values.first()
                if (!singleAttribute.oneFormatRequiresMultipleSetters && singleAttribute.formats.size  == 1) {
                    // select most appropriate setter for single formatted attributes (least parameters)
                    val selectedSetter : Int = codeBlockSetters.values.sortedBy { it.parameters.size }.first().hashCode()
                    for (setterHashCode in singleAttribute.setterHashCodes) {
                        if (setterHashCode != selectedSetter) {
                            singleAttribute.typesPerSetter.remove(setterHashCode)
                            codeBlockSetters.remove(setterHashCode)
                        }
                    }
                    singleAttribute.setterHashCodes.removeIf { it != selectedSetter }
                }
            }
            val codeBlock = CodeBlock(
                    codeBlockSetters,
                    codeBlockAttributes
            )

            codeBlocks.add(codeBlock)
        }

        return codeBlocks
    }


    private fun recursiveCodeBlockFind(
            attribute: Attribute,
            processedAttributes: MutableSet<String>,
            codeBlockSetters: MutableMap<Int, Setter>,
            codeBlockAttributes: MutableMap<String, Attribute>,
            setters: Map<Int, Setter>,
            attributes: Map<String, Attribute>
    ) {
        if (processedAttributes.contains(attribute.name)) return
        processedAttributes.add(attribute.name)
        codeBlockAttributes[attribute.name] = attribute

        for(setterKey in attribute.setterHashCodes) {
            if (!codeBlockSetters.containsKey(setterKey)) {
                val setter = setters[setterKey] as Setter
                codeBlockSetters[setterKey] = setter
                setter.callSignatureMaps.getCallSignatureMap(attribute.name).keys.forEach {
                    if (it != attribute.name) {
                        recursiveCodeBlockFind(
                                attributes[it] as Attribute,
                                processedAttributes,
                                codeBlockSetters,
                                codeBlockAttributes,
                                setters,
                                attributes
                        )
                    }
                }
            }

        }
    }
}