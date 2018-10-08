package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.ResolvedStatus
import com.laidpack.sourcerer.generator.peeker.ClassInfo
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
import java.net.URL
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory


class StyleableAttributeManager {
    private val docFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docFactory.newDocumentBuilder()
    private val docs = mutableMapOf<String, Document>()
    private val xpFactory = XPathFactory.newInstance()

    fun hasAttributesDefined(className: ClassName, widget: Widget): Boolean {
        return widget.hasAttributesFile && findStyleable(getStyleableNameFromClassName(className), widget) != null
    }

    private fun getDoc(widget: Widget) : Document {
        val url = widget.attributesXmlUrl
        return getDoc(url)
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

    private fun getStyleableNameFromClassName(className: ClassName): String {
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
                getStyleableNameFromClassName(classInfo.targetClassName), classInfo.widget
        )

        val attributes = mutableMapOf<String, Attribute>()
        styleableAttributes.values.forEach { styleableAttr ->
            val attribute = Attribute(
                    classInfo.targetClassName,
                    styleableAttr.name,
                    styleableAttr.format,
                    styleableAttr.flags,
                    styleableAttr.enumValues,
                    ResolvedStatus.IDENTIFIED_IN_XML
            )
            attributes[attribute.name] = attribute
        }
        return attributes
    }

    private fun findStyleable(name: String, widget: Widget): Node? {
        if (!cachedStyleableNodes.containsKey(name)) {
            val xPath = xpFactory.newXPath()
            val selector = "/resources/declare-styleable[@name='$name']"

            val nodeList = xPath.compile(selector).evaluate(getDoc(widget), XPathConstants.NODESET) as NodeList
            val node = nodeList.item(0)
            cachedStyleableNodes[name] = node
        }
        return cachedStyleableNodes[name]
    }

    private fun getStylableAttributes(styleableName: String, widget: Widget) : Map<String, StyleableAttribute> {
        if (!cachedAttributes.containsKey(styleableName)) {
            val styleableNode = findStyleable(styleableName, widget)
                    ?: throw IllegalArgumentException("Styleable with name '$styleableName does not exist")
            val attributes = getStylableAttributes(styleableNode)
            cachedAttributes[styleableName] = attributes
        }
        return cachedAttributes[styleableName] as Map<String, StyleableAttribute>
    }

    private fun getStylableAttributes(stylable: Node) : Map<String, StyleableAttribute> {
        val xPath = xpFactory.newXPath()
        val selector = "attr"
        val nodes =  xPath.compile(selector).evaluate(stylable, XPathConstants.NODESET) as DTMNodeList
        val attributes = mutableMapOf<String, StyleableAttribute>()
        for (i in 0 until nodes.length) {
            val node = nodes.item(i)
            if (node.hasAttributes() && node.attributes.getNamedItem("name") != null) {
                val name = node.attributes.getNamedItem("name").nodeValue
                if (name.startsWith("__removed")) continue

                val format = mutableListOf<StyleableAttributeFormat>()
                if (node.attributes.getNamedItem("format") != null) {
                    val formatString = node.attributes.getNamedItem("format").nodeValue
                    format.addAll(StyleableAttributeFormat.fromString(formatString))
                }
                val flags = getFlags(node)
                val enumValues = getEnumValues(node)
                if (format.isEmpty()) {
                    when {
                        enumValues.isNotEmpty() -> format.add(StyleableAttributeFormat.Enum)
                        flags.isNotEmpty() -> format.add(StyleableAttributeFormat.Integer)
                        else -> format.add(StyleableAttributeFormat.Unspecified)
                    }
                }
                val attribute = StyleableAttribute(name, format, flags, enumValues)
                attributes[attribute.name] = attribute
            }
        }
        return attributes
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
                val value = when {
                    valueAsString.startsWith("0x") -> valueAsString.substring(2).toInt(16)
                    else -> valueAsString.toInt()
                }
                flags.add(StyleableAttributeFlag(
                        flagNode.attributes.getNamedItem("name").nodeValue,
                        value
                ))
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
                if (node.hasAttributes() && node.attributes.getNamedItem("format") != null) {
                    val attributeFormat = node.attributes.getNamedItem("format").nodeValue
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
        val format: MutableList<StyleableAttributeFormat>,
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

