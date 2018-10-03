package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.generators.MultiFormatGenerator
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.*
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.KClass

class TypePhilosopher(private val attrManager: AttributeManager, private val classInfo: ClassInfo) {
    private val cachedFormatsReservedForSetters = mutableMapOf<Int /*setter hash code */,  StyleableAttributeFormat?>()

    fun contemplateOnTheMeaningOfTypes(codeBlocks: List<CodeBlock>): List<CodeBlock> {
        val selectedCodeBlocks = mutableListOf<CodeBlock>()
        // assign setter types first before we can assign formats per setter (see assignAttributeType)
        codeBlocks.forEach { codeBlock ->
            for (setter in codeBlock.setters) {
                for (attrName in setter.attributeToParameter.keys) {
                    val attribute = codeBlock.attributes[attrName] as Attribute
                    val typesForThisSetter = attribute.resolvedTypesPerSetter(setter.hashCode())
                    assignFormatToAttributeParameter(attribute, setter, typesForThisSetter)
                    assignSetterType(typesForThisSetter)
                }
            }
        }
        // assign - amongst others - formats per setter
        codeBlocks.forEach { codeBlock ->
            var assignedGetter = false
            for (setter in codeBlock.setters) {
                for (attrName in setter.attributeToParameter.keys) {
                    val attribute = codeBlock.attributes[attrName] as Attribute
                    val typesForThisSetter = attribute.resolvedTypesPerSetter(setter.hashCode())
                    assignAttributeType(attribute, setter, typesForThisSetter, codeBlock)
                    attribute.localVariableName = "immutable${attribute.name.toCamelCase()}"
                    try {
                        assignGettersToAttribute(attribute, setter)
                        assignedGetter = true
                    } catch (e: IllegalStateException) {
                        if (codeBlock.setters.size > 1) {
                            println("Skipping setter ${setter.name} for attribute $attrName. There are other setters")
                        }
                    }
                }
            }
            if (!assignedGetter) {
                //throw IllegalStateException("Could not match getter with any setters. Setter method name(s): '${codeBlock.setters.joinToString(", "){ it.name }}', Attribute(s): '${codeBlock.attributes.values.joinToString(", "){ it.name }}'")
                println("Skipping code block (no getter found). Setter method name(s): '${codeBlock.setters.joinToString(", "){ it.name }}', Attribute(s): '${codeBlock.attributes.values.joinToString(", "){ it.name }}'")
            } else {
                selectedCodeBlocks.add(codeBlock)
            }
        }

        return selectedCodeBlocks
    }

    private fun assignSetterType(typesForThisSetter: AttributeTypesForSetter) {
        typesForThisSetter.mutableSetterType = considerWhatTypeReallyIsAtTheRudimentaryLevel(typesForThisSetter.setterClassName)
        typesForThisSetter.mutableResolvedSetterType = typesForThisSetter.setterType
        if (typesForThisSetter.setterClassName.endsWith("[]")) {
            typesForThisSetter.mutableSetterCompoundType = considerWhatTypeReallyIsAtTheRudimentaryLevel(typesForThisSetter.setterClassName.substring(0, typesForThisSetter.setterClassName.length - 2))
            typesForThisSetter.mutableResolvedSetterType = typesForThisSetter.setterCompoundType
        }
    }

    private fun assignAttributeType(attribute: Attribute, setter: Setter, typesForThisSetter: AttributeTypesForSetter, codeBlock: CodeBlock) {
        val result = thinkAboutHowThisAttributeTypeIsUnlikeOthers(attribute, setter, typesForThisSetter, codeBlock)
        typesForThisSetter.mutableAttributeType = result.typeName
        typesForThisSetter.attributeCanonicalNames.addAll(result.canonicalNames)
        typesForThisSetter.formats.addAll(result.formats)
        if (attribute.enumValues.isNotEmpty() || attribute.flags.isNotEmpty()) {
            typesForThisSetter.hasEnumAsAttributeType = true
            typesForThisSetter.enumClassName = result.enumClassName
        }
    }

