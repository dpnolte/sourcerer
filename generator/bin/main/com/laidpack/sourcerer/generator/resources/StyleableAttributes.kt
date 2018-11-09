package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.ResolvedStatus
import com.laidpack.sourcerer.generator.index.ClassInfo
import com.squareup.kotlinpoet.ClassName
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.XdEntity
import kotlinx.dnq.XdNaturalEntityType
import kotlinx.dnq.xdRequiredIntProp
import kotlinx.dnq.xdRequiredStringProp
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.lang.NumberFormatException
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory


class StyleableAttributeManager(private val env: SourcererEnvironment) {
    private val docFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docFactory.newDocumentBuilder()
    private val docs = mutableMapOf<String, Document>()
    private val xpFactory = XPathFactory.newInstance()
    private val defaultResourcesNode by lazy {getResourcesNode(defaultDocUrl)}
    private val rootLevelAttributes by lazy {getStylableAttributes(
            defaultResourcesNode,
            checkIfAttributeIsDefinedAtRoot = false
    )}
    private val defaultAttributes by lazy {getAllDefaultAttributes()}
    private val defaultDocUrl by lazy {env.sdkStructure.attributesFilePath.toUri().toURL()}

    fun hasAttributesDefined(className: ClassName, widget: Widget): Boolean {
        return widget.hasAttributesFile && findStyleable(getStyleableNameFromClassName(className), widget) != null
    }

    private fun getDoc(url: URL) : Document {
        val urlAsString = url.toString()
        if (!docs.containsKey(urlAsString)) {
            val inputStream = url.openStream()
            docs[urlAsString] = docBuilder.parse(inputStream)
            inputStream.close()
        }
        return docs[urlAsString] as Document
    }

    fun getStyleableNameFromClassName(className: ClassName): String {
        // check if static class..
        val splitName = className.canonicalName.split(".")
        val lastNamePart = splitName[splitName.lastIndex]
        val isStatic = lastNamePart.contains("$")
        val staticNameSplit = if (isStatic) {
            lastNamePart.split("$")
        } else listOf(lastNamePart)
        return when {
            isStatic && staticNameSplit.last().contains("LayoutParams")
                -> staticNameSplit.first() + "_" + staticNameSplit.last().replace("LayoutParams", "Layout")
            isStatic
                -> staticNameSplit.last()
            lastNamePart.contains("LayoutParams")
                -> splitName[splitName.lastIndex - 1] + "_" + lastNamePart.replace("LayoutParams", "Layout")
            else -> lastNamePart
        }
    }

    fun getAttributesFromXml(classInfo: ClassInfo): Map<String, Attribute> {
        // findOrCreate annotations from xml
        val styleableAttributes = getStylableAttributes(
                getStyleableNameFromClassName(classInfo.xdDeclaredType.targetClassName), classInfo.xdDeclaredType.widget as Widget
        )

        val attributes = mutableMapOf<String, Attribute>()
        styleableAttributes.values.forEach { styleableAttr ->
            val attribute = Attribute(
                    classInfo.xdDeclaredType.targetClassName,
                    styleableAttr.name,
                    styleableAttr.formats,
                    styleableAttr.flags,
                    styleableAttr.enumValues,
                    ResolvedStatus.IDENTIFIED_IN_XML
            )
            attributes[attribute.name] = attribute
        }
        return attributes
    }

