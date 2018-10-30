package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.flow.attributes.FlowInterpreter
import com.laidpack.sourcerer.generator.generators.ClassGeneratorManager
import com.laidpack.sourcerer.generator.javadoc.JavaDocInterpreter
import com.laidpack.sourcerer.generator.lint.ApiDetector
import com.laidpack.sourcerer.generator.lint.ApiRequirementsChecker
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeManager
import com.laidpack.sourcerer.generator.resources.Widget
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.laidpack.sourcerer.generator.target.*
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import kotlin.system.measureTimeMillis

class Sourcerer(
        private val env: SourcererEnvironment,
        private val widgetRegistry: WidgetRegistry = WidgetRegistry.create(env),
        private val apiRequirementsChecker: ApiRequirementsChecker = ApiRequirementsChecker(env),
        private val attrsXmlManager: StyleableAttributeManager = StyleableAttributeManager(env)
) {
    private val setters = mutableMapOf<Int, Setter>()
    private val typedArrayInfo = DeclaredTypeRegistry.getTypedArrayInfo(env.sdkStructure)
    private val interpreterFactories = listOf(
            FlowInterpreter,
            JavaDocInterpreter
    )

    fun generateFactoriesForAllWidgets() {
        for(indexedClass in DeclaredTypeRegistry.getPotentialWidgetClasses()) {
            generateFactoriesForClass(indexedClass)
        }
    }

    fun generateFactoriesForClass(xdDeclaredType: XdDeclaredType) {
        println("=================================")
        println("Processing ${xdDeclaredType.targetClassName.canonicalName}")
        val savedResult = getSavedResult(xdDeclaredType)
        val result = if (savedResult == null) {
            println("\t--retrieving class info")
            ensureSuperClassesHaveResolvedAttributes(xdDeclaredType)
            val classInfo = DeclaredTypeRegistry.getClassInfo(xdDeclaredType)
            if (classInfo != null) {
                resolveAttributesInClass(classInfo)
            } else {
                println("\t--invalid class")
                null
            }
        } else {
            println("\t--retrieved saved result")
            savedResult
        }
        if (result != null) {
            val generator = ClassGeneratorManager(
                    env.stubModuleDir,
                    SourcererEnvironment.generatedPackageName,
                    SourcererEnvironment.generatedPackageName,
                    result,
                    getSuperClassResults(result),
                    env.minSdkVersion,
                    setOf()
            )
            generator.generateAttributeFile()
            generator.generateFactoryFile()
            println("----")
            println("Generated files for ${result.targetClassName.simpleName} @ ${generator.targetPathAsString}:")
            println("\t+ ${generator.attributesFileName}")
            println("\t+ ${generator.factoryFileName}")
        }
        println("=================================")
    }

    private fun resolveAttributesInClass(classInfo: ClassInfo): SourcererResult {
        val savedResult = getSavedResult(classInfo)
        if (savedResult != null) {
            println("\t--restored saved result")
            return savedResult
        }

        val xdDeclaredType = classInfo.xdDeclaredType
        /** get attributes from attrs.xml **/
        val attributes = if (attrsXmlManager.hasAttributesDefined(xdDeclaredType.targetClassName, xdDeclaredType.widget as Widget)) {
            attrsXmlManager.getAttributesFromXml(classInfo).toMutableMap()
        } else mutableMapOf()
        val attributeManager = AttributeManager(
                classInfo,
                attributes,
                attrsXmlManager.getStyleableNameFromClassName(xdDeclaredType.targetClassName),
                attrsXmlManager,
                env.androidResourceManager
        )

        /** interpret interesting methods/constructors and derive setters + attributes **/
        var codeBlocks : List<CodeBlock>
        val resolvedAttributes : Map<String, Attribute>
        val resolvedSetters: Map<Int, Setter>
        println("\t--analyzing source code")
        println("\t\t------------")
        val attributesDefinedInXml = attributes.isNotEmpty()
        if (!attributesDefinedInXml) {
            println("\t\tNo attributes specified. Try to derive attributes from source code")
        }
        interpreterFactories.forEach { factory ->
            val elapsedTime = measureTimeMillis {
                val interpreter = factory.create(
                        classInfo,
                        typedArrayInfo,
                        attributeManager,
                        attributesDefinedInXml
                )
                val interpretationResult = interpreter.interpret(setters)
                mergeInterpretations(attributes, interpretationResult)
                println("\t\t------------")
                println("\t\t${xdDeclaredType.targetClassName.simpleName}'s attributes identified with ${interpretationResult.name} interpretation: ${interpretationResult.identified}/${attributes.size}")
                println("\t\t-- of which are newly identified: ${interpretationResult.identifiedNew}/${attributes.size}")
                println("\t\t${xdDeclaredType.targetClassName.simpleName}'s attributes resolved with ${interpretationResult.name} interpretation: ${interpretationResult.resolved}/${attributes.size}")
            }
            println("\t\t\telapsed time: $elapsedTime ms")
        }
        println("\t\t------------")
        /** resolve type and group attributes to code blocks **/
        var startTime = System.currentTimeMillis()
        val codeBlockCollector = CodeBlockCollector(classInfo, attributeManager)
        val potentialResolvedAttributes = attributes.filter { it.value.resolvedStatus == ResolvedStatus.SETTER_DEFINED }
        codeBlocks = codeBlockCollector.reflectOnCodeBlockSociety(potentialResolvedAttributes, setters)
        val typePhilosopher = TypePhilosopher(attributeManager, classInfo)
        codeBlocks = typePhilosopher.contemplateOnTheMeaningOfTypes(codeBlocks)
        resolvedAttributes = codeBlocks.map { it.attributes.values }.flatten().associateBy { it.name }
        resolvedSetters = codeBlocks.map { it.setters.values }.flatten().associateBy { it.hashCode() }
        var elapsedTime = System.currentTimeMillis() - startTime
        println("\t\t${xdDeclaredType.targetClassName.simpleName}'s attributes resolved total: ${resolvedAttributes.size}/${attributes.size}")
        println("\t\t\tresolved code blocks and types for ${xdDeclaredType.targetClassName.simpleName}  - $elapsedTime ms")

        var fallbackClassName: ClassName? = null
        var classMiniApiLevel = ApiDetector.UNKNOWN_OR_VERSION_1
        if (apiRequirementsChecker.canCheckRequirements(classInfo)) {
            startTime = System.currentTimeMillis()
            println("\t--checking minimum api level requirements")
            val apiRequirements = apiRequirementsChecker.checkMinApiRequirements(classInfo, codeBlocks)
            apiRequirements.assignMinApiLevelRequirementsToCodeBlocks(codeBlocks)
            fallbackClassName = apiRequirements.fallbackClassName
            classMiniApiLevel = apiRequirements.classMinApiLevel
            println("\t\t------------")
            println("\t\t${classInfo.xdDeclaredType.targetClassName.simpleName} min api level: ${if (apiRequirements.classMinApiLevel == -1) "unknown or version 1" else apiRequirements.classMinApiLevel.toString()}")
            println("\t\t\tFallback class if any?: ${apiRequirements.fallbackClassName ?: "None"}")
            println("\t\t\t${apiRequirements.codeBlockMinApiLevelList.count { it > env.minSdkVersion }}/${apiRequirements.codeBlockMinApiLevelList.size} code blocks have min api level requirements (min sdk: ${env.minSdkVersion})")
            elapsedTime = System.currentTimeMillis() - startTime
            println("\t\t\telapsed time - $elapsedTime ms")
            println("\t\t------------")
        }

        val packageName = widgetRegistry.getPackageName(classInfo)
        val superClassName = Store.transactional {
            xdDeclaredType.superClasses.toList()
                    .sortedByDescending { xdSuperClass -> xdSuperClass.superClasses.size() }
                    .find { xdSuperClass ->
                        xdSuperClass.sourcererResult != null
                    }?.targetClassName
        }
        val result = Store.transactional {
            SourcererResult(
                    xdDeclaredType.targetClassName,
                    superClassName,
                    fallbackClassName,
                    getDefaultLayoutParamClass(classInfo),
                    classInfo.classDeclaration.isAbstract,
                    classInfo.classDeclaration.isFinal,
                    xdDeclaredType.constructorExpression?.toEnum(false) as ConstructorExpression,
                    classInfo.classDeclaration.typeParameters.size,
                    xdDeclaredType.classCategory?.toEnum(false) as ClassCategory,
                    xdDeclaredType.isViewGroup,
                    classMiniApiLevel,
                    resolvedAttributes,
                    resolvedSetters,
                    codeBlocks,
                    classInfo.xdDeclaredType,
                    packageName
            )
        }
        saveResult(result)

        return result
    }

    private fun ensureSuperClassesHaveResolvedAttributes(xdDeclaredType: XdDeclaredType) {
        println("\t--verifying that super classes are resolved")
        var areAllSuperClassesResolved = true
        Store.transactional {
            val xdSuperClasses = xdDeclaredType.superClasses.toList().sortedBy { xdSuperClass -> xdSuperClass.superClasses.size() }
            for (xdSuperClass in xdSuperClasses) {
                if (xdSuperClass.sourcererResult == null && xdSuperClass.widget != null) {
                    val superClassInfo = DeclaredTypeRegistry.getClassInfo(xdSuperClass) as ClassInfo
                    println("\t- super class ${xdSuperClass.targetClassName.simpleName} not yet resolved")
                    resolveAttributesInClass(superClassInfo)
                    areAllSuperClassesResolved = false
                }
            }
        }
        if (areAllSuperClassesResolved) {
            println("\t--all super classes are resolved")
        }
        println("\t--returning to initial target class ${xdDeclaredType.targetClassName}")
    }

    private fun getSuperClassResults(result: SourcererResult): List<SourcererResult> {
        val superClassResults = mutableListOf<SourcererResult>()
        Store.transactional {
            for (xdSuperClass in result.xdDeclaredType.superClasses.toList()) {
                if (xdSuperClass.widget != null) {
                    val xdResult = xdSuperClass.sourcererResult as XdSourcererResult
                    superClassResults.add(xdResult.toSnapshot())
                }
            }
        }
        return superClassResults
    }

    private fun mergeInterpretations(attributes: Map<String, Attribute>, interpretationResult: InterpretationResult) {
        interpretationResult.interpretations.forEach {
            if (it.attributes.isNotEmpty()) {
                mergeAttributesAndSetters(attributes, it.attributes, it.setters, interpretationResult)
            }
        }
    }

    private fun mergeAttributesAndSetters(
            attributes: Map<String, Attribute>,
            attributesFoundInSource: Map<String, Attribute>,
            settersFoundInSource: Map<Int, Setter>,
            interpretationResult: InterpretationResult
    ) {
        for (attrInSource in attributesFoundInSource.values) {
            if (!attributes.containsKey(attrInSource.name)) {
                //throw IllegalStateException("Attribute ${attrInSource.name} does not exist in attrs.xml")
                println("\t\t\t! - Attribute ${attrInSource.name} does not exist in attrs.xml.. Skipping it")
                continue
            }
            // if (attrInSource.hasSetter && !processedSetters.contains(attrInSource.resolvedSetter.name)) processedSetters.findOrCreate(attrInSource.resolvedSetter.name)

            val attribute = attributes[attrInSource.name] as Attribute
            when {
                attrInSource.setterHashCodes.isNotEmpty() && attribute.resolvedStatus < ResolvedStatus.SETTER_DEFINED -> {
                    addSetters(attribute, attrInSource, settersFoundInSource)
                    attribute.setterHashCodes.addAll(attrInSource.setterHashCodes)
                    attribute.getters.clear()
                    attribute.getters.addAll(attrInSource.getters)
                    attrInSource.typesPerSetter.forEach {
                        attribute.typesPerSetter[it.key] = it.value
                    }
                    attribute.oneFormatRequiresMultipleSetters = attrInSource.oneFormatRequiresMultipleSetters
                    attribute.resolvedStatus = ResolvedStatus.SETTER_DEFINED
                    attribute.resolvedByInterpreter = interpretationResult.name
                    interpretationResult.resolved += 1
                    interpretationResult.identifiedNew += 1
                }
                attrInSource.setterHashCodes.isNotEmpty() && attribute.resolvedStatus == ResolvedStatus.SETTER_DEFINED -> { // only add new settersFoundInSource
                    var hasAddedSetter = false
                    for (setterHashCode in attrInSource.setterHashCodes) {
                        if (!attribute.setterHashCodes.contains(setterHashCode)) {
                            attribute.setterHashCodes.add(setterHashCode)
                            setters[setterHashCode] = settersFoundInSource[setterHashCode] as Setter
                            val typesPerSetter = attrInSource.resolvedTypesPerSetter(setterHashCode)
                            attribute.typesPerSetter[setterHashCode] = typesPerSetter
                            hasAddedSetter = true
                        }
                    }
                    if (hasAddedSetter) {
                        if (!attribute.oneFormatRequiresMultipleSetters && attrInSource.oneFormatRequiresMultipleSetters) {
                            attribute.oneFormatRequiresMultipleSetters = true
                        }
                        attribute.resolvedByInterpreter += ", ${interpretationResult.name}"
                    }
                }
                attrInSource.setterHashCodes.isEmpty() && attribute.resolvedStatus < ResolvedStatus.IDENTIFIED_IN_SOURCE -> {
                    attrInSource.typesPerSetter.forEach {
                        attribute.typesPerSetter[it.key] = it.value
                    }
                    attribute.resolvedStatus = ResolvedStatus.IDENTIFIED_IN_SOURCE
                    interpretationResult.identifiedNew += 1
                }
            }
        }
    }

    private fun addSetters(attribute: Attribute, attrInSource: Attribute, settersInSource: Map<Int, Setter>) {
        attrInSource.setterHashCodes.forEach { setterHashCode ->
            val setter = settersInSource[setterHashCode] as Setter
            attribute.setterHashCodes.add(setterHashCode)
            setters[setterHashCode] = setter
        }
    }

    private fun saveResult(result: SourcererResult) {
        println("\t--saving result for ${result.targetClassName}")
        Store.transactional {
            result.toEntity()

        }
    }

    private fun getSavedResult(classInfo: ClassInfo): SourcererResult? {
        return Store.transactional {
            classInfo.xdDeclaredType.sourcererResult?.toSnapshot()
        }
    }

    private fun getSavedResult(xdDeclaredType: XdDeclaredType): SourcererResult? {
        return Companion[xdDeclaredType]
    }

    private fun getDefaultLayoutParamClass(classInfo: ClassInfo): ClassName? {
        return if (classInfo.xdDeclaredType.classCategory == XdClassCategory.View) {
                classInfo.xdDeclaredType.widget?.layoutParamClasses?.firstOrNull()?.targetClassName
            } else null
    }

    companion object {
        operator fun get(xdDeclaredType: XdDeclaredType): SourcererResult? {
            return Store.transactional {
                xdDeclaredType.sourcererResult?.toSnapshot()
            }
        }
        operator fun get(className: ClassName): SourcererResult? {
            val indexedClass = DeclaredTypeRegistry[className] ?: return null
            return this[indexedClass]
        }
        fun getAll(): List<SourcererResult> {
            return Store.transactional {
                XdSourcererResult.all().toList().map { xdResult ->
                    xdResult.toSnapshot()
                }
            }
        }
    }
}


