package com.laidpack.sourcerer.generator.target

// this is a tricky relation as attributes and setters are organized per call as identified in code
// a set of attrs are called with the method
class CallSignatureMapList(
        private val setterName: String
): Iterable<Map<String, Int>> {
    val attributesToParameters: MutableList<MutableMap<String /* attribute name*/, Int /* parameter index */>> = mutableListOf()
    operator fun get(attrName: String): Int {
        return attributesToParameters.find { it.containsKey(attrName) }?.get(attrName)
                ?: throw java.lang.IllegalArgumentException("Setter $setterName contains no attribute $attrName")
    }
    operator fun get(attribute: Attribute): Int {
        return attributesToParameters.find { it.containsKey(attribute.name) }?.get(attribute.name)
                ?: throw java.lang.IllegalArgumentException("Setter $setterName contains no attribute ${attribute.name}")
    }
    override fun iterator(): Iterator<Map<String, Int>> {
        return attributesToParameters.iterator()
    }
    fun containsAttribute(attrName: String): Boolean {
        return attributesToParameters.any { it.containsKey(attrName) }
    }
    fun findByAttributeNames(attributeNames: List<String>): MutableMap<String, Int>? {
        for (attrName in attributeNames) {
            if (containsAttribute(attrName)) {
                return getCallSignatureMap(attrName)
            }
        }
        return null
    }
    fun add(attributesToParameters: MutableMap<String, Int>) {
        if (hasCallSignatureMap(attributesToParameters)) throw java.lang.IllegalArgumentException("Already added signature call: $setterName(${attributesToParameters.map { "${it.key}:${it.value}" }.joinToString()})")
        this.attributesToParameters.add(attributesToParameters)
    }
    fun add(attr: Attribute, parameterIndex: Int, otherAttributes: List<String>) {
        val map = findByAttributeNames(otherAttributes)
        if (map != null) {
            map[attr.name] = parameterIndex
        } else {
            attributesToParameters.add(mutableMapOf(attr.name to parameterIndex))
        }
    }
    fun hasCallSignatureMap(attributesToParameters: MutableMap<String, Int>): Boolean {
        return this.attributesToParameters.any { it == attributesToParameters }
    }
    fun getCallSignatureMap(attrName: String): MutableMap<String, Int> {
        return attributesToParameters.find { it.containsKey(attrName) }
            ?: throw java.lang.IllegalArgumentException("Setter $setterName contains no attribute $attrName")
    }
    fun getCallSignatureMap(attr: Attribute): MutableMap<String, Int> {
        return getCallSignatureMap(attr.name)
    }
    fun getCallSignatureMap(codeBlock: CodeBlock): MutableMap<String, Int> {
        return getCallSignatureMap(codeBlock.attributes)
    }
    fun getCallSignatureMap(attributes: Map<String, Attribute>): MutableMap<String, Int> {
        for (attribute in attributes.values) {
            try {
                return getCallSignatureMap(attribute)
            } catch (e: java.lang.IllegalArgumentException) {
                continue
            }
        }
        throw java.lang.IllegalArgumentException("Setter $setterName contains none of the following attributes in the code block: ${attributes.keys.joinToString()}")
    }
    fun getCallSignatureMapUsedByAnOfTheseAttributes(attributes: Iterable<Attribute>): Map<String, Int> {
        for (attribute in attributes) {
            if (containsAttribute(attribute.name)) {
                return getCallSignatureMap(attribute.name)
            }
        }
        throw java.lang.IllegalArgumentException("Setter $setterName has none of the following attributes mapped: ${attributes.joinToString { it.name }}")
    }
    fun getCallUsedByThisAttribute(attribute: Attribute): Map<String, Int> {
        return getCallSignatureMapUsedByAnOfTheseAttributes(listOf(attribute))
    }

    fun size(attr: Attribute): Int {
        return getCallSignatureMap(attr).size
    }
}