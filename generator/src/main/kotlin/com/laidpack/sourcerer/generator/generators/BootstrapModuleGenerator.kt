package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi

data class AdapterRegistrationWrapper(
        val subjectClassName: ClassName,
        val adapterClassName: ClassName,
        val elementType: String
)
data class AssociatedViewGroupAndLayoutParamsWrapper(
        val viewGroupElementType: String,
        val layoutParamsElementType: String
)
data class FactoryWrapper(
        val factoryClassName: ClassName,
        val viewClassName: TypeName,
        val attributesClassName: ClassName,
        val isFinal: Boolean
)


class BootstrapModuleGenerator(
        val targetClassName: ClassName,
        val adapters: List<AdapterRegistrationWrapper>,
        val viewGroupToLayoutParams: List<AssociatedViewGroupAndLayoutParamsWrapper>,
        val factories: List<FactoryWrapper>

) {
    private val serializerParam = ParameterSpec.builder("serializer", serializerComponentClassName)
            .build()
    private val inflaterParam = ParameterSpec.builder("inflater", inflaterComponentClassName)
            .build()

    fun generateFile(): FileSpec {
        val fileSpec = FileSpec.builder(targetClassName.packageName, targetClassName.simpleName)
        fileSpec.addType(getClassSpec())
        return fileSpec.build()
    }

    private fun getClassSpec(): TypeSpec {
        val classSpec = TypeSpec.classBuilder(targetClassName)
                .addSuperinterface(bootstrapperInterfaceName)
                .addFunction(getBootstrapFun())
        return classSpec.build()
    }

    private fun getBootstrapFun(): FunSpec {
        val funSpec = FunSpec.builder("bootstrap")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(serializerParam)
                .addParameter(inflaterParam)
        for (adapter in adapters) {
            funSpec.addStatement(
                    "%N.registerAutoGeneratedAdapter(%T::class, {moshi -> %T(moshi as %T)}, %S)",
                    serializerParam,
                    adapter.subjectClassName,
                    adapter.adapterClassName,
                    moshiClassName,
                    adapter.elementType
            )
        }
        for (viewGroupToLayoutParam in viewGroupToLayoutParams) {
            funSpec.addStatement(
                    "%N.associateViewGroupWithLayoutParams(%S, %S)",
                    serializerParam,
                    viewGroupToLayoutParam.viewGroupElementType,
                    viewGroupToLayoutParam.layoutParamsElementType
            )
        }
        for (factory in factories) {
            val factoryArgs = mutableListOf<Any>()
            val factoryExpression = if (factory.isFinal) {
                factoryArgs.add(inflaterParam)
                factoryArgs.add(factory.factoryClassName)
                factoryArgs.add(factory.attributesClassName)
                "%N.addFactory(%T<%T>())"
            } else {
                factoryArgs.add(inflaterParam)
                factoryArgs.add(factory.factoryClassName)
                factoryArgs.add(factory.viewClassName)
                factoryArgs.add(factory.attributesClassName)
                "%N.addFactory(%T<%T, %T>())"
            }
            funSpec.addStatement(factoryExpression, *factoryArgs.toTypedArray())
        }
        return funSpec.build()
    }

    companion object {
        private val serializerComponentClassName = ClassName(SourcererEnvironment.servicePackageName,"SerializerComponent")
        private val inflaterComponentClassName = ClassName(SourcererEnvironment.servicePackageName, "InflaterComponent")
        private val moshiClassName = Moshi::class.asClassName()
        private val bootstrapperInterfaceName = ClassName(SourcererEnvironment.servicesApiPackageName, "Bootstrapper")
    }
}