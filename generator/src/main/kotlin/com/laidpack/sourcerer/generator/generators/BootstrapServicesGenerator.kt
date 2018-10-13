package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.*


class BootstrapServicesGenerator(
       private val targetClassName: ClassName,
       private val moduleBootstrapperClassNames: List<ClassName>
) {
    private val serializerParam = ParameterSpec.builder("serializer", serializerComponentClassName)
            .build()
    private val inflaterParam = ParameterSpec.builder("inflater", inflaterComponentClassName)
            .build()

    fun generateFile(): FileSpec {
        val fileSpec = FileSpec.builder(targetClassName.packageName, targetClassName.simpleName)
                .addComment("do not edit, auto-generated")
                .addType(getClassSpec())

        return fileSpec.build()
    }

    fun getClassSpec(): TypeSpec {
        val classSpec = TypeSpec.classBuilder(targetClassName)
                .addModifiers(KModifier.OPEN)
                .addFunction(getBootstrapFun())
                .addFunction(getBootstrapGeneratedModulesFun())
                .addFunction(getBootstrapModuleFun())
        return classSpec.build()
    }

    fun getBootstrapFun(): FunSpec {
        val funSpec = FunSpec.builder("bootstrap")
                .addModifiers(KModifier.OPEN)
                .addParameter(serializerParam)
                .addParameter(inflaterParam)
                .addStatement("bootstrapGeneratedModules(%N, %N)", serializerParam, inflaterParam)

        return funSpec.build()
    }

    fun getBootstrapGeneratedModulesFun(): FunSpec {
        val funSpec = FunSpec.builder("bootstrapGeneratedModules")
                .addModifiers(KModifier.PROTECTED)
                .addParameter(serializerParam)
                .addParameter(inflaterParam)

        for (bootstrapperClassName in moduleBootstrapperClassNames) {
            funSpec.addStatement(
                    "bootstrapModule(%N, %N, %S)",
                    serializerParam,
                    inflaterParam,
                    bootstrapperClassName.canonicalName
            )
        }

        return funSpec.build()
    }

    fun getBootstrapModuleFun(): FunSpec {
        val funSpec = FunSpec.builder("bootstrapModule")
                .addModifiers(KModifier.PROTECTED)
                .addParameter(serializerParam)
                .addParameter(inflaterParam)
                .addParameter("classNameAsString", String::class)
                .beginControlFlow("try")
                .addStatement(
                        "val bootstrapper = %T.forName(%N).newInstance() as %T",
                        clazzClassName,
                        "classNameAsString",
                        bootstrapperInterfaceName
                )
                .addStatement("bootstrapper.bootstrap(%N, %N)", serializerParam, inflaterParam)
                .endControlFlow()
                .beginControlFlow("catch (e: %T)", ClassNotFoundException::class.asClassName())
                .addComment("module not included as dependency")
                .endControlFlow()


        return funSpec.build()
    }

    companion object {
        private val clazzClassName = ClassName("java.lang", "Class")
        private val serializerComponentClassName = ClassName(SourcererEnvironment.servicePackageName,"SerializerComponent")
        private val inflaterComponentClassName = ClassName(SourcererEnvironment.servicePackageName, "InflaterComponent")
        private val bootstrapperInterfaceName = ClassName(SourcererEnvironment.servicesApiPackageName, "Bootstrapper")
    }
}