    fun getAttributesFromXml(attributeNameOrDeclaredStylableName: String, currentClassInfo: ClassInfo): List<Attribute> {
        // Note, all attributes share the same global namespace.
        // That means that even if you create a new attribute inside of a <declare-styleable> element
        // it can be used outside of it
        // and you cannot create another attribute with the same name of a different type.
        val widget = Store.transactional { currentClassInfo.xdDeclaredType.widget as Widget }
        val declaredStylable = findStyleable(attributeNameOrDeclaredStylableName, defaultDocUrl)
            ?: if (widget.hasAttributesFile) {
                findStyleable(attributeNameOrDeclaredStylableName, widget)
            } else null
        if (declaredStylable != null) {
            val stylableAttrs = getStylableAttributes(declaredStylable, checkIfAttributeIsDefinedAtRoot = false)
            val attrs = mutableListOf<Attribute>()
            for (styleableAttr in stylableAttrs.values) {
                attrs.add(
                        Attribute(
                            currentClassInfo.xdDeclaredType.targetClassName,
                            styleableAttr.name,
                            styleableAttr.formats,
                            styleableAttr.flags,
                            styleableAttr.enumValues,
                            ResolvedStatus.IDENTIFIED_IN_XML
                        )
                )
            }
            return attrs
        } else {
            val styleableAttr = rootLevelAttributes[attributeNameOrDeclaredStylableName]
                    ?: findAttributeFromDefaultDoc((attributeNameOrDeclaredStylableName))
                    ?: (if (widget.hasAttributesFile) {
                        findAttributeFromDoc(attributeNameOrDeclaredStylableName, widget)
                       } else null)
                    ?: throw IllegalArgumentException("No attribute or declared styable with name $attributeNameOrDeclaredStylableName in default doc $defaultDocUrl or widget doc ${Store.transactional { widget.attributesXmlUrl }} ")

            return listOf(
                    Attribute(
                        currentClassInfo.xdDeclaredType.targetClassName,
                        styleableAttr.name,
                        styleableAttr.formats,
                        styleableAttr.flags,
                        styleableAttr.enumValues,
                        ResolvedStatus.IDENTIFIED_IN_XML
                )
            )
        }
    }

    private fun findAttributeFromDefaultDoc(attributeName: String): StyleableAttribute? {
        return defaultAttributes[attributeName]
    }
    private fun findAttributeFromDoc(attributeName: String, widget: Widget): StyleableAttribute? {
        val resourcesNode = getResourcesNode(Store.transactional { widget.attributesXmlUrl })
        val stylableAttributes = getAllAttributes(resourcesNode)
        return stylableAttributes[attributeName]
    }

    private fun getAllDefaultAttributes(): Map<String, StyleableAttribute> {
        return getAllAttributes(defaultResourcesNode)
    }
    private fun getAllAttributes(rootNode: Node): Map<String, StyleableAttribute> {
        val xPath = xpFactory.newXPath()
        val selector = ".//attr"
        val nodes =  xPath.compile(selector).evaluate(rootNode, XPathConstants.NODESET) as DTMNodeList
        val attributes = mutableMapOf<String, StyleableAttribute>()
        for (i in 0 until nodes.length) {
            val node = nodes.item(i)
            val attr = getStylableAttribute(node, false)
            if (attr != null) {
                attributes[attr.name] = attr
            }
        }
        return attributes
    }

    private fun getResourcesNode(docUrl: URL): Node {
        val xPath = xpFactory.newXPath()
        val selector = "/resources"
        val nodeList = xPath.compile(selector)
                .evaluate(
                        getDoc(docUrl),
                        XPathConstants.NODESET
                ) as NodeList
        return nodeList.item(0)
    }
    private fun findStyleable(name: String, widget: Widget): Node? {
        return findStyleable(name, widget.attributesXmlUrl)
    }
    private fun findStyleable(name: String, attrsFileUrl: URL): Node? {
        val key = attrsFileUrl.toString() + name
        if (!cachedStyleableNodes.containsKey(key)) {
            val xPath = xpFactory.newXPath()
            val selector = "/resources/declare-styleable[@name='$name']"

            val nodeList = xPath.compile(selector).evaluate(getDoc(attrsFileUrl), XPathConstants.NODESET) as NodeList
            val node = nodeList.item(0)
            cachedStyleableNodes[key] = node
        }
        return cachedStyleableNodes[key]
    }

