package com.laidpack.sourcerer.generator.generators

import com.laidpack.typescript.annotation.TypeScript
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.index.ClassCategory
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.toCamelCase
import com.squareup.kotlinpoet.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


class AttributesGenerator(
        private val targetPackageName: String,
        private val attributesClassName: ClassName,
        private val attributesSuperClassName: ClassName?,
        private val attributes: Map<String, Attribute>,
        private val superClassAttributes: Map<ClassName, Map<String, Attribute>>
) {

    fun generateFile(): FileSpec {
        val file = FileSpec.builder(targetPackageName, attributesClassName.simpleName)
        file.addType(getClassTypeSpec())
        file.addEnumTypes()

        return file.build()
    }

    private fun getClassTypeSpec(): TypeSpec {
        val classTypeSpec = TypeSpec.classBuilder(attributesClassName)
                .addModifiers(KModifier.OPEN)
                .addAnnotation(
                        AnnotationSpec.builder(JsonClass::class.java)
                                .addMember("%L = true", "generateAdapter")
                                .build()
                )
                .addAnnotation(TypeScript::class.java)
                .addSuperinterface(attributesInterfaceName)
                .addSuperClassSpec()
                .primaryConstructor( FunSpec.constructorBuilder()
                        .addParameters(getParameterSpecsForClass(attributesClassName, attributes))
                        .addParametersForSuperClassAttributes()
                        .build()
                )
                .addProperties(getPropertySpecsForClass(attributesClassName, attributes))
                //.addAllPropertySpecs()

        return classTypeSpec.build()
    }

    private fun FileSpec.Builder.addEnumTypes(): FileSpec.Builder {
        for (attribute in attributes.values) {
            if (!attribute.isDeclaredInSuperClass
                && (attribute.enumValues.isNotEmpty() || attribute.flags.isNotEmpty())
            ) {
                this.addEnumTypesForAttribute(attribute)
            }
        }
        return this
    }

    private fun getPropertySpecsForClass(
            className: ClassName,
            attributes: Map<String, Attribute>
    ): List<PropertySpec> {
        val propertySpecs = mutableListOf<PropertySpec>()
        for (attribute in attributes.values) {
            if (attribute.isDeclaredInSuperClass) continue

            propertySpecs.add(
                    PropertySpec.builder(
                            attribute.name,
                            getPropertyType(className, attribute)
                    )
                            .addQualifierAnnotationIfNeeded(className, attribute)
                            .initializer(attribute.name)
                            .build()
            )
        }
        return propertySpecs
    }

    private fun FunSpec.Builder.addParametersForSuperClassAttributes(): FunSpec.Builder {
        for ((className, attrs) in superClassAttributes) {
            //this.addComment("Super class $className attributes")
            this.addParameters(getParameterSpecsForClass(className, attrs))
        }
        return this
    }

    private fun getParameterSpecsForClass(
            className: ClassName,
            attributes: Map<String, Attribute>
    ): List<ParameterSpec> {
        val parameterSpecs = mutableListOf<ParameterSpec>()
        for (attribute in attributes.values) {
            if (attribute.isDeclaredInSuperClass) continue

            parameterSpecs.add(
                    ParameterSpec.builder(
                                    attribute.name,
                                    getPropertyType(className, attribute)
                            )
                            .addDefaultValue(attribute)
                            .build()
            )
        }
        return parameterSpecs
    }
    private fun ParameterSpec.Builder.addDefaultValue(attribute: Attribute): ParameterSpec.Builder {
        return if (attribute.formatsUsedBySetters.size == 1) {
            this.defaultValue("null")
        } else {
            this.defaultValue(
                    "%T(setOf(${attribute.formats.joinToString(", ") { "%T.%L" }}))",
                    MultiFormatGenerator.multiFormatClassName,
                    *attribute.formats.map { listOf(FormatEnumGenerator.formatEnumClassName, it.toString()) }
                            .flatten()
                            .toTypedArray()
            )
        }
    }

    private fun FileSpec.Builder.addEnumTypesForAttribute(attribute: Attribute): FileSpec.Builder {
        val createEnumForFormats = attribute.formats.filter {
            it == StyleableAttributeFormat.Flags || it == StyleableAttributeFormat.Enum
        }
        if (createEnumForFormats.isEmpty()) IllegalStateException("Attribute '${attribute.name}' does not need an enum type (no enums or flags)")
        for (format in createEnumForFormats) {
            val enumClassName = getEnumClassName(
                    attributesClassName,
                    attribute,
                    attribute.typesForFirstSetter
            )
            val enumTypeSpecBuilder = TypeSpec.enumBuilder(enumClassName)
                    .addAnnotation(TypeScript::class.java)
                    .primaryConstructor(FunSpec.constructorBuilder()
                            .addParameter("key", String::class, KModifier.OVERRIDE)
                            .addParameter("value", Int::class, KModifier.OVERRIDE)
                            .build()
                    )
                    .addProperty(
                            PropertySpec.builder("key", String::class)
                                    .initializer("key")
                                    .build()
                    )
                    .addProperty(
                            PropertySpec.builder("value", Int::class)
                                    .initializer("value")
                                    .build()
                    )
                    .addSuperinterface(enumInterfaceName)

            val enumConstants: List<Pair<String, Int>> = if (format == StyleableAttributeFormat.Flags) {
                attribute.flags.map { Pair(it.name, it.value) }
            } else attribute.enumValues.map { Pair(it.name, it.value) }
            enumConstants.forEach {
                val name = it.first
                val value = it.second
                enumTypeSpecBuilder.addEnumConstant(
                        name.toCamelCase(),
                        TypeSpec.anonymousClassBuilder()
                                .addSuperclassConstructorParameter("%S", name)
                                .addSuperclassConstructorParameter("%L", value)
                                .addAnnotation(
                                        AnnotationSpec.builder(Json::class)
                                                .addMember("name = %S", name)
                                                .build()
                                )
                                .build()
                )
            }
            this.addType(enumTypeSpecBuilder.build())
        }
        return this
    }

    private fun TypeSpec.Builder.addSuperClassSpec(): TypeSpec.Builder {
        if (attributesSuperClassName != null) {
            this.superclass(attributesSuperClassName)
            for (attrs in superClassAttributes.values) {
                for (attr in attrs.values) {
                    if (!attr.isDeclaredInSuperClass) {
                        this.addSuperclassConstructorParameter("%N = %N", attr.name, attr.name)
                    }
                }
            }
        }
        return this
    }

    private fun getEnumClassName(
            attributeClassName: ClassName,
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter
    ): ClassName {
        val key = attributeClassName.canonicalName + attribute.name
        if (enumClassNameRegistry.containsKey(key)) {
            return enumClassNameRegistry[key] as ClassName
        }
        var className = ClassName(attributeClassName.packageName, (typesForThisSetter.enumClassName as ClassName).simpleName)
        while (usedEnumClassNames.contains(className)) {
            className = ClassName(attributeClassName.packageName, className.simpleName + "_")
        }
        usedEnumClassNames.add(className)
        enumClassNameRegistry[key] = className
        return className
    }

    private fun getPropertyType(className: ClassName, attribute: Attribute): TypeName {
        val usedFormats = attribute.formatsUsedBySetters
        val isMultiFormatted = usedFormats.size > 1
        val firstFormat = usedFormats.first()
        return when {
            isMultiFormatted
                -> MultiFormatGenerator.multiFormatClassName
            attribute.typesForFirstSetter.hasFlagsAsAttributeType
                ->  flagsAccumulatorClassName.asNullable()
            attribute.typesForFirstSetter.hasEnumAsAttributeType
                -> getEnumClassName(
                        className,
                        attribute,
                        attribute.typesForFirstSetter
                ).asNullable()
            else
                -> firstFormat.toClass().asTypeName().asNullable()
        }
    }

    private fun PropertySpec.Builder.addQualifierAnnotationIfNeeded(className: ClassName, attribute: Attribute): PropertySpec.Builder {
        // check if we have different formats, then use multiqualifier..
        // collect all formats that are actually used
        val formats = attribute.formatsUsedBySetters
        val hasMultipleFormats = formats.size > 1
        if (hasMultipleFormats) {
            val args = mutableListOf<Any>()
            val arrayOfFormats = formats.joinToString(", ") {
                args.add(FormatEnumGenerator.formatEnumClassName)
                args.add(it.name)
                "%T.%N"
            }
            val annotationSpec = AnnotationSpec.builder(MultiFormatGenerator.multiFormatQualifierClassName)
                    .addMember("formats = [$arrayOfFormats]", *args.toTypedArray())
                    .useSiteTarget(AnnotationSpec.UseSiteTarget.FIELD)

            if (formats.contains(StyleableAttributeFormat.Enum)) {
                annotationSpec.addMember("enumType = %T::class", getEnumClassName(
                        className,
                        attribute,
                        attribute.typesForFirstSetter
                ))
            }
            if (formats.contains(StyleableAttributeFormat.Flags)) {
                annotationSpec.addMember("flagsType = %T::class", getEnumClassName(
                        className,
                        attribute,
                        attribute.typesForFirstSetter
                ))
            }
            this.addAnnotation(annotationSpec.build())
        } else {
            // if we only have one formats, check if qualifier is needed
            val format = formats.first()
            if (!format.requiresQualifier) return this
            val qualifierClassName = format.toQualifierAnnotationClassName()
            val annotationSpec = AnnotationSpec.builder(qualifierClassName)
                    .useSiteTarget(AnnotationSpec.UseSiteTarget.FIELD)
            if (format == StyleableAttributeFormat.Flags) {
                annotationSpec.addMember("flagsType = %T::class", getEnumClassName(
                        className,
                        attribute,
                        attribute.typesForFirstSetter
                ))
            }
            this.addAnnotation(annotationSpec.build())
        }
        return this
    }

    companion object {
        fun getAttributesClassName(targetPackageName: String, originalClassName: ClassName, classCategory: ClassCategory): ClassName {
            return if (classCategory == ClassCategory.LayoutParams) {
                val splitClassNameString = originalClassName.canonicalName.split(".")
                val simpleName = splitClassNameString[splitClassNameString.lastIndex - 1] + splitClassNameString[splitClassNameString.lastIndex]
                return ClassName(targetPackageName, simpleName + "Attributes")
            } else {
                ClassName(targetPackageName, originalClassName.simpleName + "Attributes")
            }
        }

        private val enumClassNameRegistry = mutableMapOf<String/* target class name + attribute name */, ClassName /* class name with new package and check for dupes*/>()
        private val usedEnumClassNames = mutableSetOf<ClassName /* class simple names */>()
        private val attributesInterfaceName = ClassName(SourcererEnvironment.servicesApiPackageName, "IAttributes")
        private val flagsAccumulatorClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "FlagsAccumulator")
        val enumInterfaceName = ClassName(SourcererEnvironment.servicesApiPackageName, "AttributeEnum")
        val flagsAccumalatorClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "FlagsAccumulator")
        private val enumForFlagsAnnotationClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "EnumForFlags")

    }

}