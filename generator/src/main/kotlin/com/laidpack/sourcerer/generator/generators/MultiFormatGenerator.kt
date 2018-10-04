package com.laidpack.sourcerer.generator.generators

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.*
import com.squareup.moshi.JsonClass
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.util.*

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

            val className = format.toClass().asClassName()
            this.addProperty(
                    PropertySpec.builder("mutable${format.name}", className.asNullable())
                            .mutable(true)
                            .initializer("null")
                            .build()
            )
            this.addProperty(
                    PropertySpec.builder("has${format.name}", Boolean::class)
                            .getter(
                                    FunSpec.getterBuilder()
                                            .addStatement("return %N != null", "mutable${format.name}")
                                            .build()
                            )
                            .build()
            )
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
                .addParameter("format", FormatEnumGenerator.formatEnumClassName)
                .addParameter("value", Any::class.asTypeName())
                .beginControlFlow("when (format)")

        for (format in formats) {
            funSpec.addStatement("%T.%N -> %N = %N as %T",
                    FormatEnumGenerator.formatEnumClassName,
                    format.name,
                    "mutable${format.name}",
                    "value",
                    format.toClass().asClassName()
            )
        }
        funSpec.endControlFlow()

        return this.addFunction(funSpec.build())
    }

    private fun TypeSpec.Builder.addCompanion() : TypeSpec.Builder {
        val moshiParameterName = "moshi"
        val funSpec = FunSpec.builder("getAdaptersMap")
                .addParameter(moshiParameterName, Moshi::class.asTypeName())
                .returns(
                        SortedMap::class.asTypeName().parameterizedBy(
                                FormatEnumGenerator.formatEnumClassName,
                                JsonAdapter::class.asTypeName().parameterizedBy(WildcardTypeName.STAR)
                        )
                )
        val sortedFormats = formats.sortedWith (
            compareBy(
                    { !it.requiresQualifier },
                    { it != StyleableAttributeFormat.Reference },
                    { it != StyleableAttributeFormat.Color },
                    { it != StyleableAttributeFormat.Dimension }
            )
        )
        var code = "return sortedMapOf(\n"
        val args = mutableListOf<Any>()
        sortedFormats.forEachIndexed { index, format ->
            val typeName = format.toClass().asTypeName()
            val paramFormat = if (format.requiresQualifier) {
                "%T::class.java, %T::class.java"
            } else "%T::class.java"

            val comma = if (index < formats.lastIndex) "," else ""
            code +=  "\t\t\t%T.%N to %N.adapter<%T>($paramFormat) as %T$comma\n"
            args.add(FormatEnumGenerator.formatEnumClassName)
            args.add(format.name)
            args.add(moshiParameterName)
            args.add(typeName)
            if (format.requiresQualifier) {
                args.add(typeName)
                args.add(format.toQualifierAnnotationClassName())
            } else args.add(typeName)
            args.add(JsonAdapter::class.asTypeName().parameterizedBy(WildcardTypeName.STAR))
        }
        code += "\t\t)"
        funSpec.addStatement(code, *args.toTypedArray())

        this.addType(TypeSpec.companionObjectBuilder()
                .addFunction (funSpec.build())
                .build()
        )

        return this
    }



    companion object {
        val multiFormatClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "MultiFormat")
        val multiFormatQualifierClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "MultiFormatQualifier")
    }
}