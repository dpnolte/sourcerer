package com.laidpack.sourcerer.generator.generators

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generator.TypePhilosopher
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.*
import com.squareup.moshi.JsonClass
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class MultiFormatGenerator {
    fun generateFile(): FileSpec {
        val file = FileSpec.builder(multiFormatClassName.packageName, multiFormatClassName.simpleName)
        file.addType(createMultiFormatType())
        return file.build()
    }

    private fun createMultiFormatType(): TypeSpec {
        val formatSet = Set::class.asClassName()
                .parameterizedBy(FormatEnumGenerator.formatEnumClassName)
        val classTypeSpec = TypeSpec.classBuilder(multiFormatClassName)
                .addAnnotation(
                        AnnotationSpec.builder(JsonClass::class.java)
                                .addMember("%L = true", "generateAdapter")
                                .build()
                )
                .addAnnotation(TypeScript::class.java)
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


        return classTypeSpec.build()
    }

    private fun TypeSpec.Builder.addPropertiesForEachFormat() : TypeSpec.Builder {
        for(value in StyleableAttributeFormat.values()) {
            val className = value.toClass().asClassName()
            this.addProperty(
                    PropertySpec.builder("mutable${value.name}", className.asNullable())
                            .initializer("null")
                            .build()
            )
            this.addProperty(
                    PropertySpec.builder("has${value.name}", Boolean::class)
                            .getter(
                                    FunSpec.getterBuilder()
                                            .addStatement("return %N != null", "mutable${value.name}")
                                            .build()
                            )
                            .build()
            )
            this.addProperty(
                    PropertySpec.builder(value.name.decapitalize(), className)
                            .getter(
                                    FunSpec.getterBuilder()
                                            .addStatement("if (!%N.contains(%T.%N)) throw IllegalStateException(\"Format '%L' is not allowed as value\")",
                                                    "allowedFormats", FormatEnumGenerator.formatEnumClassName, value.name, value.name)
                                            .addStatement("return %N ?: throw IllegalStateException(\"%N is null\")",
                                                    "mutable${value.name}", value.name)
                                            .build()
                            )
                            .build()
            )
        }
        return this
    }

    companion object {
        val multiFormatClassName = ClassName(SourcererEnvironment.generatedPackageName, "MultiFormat")
        val multiFormatQualifierClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "MultiFormatQualifier")
    }
}