package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.*


class FormatEnumGenerator() {
    fun generateFile(): FileSpec {
        val file = FileSpec.builder(formatEnumClassName.packageName, formatEnumClassName.simpleName)
        file.addComment("do not edit, auto-generated")
        file.addType(createEnumType())
        file.addTypeAlias(TypeAliasSpec.builder(stringAliasClassName.simpleName, String::class).build())
        return file.build()
    }

    private fun createEnumType(): TypeSpec {
        val enumTypeSpecBuilder = TypeSpec.enumBuilder(formatEnumClassName)
                .primaryConstructor(FunSpec.constructorBuilder()
                        .addParameter("value", stringAliasClassName)
                        .build()
                )
                .addProperty(
                        PropertySpec.builder("value", stringAliasClassName)
                                .initializer("value")
                                .build()
                )

        val values = StyleableAttributeFormat.values()
        for (value in values) {
            enumTypeSpecBuilder.addEnumConstant(
                    value.name,
                    TypeSpec.anonymousClassBuilder()
                            .addSuperclassConstructorParameter("%S", value.value)
                            .build()
            )
        }

        return enumTypeSpecBuilder.build()
    }

    companion object {
        private val stringAliasClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "StringAlias")
        val formatEnumClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "Format")
    }
}