    private fun getStylableAttributes(styleableName: String, widget: Widget) : Map<String, StyleableAttribute> {
        return getStylableAttributes(styleableName, widget.attributesXmlUrl)
    }
    private fun getStylableAttributes(styleableName: String, attrsFileUrl: URL) : Map<String, StyleableAttribute> {
        if (!cachedAttributes.containsKey(styleableName)) {
            val styleableNode = findStyleable(styleableName, attrsFileUrl)
                    ?: throw IllegalArgumentException("Styleable with name '$styleableName does not exist")
            val attributes = getStylableAttributes(styleableNode)
            cachedAttributes[styleableName] = attributes
        }
        return cachedAttributes[styleableName] as Map<String, StyleableAttribute>
    }

    private fun getStylableAttributes(stylableOrResources: Node, checkIfAttributeIsDefinedAtRoot: Boolean = true) : Map<String, StyleableAttribute> {
        val xPath = xpFactory.newXPath()
        val selector = "attr"
        val nodes =  xPath.compile(selector).evaluate(stylableOrResources, XPathConstants.NODESET) as DTMNodeList
        val attributes = mutableMapOf<String, StyleableAttribute>()
        for (i in 0 until nodes.length) {
            val node = nodes.item(i)
            val attr = getStylableAttribute(node, checkIfAttributeIsDefinedAtRoot)
            if (attr != null) {
                attributes[attr.name] = attr
            }
        }
        return attributes
    }

    private fun getStylableAttribute(node: Node, checkIfAttributeIsDefinedAtRoot: Boolean = true): StyleableAttribute? {
        if (node.hasAttributes() && node.attributes.getNamedItem("name") != null) {
            val name = node.attributes.getNamedItem("name").nodeValue
            if (name.startsWith("__removed")) return null

            val formats = mutableListOf<StyleableAttributeFormat>()
            if (node.attributes.getNamedItem("format") != null) {
                val formatString = node.attributes.getNamedItem("format").nodeValue
                formats.addAll(StyleableAttributeFormat.fromString(formatString))
            }
            val flags = getFlags(node)
            val enumValues = getEnumValues(node)
            when {
                enumValues.isNotEmpty() && !formats.contains(StyleableAttributeFormat.Enum)
                -> formats.add(StyleableAttributeFormat.Enum)
                flags.isNotEmpty() && !formats.contains(StyleableAttributeFormat.Flags)
                -> formats.add(StyleableAttributeFormat.Flags)
                formats.isEmpty() -> {
                    // check if attribute is defined at root level
                    if (checkIfAttributeIsDefinedAtRoot && rootLevelAttributes.containsKey(name)) {
                        val rootLevelAttr = rootLevelAttributes[name] as StyleableAttribute
                        formats.addAll(rootLevelAttr.formats)
                        flags.addAll(rootLevelAttr.flags)
                        enumValues.addAll(rootLevelAttr.enumValues)
                    } else {
                        formats.add(StyleableAttributeFormat.Unspecified)
                    }
                }
            }

            return StyleableAttribute(name, formats, flags, enumValues)
        }
        return null
    }

    private fun getFlags(node: Node): MutableList<StyleableAttributeFlag> {
        val flags = mutableListOf<StyleableAttributeFlag>()
        val xPath = xpFactory.newXPath()
        val flagSelector = "flag"
        val flagNodes = xPath.compile(flagSelector).evaluate(node, XPathConstants.NODESET) as DTMNodeList
        for (i in 0 until flagNodes.length) {
            val flagNode = flagNodes.item(i)
            if (flagNode.hasAttributes() && flagNode.attributes.getNamedItem("name") != null && flagNode.attributes.getNamedItem("value") != null) {
                val valueAsString = flagNode.attributes.getNamedItem("value").nodeValue
                try {
                    val value = when {
                        valueAsString.startsWith("0x") -> valueAsString.substring(2).toInt(16)
                        else -> valueAsString.toInt()
                    }
                    flags.add(StyleableAttributeFlag(
                            flagNode.attributes.getNamedItem("name").nodeValue,
                            value
                    ))
                } catch (e: NumberFormatException) {
                    continue // out of bounds flag.. ignore for now
                }
            }
        }
        return flags
    }


