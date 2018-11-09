package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.TypeScriptNameProvider
import com.laidpack.typescript.codegen.BaseTypeScriptProcessor
import com.laidpack.typescript.codegen.Nullability
import com.laidpack.typescript.codegen.TypeTransformer
import javax.annotation.processing.Processor

@Suppress("unused")
@AutoService(Processor::class)
class AttributesTypeScriptProcessor : BaseTypeScriptProcessor(
        customTransformers = listOf(
                TypeTransformer({ t -> t.annotationNames.contains("$servicesApiPackageName.MultiFormatQualifier")}, "any", Nullability.Null),
                TypeTransformer({ t -> t.annotationNames.contains("$servicesApiPackageName.ColorQualifier")}, "string", Nullability.NoTransform),
                TypeTransformer({ t -> t.annotationNames.contains("$servicesApiPackageName.DimensionQualifier")}, "string", Nullability.NoTransform),
                TypeTransformer({ t -> t.annotationNames.contains("$servicesApiPackageName.ReferenceQualifier")}, "string", Nullability.NoTransform),
                TypeTransformer({ t -> t.annotationNames.contains("$servicesApiPackageName.FlagsQualifier")}, "string", Nullability.Null)
        ),
        constrainToCurrentModulePackage = true,
        /*filePreProcessors = listOf({ targetTypes, rootPackageNames, _ ->
            var result = ""
            val processedImports = mutableSetOf<String>()
            for (targetType in targetTypes.values) {
                for(superType in targetType.superTypes) {
                    if (!rootPackageNames.contains(superType.className.packageName)
                            && rootPackageNames.all { !superType.className.packageName.startsWith(it) }) {
                        val dependencyModuleName = TypeScriptNameProvider.getModuleName(superType.className.packageName)
                        if (!processedImports.contains(dependencyModuleName)) {
                            val typeFileName = TypeScriptNameProvider.getAttributesFileName(superType.className.packageName)
                                    .replace(TypeScriptNameProvider.fileTypeDefinitionsExtension, "")
                            processedImports.add(dependencyModuleName)
                            result += "import { $dependencyModuleName } from \"./$typeFileName\";\n"
                        }
                    }
                }
            }
            return@listOf "$result\n"
        }),*/
        superTypeTransformer = {className, currentModuleName ->
            val superTypeModuleName = TypeScriptNameProvider.getModuleName(className.packageName)
            if (superTypeModuleName != currentModuleName) {
                "$superTypeModuleName.${className.simpleName}"
            } else className.simpleName
        }
) {
        companion object {
            private const val servicesPackageName = "com.laidpack.sourcerer.services"
            private const val servicesApiPackageName = "$servicesPackageName.api"
        }
}