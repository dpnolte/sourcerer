package com.laidpack.sourcerer.generator.generators

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlin.reflect.KClass

class MultiFormatGenerator {
    private val formats = StyleableAttributeFormat.values()

    fun generateFile(): FileSpec {
        val file = FileSpec.builder(multiFormatClassName.packageName, multiFormatClassName.simpleName)
        file.addComment("do not edit, auto-generated")
        file.addType(createMultiFormatType())
        return file.build()
    }

    private fun createMultiFormatType(): TypeSpec {
        val formatSet = Set::class.asClassName()
                .parameterizedBy(FormatEnumGenerator.formatEnumClassName)
        val classTypeSpec = TypeSpec.classBuilder(multiFormatClassName)
                .addAnnotation(TypeScript::class.java)
                .addModifiers(KModifier.OPEN)
                .primaryConstructor(
                        FunSpec.constructorBuilder()
                                .addParameter(
                                        ParameterSpec.builder("allowedFormats", formatSet)
                                                .addAnnotation(Transient::class)
                                                .defaultValue("setOf()")
                                                .build()
                                )
                                .build()
                )
                .addProperty(
                        PropertySpec.builder("allowedFormats", formatSet)
                                .addModifiers(KModifier.PRIVATE)
                                .initializer("allowedFormats")
                                .build()
                )
                .addPropertiesForEachFormat()
                .addSetValueFunc()
                .addCompanion()


        return classTypeSpec.build()
    }

    private fun TypeSpec.Builder.addPropertiesForEachFormat() : TypeSpec.Builder {
        val hasAnyValueArgs = mutableListOf<Any>()
        var hasAnyValueCode = "return "
        formats.forEachIndexed { index, format ->
            hasAnyValueArgs.add("mutable${format.name}")
            hasAnyValueCode += "%N != null"
            if (index < formats.lastIndex) {
                hasAnyValueCode += "\n\t\t\t|| "
            }

            val className = when (format) {
                StyleableAttributeFormat.Enum -> AttributesGenerator.enumInterfaceName
                StyleableAttributeFormat.Flags -> AttributesGenerator.flagsAccumalatorClassName
                else -> format.toClass().asClassName()
            }
            val mutableProperty =  PropertySpec.builder("mutable${format.name}", className.asNullable())
                    .mutable(true)
                    .initializer("null")
            if (format == StyleableAttributeFormat.Enum || format == StyleableAttributeFormat.Flags) {
                mutableProperty.addAnnotation(Transient::class)
            }
            this.addProperty(mutableProperty.build())
            this.addProperty(
                    PropertySpec.builder("has${format.name}", Boolean::class)
                            .getter(
                                    FunSpec.getterBuilder()
                                            .addStatement("return %N != null", "mutable${format.name}")
                                            .build()
                            )
                            .build()
            )
            if (format != StyleableAttributeFormat.Enum && format != StyleableAttributeFormat.Flags) {
                this.addProperty(
                        PropertySpec.builder(format.name.decapitalize(), className)
                                .getter(
                                        FunSpec.getterBuilder()
                                                .addStatement("if (!%N.contains(%T.%N)) throw IllegalStateException(\"Format '%L' is not allowed as value\")",
                                                        "allowedFormats", FormatEnumGenerator.formatEnumClassName, format.name, format.name)
                                                .addStatement("return %N ?: throw IllegalStateException(\"%N is null\")",
                                                        "mutable${format.name}", format.name)
                                                .build()
                                )
                                .build()
                )
            } else {
                this.addProperty(
                        PropertySpec.builder(format.name.decapitalize(), Int::class.asTypeName())
                                .getter(
                                        FunSpec.getterBuilder()
                                                .addStatement("if (!%N.contains(%T.%N)) throw IllegalStateException(\"Format '%L' is not allowed as value\")",
                                                        "allowedFormats", FormatEnumGenerator.formatEnumClassName, format.name, format.name)
                                                .addStatement("return %N?.value ?: throw IllegalStateException(\"%N is null\")",
                                                        "mutable${format.name}", format.name)
                                                .build()
                                )
                                .build()
                )
            }
        }
        this.addProperty(
                PropertySpec.builder("hasAnyValue", Boolean::class)
                        .getter(
                                FunSpec.getterBuilder()
                                        .addStatement(
                                                hasAnyValueCode,
                                                *hasAnyValueArgs.toTypedArray()
                                        )
                                        .build()
                        )
                        .build()
        )
        return this
    }

    private fun TypeSpec.Builder.addSetValueFunc() : TypeSpec.Builder {
        val funSpec = FunSpec.builder("setValue")
                .addParameter("formats", FormatEnumGenerator.formatEnumClassName)
                .addParameter("value", Any::class.asTypeName())
                .beginControlFlow("when (formats)")

        for (format in formats) {
            val className = when (format) {
                StyleableAttributeFormat.Enum -> AttributesGenerator.enumInterfaceName
                StyleableAttributeFormat.Flags -> AttributesGenerator.flagsAccumalatorClassName
                else -> format.toClass().asClassName()
            }
            funSpec.addStatement("%T.%N -> %N = %N as %T",
                    FormatEnumGenerator.formatEnumClassName,
                    format.name,
                    "mutable${format.name}",
                    "value",
                    className
            )
        }
        funSpec.endControlFlow()

        return this.addFunction(funSpec.build())
    }

