package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.TypeScriptFileNameProvider
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
        filePreProcessors = listOf({ targetTypes, rootPackageNames, _ ->
            var result = ""
            val processedImports = mutableSetOf<String>()
            for (targetType in targetTypes.values) {
                for(superType in targetType.superTypes) {
                    if (!rootPackageNames.contains(superType.className.packageName)
                            && rootPackageNames.all { !superType.className.packageName.startsWith(it) }) {
                        val fileName = TypeScriptFileNameProvider.getAttributesFileName(superType.className.packageName)
                                .replace(TypeScriptFileNameProvider.fileExtension, "")
                        if (!processedImports.contains(fileName)) {
                            processedImports.add(fileName)
                            result += "import \"./$fileName\";\n"
                        }
                    }
                }
            }
            return@listOf "$result\n"
        })
) {
        companion object {
            private const val servicesPackageName = "com.laidpack.sourcerer.services"
            private const val servicesApiPackageName = "$servicesPackageName.api"
        }
}