    private fun getEnumValues(node: Node): MutableList<StyleableAttributeEnumValue> {
        val enumValues = mutableListOf<StyleableAttributeEnumValue>()
        val xPath = xpFactory.newXPath()
        val enumSelector = "enum"
        val enumNodes = xPath.compile(enumSelector).evaluate(node, XPathConstants.NODESET) as DTMNodeList
        for (i in 0 until enumNodes.length) {
            val enumNode = enumNodes.item(i)
            if (enumNode.hasAttributes() && enumNode.attributes.getNamedItem("name") != null && enumNode.attributes.getNamedItem("value") != null) {
                val valueAsString = enumNode.attributes.getNamedItem("value").nodeValue
                val value = when {
                    valueAsString.startsWith("0x") -> valueAsString.substring(2).toInt(16)
                    else -> valueAsString.toInt()
                }
                enumValues.add(StyleableAttributeEnumValue(
                        enumNode.attributes.getNamedItem("name").nodeValue,
                        value
                ))
            }
        }
        return enumValues
    }


    fun getAllPossibleFormats(widgetRegistry: WidgetRegistry): Set<String> {
        val formats = mutableSetOf<String>()
        val xPath = xpFactory.newXPath()
        val selector = "/resources/declare-styleable/attr"
        val distinctUrls = mutableSetOf<URL>()
        for(widget in widgetRegistry) {
            if (!distinctUrls.contains(widget.attributesXmlUrl) && widget.hasAttributesFile) {
                distinctUrls.add(widget.attributesXmlUrl)
            }
        }
        for (url in distinctUrls) {
            val nodes = xPath.compile(selector).evaluate(getDoc(url), XPathConstants.NODESET) as DTMNodeList
            for (i in 0 until nodes.length) {
                val node = nodes.item(i)
                if (node.hasAttributes() && node.attributes.getNamedItem("formats") != null) {
                    val attributeFormat = node.attributes.getNamedItem("formats").nodeValue
                    if (!formats.contains(attributeFormat)) {
                        formats.add(attributeFormat)
                    }
                }
            }
        }
        return formats
    }

    companion object {
        private val cachedAttributes = mutableMapOf<String, Map<String, StyleableAttribute>>()
        private val cachedStyleableNodes = mutableMapOf<String, Node?>()
    }
}

data class StyleableAttribute(
        val name: String,
        val formats: MutableList<StyleableAttributeFormat>,
        val flags: MutableList<StyleableAttributeFlag>,
        val enumValues: MutableList<StyleableAttributeEnumValue>
)


data class StyleableAttributeFlag(val name: String, val value: Int) {
    fun toEntity() : XdStyleableAttributeFlag {
        val entity = XdStyleableAttributeFlag.new()
        entity.name = this.name
        entity.value = this.value
        return entity
    }
}
class XdStyleableAttributeFlag (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdStyleableAttributeFlag>()
    var name by xdRequiredStringProp()
    var value by xdRequiredIntProp()

    fun toSnapshot(transaction: Boolean = true): StyleableAttributeFlag {
        val block = {
            StyleableAttributeFlag(this.name, this.value)
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}

data class StyleableAttributeEnumValue(val name: String, val value: Int) {
    fun toEntity() : XdStyleableAttributeEnumValue {
        val entity = XdStyleableAttributeEnumValue.new()
        entity.name = this.name
        entity.value = this.value
        return entity
    }
}
class XdStyleableAttributeEnumValue (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdStyleableAttributeEnumValue>()
    var name by xdRequiredStringProp()
    var value by xdRequiredIntProp()

    fun toSnapshot(transaction: Boolean = true): StyleableAttributeEnumValue {
        val block = {
            StyleableAttributeEnumValue(this.name, this.value)
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}