    private fun TypeSpec.Builder.addCompanion() : TypeSpec.Builder {
        val moshiParameterName = "moshi"
        val returnType =  Map::class.asTypeName().parameterizedBy(
                FormatEnumGenerator.formatEnumClassName,
                LambdaTypeName.get(
                        null,
                        listOf(),
                        JsonAdapter::class.asTypeName().parameterizedBy(WildcardTypeName.STAR)
                )
        )
        val propertySpec = PropertySpec.builder("delegatesMapProvider", LambdaTypeName.get(
                null,
                listOf(ParameterSpec.builder("moshi", Moshi::class.asTypeName()).build()),
                returnType
        ))
                .addModifiers(KModifier.PRIVATE)
        val sortedFormats = formats.sortedWith (
            compareBy(
                    { !it.requiresQualifier },
                    { it != StyleableAttributeFormat.Reference },
                    { it != StyleableAttributeFormat.Color },
                    { it != StyleableAttributeFormat.Dimension }
            )
        )
        var code = "{moshi -> sortedMapOf(\n"
        val args = mutableListOf<Any>()
        sortedFormats.forEachIndexed { index, format ->
            // skip Enum and Flags formats as these need specific types
            if (format != StyleableAttributeFormat.Enum && format != StyleableAttributeFormat.Enum) {
                val typeName = format.toClass().asTypeName()
                val paramFormat = if (format.requiresQualifier) {
                    "%T::class.javaObjectType, %T::class.java"
                } else "%T::class.javaObjectType"

                val comma = if (index < formats.lastIndex) "," else ""
                code += "\t\t\t%T.%N to {%N.adapter<%T>($paramFormat) as %T}$comma\n"
                args.add(FormatEnumGenerator.formatEnumClassName)
                args.add(format.name)
                args.add(moshiParameterName)
                args.add(typeName.asNullable())
                if (format.requiresQualifier) {
                    args.add(typeName)
                    args.add(format.toQualifierAnnotationClassName())
                } else args.add(typeName)
                args.add(JsonAdapter::class.asTypeName().parameterizedBy(WildcardTypeName.STAR))
            }
        }
        code += "\t\t)}"
        propertySpec.initializer(code, *args.toTypedArray())
        val funSpec = FunSpec.builder("getDelegatesPerFormat")
                .addParameter(moshiParameterName, Moshi::class.asTypeName())
                .addParameter(ParameterSpec.builder(
                            "enumType",
                            KClass::class.asClassName()
                                    .parameterizedBy(WildcardTypeName.subtypeOf(AttributesGenerator.enumInterfaceName))
                        )
                        .defaultValue("%T::class", missingTypeName)
                        .build()
                )
                .addParameter(ParameterSpec.builder(
                            "flagsType",
                            KClass::class.asClassName()
                                    .parameterizedBy(WildcardTypeName.subtypeOf(AttributesGenerator.enumInterfaceName))
                        )
                        .defaultValue("%T::class", missingTypeName)
                        .build()
                )
                .returns(returnType)
                .beginControlFlow("if (!initializedDelegatesMap)")
                .addStatement("delegatesMap = delegatesMapProvider(moshi)")
                .addStatement("initializedDelegatesMap = true")
                .endControlFlow()
                .beginControlFlow("if (enumType != %T::class || flagsType != %T::class)", missingTypeName, missingTypeName)
                .addStatement("val map = delegatesMap.toMutableMap()")
                .beginControlFlow("if (enumType != %T::class)", missingTypeName)
                .addStatement("map[%T.%N] = {moshi.adapter(enumType.javaObjectType) as JsonAdapter<*>}", FormatEnumGenerator.formatEnumClassName, "Enum")
                .endControlFlow()
                .beginControlFlow("if (flagsType != %T::class)", missingTypeName)
                .addStatement("map[%T.%N] = {%T(flagsType)}", FormatEnumGenerator.formatEnumClassName, "Flags", flagsAdapterClassName)
                .endControlFlow()
                .addStatement("return %N", "map")
                .endControlFlow()
                .addStatement("return delegatesMap")
        this.addType(TypeSpec.companionObjectBuilder()
                .addProperty(propertySpec.build())
                .addProperty(PropertySpec.varBuilder(
                            "initializedDelegatesMap",
                            Boolean::class.asTypeName()
                        )
                        .addModifiers(KModifier.PRIVATE)
                        .initializer("false")
                        .build()
                )
                .addProperty(PropertySpec.varBuilder(
                            "delegatesMap",
                            returnType
                        )
                        .addModifiers(KModifier.PRIVATE, KModifier.LATEINIT)
                        .build()
                )
                .addFunction (funSpec.build())
                .build()
        )

        return this
    }



    companion object {
        val missingTypeName = ClassName(SourcererEnvironment.servicesApiPackageName, "MissingType")
        val multiFormatClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "MultiFormat")
        val flagsAdapterClassName = ClassName(SourcererEnvironment.servicesAdaptersPackageName, "FlagsAdapter")
        val multiFormatQualifierClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "MultiFormatQualifier")
    }
}