    private fun assignGettersToAttribute(attribute: Attribute, setter: Setter) {
        val getters = attrManager.getResolvedGetters(
                attribute,
                setter
        )
        // assign formats to getters
        for (getter in getters) {
            val typeName = considerWhatTypeReallyIsAtTheRudimentaryLevel(getter.describedReturnType)
            getter.mutableTypeName = typeName
        }
        setter.mutablePropertyName = attrManager.getResolvedSetterPropertyName(setter)
        attribute.getters.addAll(getters)
    }


    fun reflectOnCodeBlockSociety(attributes: Map<String, Attribute>, setters: Map<Int, Setter>): List<CodeBlock> {
        val codeBlocks = mutableListOf<CodeBlock>()

        // Find all attributes that share a setter..
        // example: methodA(param1, param2) + methodB(param2, param3) --> code block should contain param1-3 and setters method A & B
        val processedAttributes = mutableSetOf<String>()
        for(attribute in attributes.values) {
            if (processedAttributes.contains(attribute.name)) continue

            // is attribute declared in super class? ensure attribute type of super class definition is used
            attrManager.assignAnyAttributeDeclaredInSuperClass(attribute, classInfo)
            if (attribute.isDeclaredInSuperClass) {
                attrManager.replaceAttributeFormatFromTo(attribute.attributeDeclaredInSuperClass, attribute)
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
                if (singleAttribute.formats.size  == 1) {
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
                    codeBlockSetters.values.toList(),
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
                setter.attributeToParameter.keys.forEach {
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

    private fun String.toCamelCase(): String {
        return this.split('_').joinToString("") { it.capitalize() }
    }

    private data class AttributeTypeResult(val typeName: TypeName, val formats: Set<StyleableAttributeFormat>, val enumClassName : TypeName? = null ) {
        val canonicalNames: List<String> = formats.map { it.toClass().java.canonicalName }
    }
    private fun thinkAboutHowThisAttributeTypeIsUnlikeOthers(
            attribute: Attribute,
            setter: Setter,
            typesForThisSetter: AttributeTypesForSetter,
            codeBlock: CodeBlock
    ): AttributeTypeResult {
        return when {
            attribute.formats.size == 1 && attribute.enumValues.isNotEmpty() -> {
                val className = ClassName(SourcererEnvironment.generatedPackageName, attribute.name.toCamelCase() + "Enum")
                AttributeTypeResult(
                        className,
                        setOf(StyleableAttributeFormat.Enum),
                        className
                )
            }
            attribute.formats.size == 1 && attribute.flags.isNotEmpty() -> {
                // TODO: make this multi format value (i.e., flags and random integer)?
                val className = ClassName(SourcererEnvironment.generatedPackageName, attribute.name.toCamelCase() + "FlagsEnum")
                AttributeTypeResult(
                        className,
                        setOf(StyleableAttributeFormat.Integer),
                        className
                )
            }
            attribute.formats.size == 1 -> {
                val format = attribute.formats.first()
                if (format != StyleableAttributeFormat.Unspecified) {
                    AttributeTypeResult(
                            format.toClass().asTypeName(),
                            setOf(format)
                    )
                } else {
                    // try to specify format by setter parameter type
                    val setterType = considerWhatTypeReallyIsAtTheRudimentaryLevel(typesForThisSetter.setterClassName)
                    val matchedFormat = StyleableAttributeFormat.fromTypeName(setterType)
                    AttributeTypeResult(matchedFormat.toClass().asTypeName(), setOf(matchedFormat))
                }
            }
            attribute.formats.size > 1 -> {
                handleMultiFormatAttributeForSetter(attribute, setter, typesForThisSetter, codeBlock)
            }
            else -> throw IllegalStateException("Could not convert attribute to type")
        }
    }

    private fun handleMultiFormatAttributeForSetter(
            attribute: Attribute,
            setter: Setter,
            typesForThisSetter: AttributeTypesForSetter,
            codeBlock: CodeBlock
    ): AttributeTypeResult {
        // try to make a smart selection of attribute formats to setter parameter type
        val hashCode = setter.hashCode()
        val otherHashes = attribute.setterHashCodes.asSequence().filter { it != hashCode }.toSet()
        val otherSetters = codeBlock.setters.filter { otherHashes.contains(it.hashCode()) }
        val formats = mutableSetOf<StyleableAttributeFormat>()
        val parameter = setter.getParameterByAttribute(attribute)
        val setterType = typesForThisSetter.resolvedSetterType
        val enumClassName : TypeName? = when {
            attribute.enumValues.isNotEmpty() ->  ClassName(SourcererEnvironment.generatedPackageName, attribute.name.toCamelCase() + "Enum")
            attribute.flags.isNotEmpty() -> ClassName(SourcererEnvironment.generatedPackageName, attribute.name.toCamelCase() + "FlagsEnum")
            else -> null
        }
        for (format in attribute.formats) {
            if (formatAssignedToOtherSetter(otherSetters, attribute, format)) continue
            val formatTypeName = format.toClass().asTypeName()
            val parameterFormatTypeName = parameter.format.toClass().asTypeName()
            val nonConvertedFormat = getNonConvertedFormatForThisSetter(setter, attribute, format)
            val isFormatSpecified = parameter.format != StyleableAttributeFormat.Unspecified
            when {
                !isFormatSpecified && formatTypeName.toString() == setterType.toString() -> formats.add(format)
                !isFormatSpecified && allowedConversions.contains(Pair(format, setterType)) -> formats.add(format)
                nonConvertedFormat != null -> formats.add(format)
                allowedConversions.contains(Pair(format, parameterFormatTypeName)) -> formats.add(format)
            }
        }
        if (formats.isEmpty())
            throw IllegalStateException("No appropriate format found for '${attribute.name}'. Setter parameter type: '$setterType'. Possible formats: '${attribute.formats.joinToString(", ")}'")
        return AttributeTypeResult(MultiFormatGenerator.multiFormatClassName, formats, enumClassName)
    }

    private fun formatAssignedToOtherSetter(otherSetters: List<Setter>, attribute: Attribute, format: StyleableAttributeFormat): Boolean {
        for (setter in otherSetters) {
            if (getNonConvertedFormatForThisSetter(setter, attribute) == format) {
                return true
            }
        }
        return false
    }

    private fun getNonConvertedFormatForThisSetter(setter: Setter, attribute: Attribute, providedFormat: StyleableAttributeFormat? = null): StyleableAttributeFormat? {
        val hashCode = setter.hashCode()
        if (cachedFormatsReservedForSetters.containsKey(hashCode)) {
            val cachedFormat = cachedFormatsReservedForSetters[hashCode]
            return when (providedFormat) {
                null -> cachedFormat
                cachedFormat -> cachedFormat
                else -> null
            }
        }
        val parameter = setter.getParameterByAttribute(attribute)
        val typesForThisSetter = attribute.typesPerSetter[setter.hashCode()] as AttributeTypesForSetter
        val setterType = typesForThisSetter.resolvedSetterType
        var selectedFormat: StyleableAttributeFormat? = null
        val formatList = if (providedFormat != null) listOf(providedFormat) else attribute.formats
        for (format in formatList) {
            val formatTypeName = format.toClass().asTypeName()
            if (parameter.format != StyleableAttributeFormat.Unspecified) {
                when {
                    format == parameter.format -> selectedFormat = format
                    format == StyleableAttributeFormat.Enum && parameter.format == StyleableAttributeFormat.Integer -> selectedFormat = format
                    format == StyleableAttributeFormat.Color && parameter.format == StyleableAttributeFormat.Integer -> selectedFormat = format
                    format == StyleableAttributeFormat.Reference && parameter.format == StyleableAttributeFormat.Integer -> selectedFormat = format
                }
            } else if (formatTypeName.toString() == setterType.toString()) {
                selectedFormat = format
            }
        }
        cachedFormatsReservedForSetters[hashCode] = selectedFormat
        return selectedFormat
    }

    // note that we're transforming it to kotlin types
    private fun considerWhatTypeReallyIsAtTheRudimentaryLevel(classNameString: String): TypeName {
        return when (classNameString) {
            "float" -> Float::class.asTypeName()
            "java.lang.Float" -> Float::class.asTypeName()
            "boolean" -> Boolean::class.asTypeName()
            "java.lang.Boolean" -> Boolean::class.asTypeName()
            "int" -> Int::class.asTypeName()
            "java.lang.Integer" -> Int::class.asTypeName()
            "long" -> Long::class.asTypeName()
            "java.lang.Long" -> Long::class.asTypeName()
            "java.lang.String" -> String::class.asTypeName()
            "java.lang.CharSequence" -> String::class.asTypeName()
            "java.lang.String[]" -> Array<String>::class.asTypeName()
            "int[]" -> Array<Int>::class.asTypeName()
            "java.lang.Integer[]" -> Array<Int>::class.asTypeName()
            "float[]" -> Array<Float>::class.asTypeName()
            "java.lang.Float[]" -> Array<Float>::class.asTypeName()
            "boolean[]" -> Array<Boolean>::class.asTypeName()
            "java.lang.Boolean[]" -> Array<Boolean>::class.asTypeName()
            else -> {
                val splitClassName = classNameString.split(".")
                val simpleName = splitClassName.last()
                return ClassName(
                        splitClassName.subList(0, splitClassName.lastIndex).joinToString("."),
                        simpleName
                )
            }
        }
    }




    private fun assignFormatToAttributeParameter(attribute: Attribute, setter: Setter, typesForThisSetter: AttributeTypesForSetter) {
        if (!typesForThisSetter.unassociatedToParameter) {
            val parameter = setter.getParameterByAttribute(attribute)
            val format = getParameterFormat(attribute, parameter)
            parameter.format = format
        }
    }

    private fun getParameterFormat(attr: Attribute, parameter: Parameter): StyleableAttributeFormat {
        // check annotations first
        if (parameter.annotations.isNotEmpty()) {
            for (annotation in parameter.annotations) {
                when (annotation) {
                    "ColorInt" -> return StyleableAttributeFormat.Color
                    "IdRes" -> return StyleableAttributeFormat.Reference
                }
            }
        }
        val typeName = considerWhatTypeReallyIsAtTheRudimentaryLevel(parameter.describedType)
        return StyleableAttributeFormat.fromTypeName(typeName)
    }


    companion object {
        private val allowedConversions = DelegateGeneratorBase.transformingMethodTypes.keys

        fun isConversionAvailable(format: StyleableAttributeFormat, typeName: TypeName): Boolean {
            return allowedConversions.contains(Pair(format,typeName))
        }
        fun toClassName(classNameAsString: String): ClassName {
            if (classNameAsString.contains(".")) {
                val indexOfLastDot = classNameAsString.indexOfLast { c -> c == '.' }
                val packageName = classNameAsString.substring(0, indexOfLastDot)
                val simpleName = classNameAsString.substring(indexOfLastDot + 1)
                if (simpleName.contains("$")) {
                    val splitSimpleName = simpleName.split("$")
                    return ClassName(packageName, splitSimpleName[0]).nestedClass(splitSimpleName[1])
                } else {
                    return ClassName(packageName, simpleName)
                }
            } else {// primitive
                return toClass(classNameAsString).asClassName()
            }
        }

        fun toClass(className: String): KClass<*> {
            when (className) {
                "boolean" -> return Boolean::class
                "byte" -> return Byte::class
                "short" -> return Short::class
                "int" -> return Int::class
                "long" -> return Long::class
                "float" -> return Float::class
                "double" -> return Double::class
                "char" -> return Char::class
                "void" -> return Void.TYPE.kotlin
                else -> {
                    val qualifiedName = if (className.contains(".")) className else "java.lang.$className"
                    try {
                        return Class.forName(qualifiedName).kotlin
                    } catch (ex: ClassNotFoundException) {
                        throw ClassNotFoundException("Class not found: $qualifiedName")
                    }

                }
            }
        }
    }

}
