package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.flow.attributes.FlowInterpreter
import com.laidpack.sourcerer.generator.generators.ClassGeneratorManager
import com.laidpack.sourcerer.generator.javadoc.JavaDocInterpreter
import com.laidpack.sourcerer.generator.lint.ApiDetector
import com.laidpack.sourcerer.generator.lint.ApiRequirementsChecker
import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.*
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.*

class Sourcerer(
        private val env: SourcererEnvironment,
        private val classRegistry: ClassRegistry = ClassRegistry(),
        private val apiRequirementsChecker: ApiRequirementsChecker = ApiRequirementsChecker(env, classRegistry)
) {
    private val setters = mutableMapOf<Int, Setter>()
    private val typedArrayInfo = ClassRegistry.getTypedArrayInfo(env.sdkStructure)
    private val interpreterFactories = listOf(
            FlowInterpreter,
            JavaDocInterpreter
    )

    fun generateFactoriesForAllWidgets() {
        for(indexedClass in ClassRegistry.getPotentialWidgetClasses()) {
            generateFactoriesForClass(indexedClass)
        }
    }

    fun generateFactoriesForClass(indexedClass: IndexedClass) {
        println("=================================")
        println("Processing ${indexedClass.targetClassName.canonicalName}")
        var result = getSavedResult(indexedClass)
        if (result == null) {
            println("\t--retrieving class info")
            val classInfo = classRegistry[indexedClass.targetClassName]
            if (classInfo != null) {
                result = analyzeSourceOfClass(classInfo)
            } else {
                println("\t--invalid class")
            }
        } else {
            println("\t--retrieved saved result")
        }
        if (result != null) {
            val generator = ClassGeneratorManager(
                    env.stubAppDir,
                    SourcererEnvironment.generatedPackageName,
                    SourcererEnvironment.generatedPackageName,
                    result,
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

    fun analyzeSourceOfClass(classInfo: ClassInfo, checkIfSuperClassesAreResolved: Boolean = true): SourcererResult {
        if (checkIfSuperClassesAreResolved) {
            ensureSuperClassesHaveResolvedAttributes(classInfo)
        }

        val savedResult = getSavedResult(classInfo)
        if (savedResult != null) {
            println("\t--restored saved result")
            classInfo.setResolvedAttributes(savedResult.attributes)
            return savedResult
        }

        /** get attributes from attrs.xml **/
        val attributes = classInfo.specifiedAttributes
        val attributeManager = AttributeManager(classInfo, attributes, classRegistry)

        /** interpret interesting methods/constructors and derive setters + attributes **/
        var codeBlocks : List<CodeBlock>
        val resolvedAttributes : Map<String, Attribute>
        val resolvedSetters: Map<Int, Setter>
        println("\t--analyzing source code")
        if (attributes.isNotEmpty()) {
            println("\t\t------------")
            interpreterFactories.forEach { factory ->
                val interpreter = factory.create(classInfo, typedArrayInfo, attributeManager, classRegistry)
                val interpretationResult = interpreter.interpret(setters)
                mergeInterpretations(attributes, interpretationResult)
                println("\t\t------------")
                println("\t\t${classInfo.targetClassName.simpleName}'s attributes identified with ${interpretationResult.name} interpretation: ${interpretationResult.identified}/${attributes.size}")
                println("\t\t-- of which are newly identified: ${interpretationResult.identifiedNew}/${attributes.size}")
                println("\t\t${classInfo.targetClassName.simpleName}'s attributes resolved with ${interpretationResult.name} interpretation: ${interpretationResult.resolved}/${attributes.size}")
            }

            println("\t\t------------")
            /** resolve type and group attributes to code blocks **/
            val codeBlockCollector = CodeBlockCollector(classInfo, attributeManager)
            val potentialResolvedAttributes = attributes.filter { it.value.resolvedStatus == ResolvedStatus.SETTER_DEFINED }
            codeBlocks = codeBlockCollector.reflectOnCodeBlockSociety(potentialResolvedAttributes, setters)
            val typePhilosopher = TypePhilosopher(attributeManager, classInfo)
            codeBlocks = typePhilosopher.contemplateOnTheMeaningOfTypes(codeBlocks)
            resolvedAttributes = codeBlocks.map { it.attributes.values }.flatten().associateBy { it.name }
            resolvedSetters = codeBlocks.map { it.setters.values }.flatten().associateBy { it.hashCode() }
            println("\t\t${classInfo.targetClassName.simpleName}'s attributes resolved total: ${resolvedAttributes.size}/${attributes.size}")
        } else {
            resolvedAttributes = mapOf()
            resolvedSetters = mapOf()
            codeBlocks = listOf()
            classInfo.setResolvedAttributes(resolvedAttributes)
            println("\t\tNo attributes specified, skipping source interpretation")
        }
        var fallbackClassName: ClassName? = null
        var classMiniApiLevel = ApiDetector.UNKNOWN_OR_VERSION_1
        if (apiRequirementsChecker.canCheckRequirements(classInfo)) {
            println("\t--checking minimum api level requirements")
            val apiRequirements = apiRequirementsChecker.checkMinApiRequirements(classInfo, codeBlocks)
            println("\t\t------------")
            println("\t\t${classInfo.targetClassName.simpleName} min api level: ${if (apiRequirements.classMinApiLevel == -1) "unknown or version 1" else apiRequirements.classMinApiLevel.toString()}")
            println("\t\tFallback class if any?: ${apiRequirements.fallbackClassName ?: "None"}")
            println("\t\t${apiRequirements.codeBlockMinApiLevelList.count { it > env.minSdkVersion }}/${apiRequirements.codeBlockMinApiLevelList.size} code blocks have min api level requirements (min sdk: ${env.minSdkVersion})")
            println("\t\t------------")
            apiRequirements.assignMinApiLevelRequirementsToCodeBlocks(codeBlocks)
            fallbackClassName = apiRequirements.fallbackClassName
            classMiniApiLevel = apiRequirements.classMinApiLevel
        }

        val result = SourcererResult(
                classInfo.targetClassName,
                if(classInfo.hasSuperClass) classInfo.superClassNames.first() else null,
                fallbackClassName,
                getDefaultLayoutParamClass(classInfo),
                classInfo.classDeclaration.isAbstract,
                classInfo.classDeclaration.isFinal,
                classInfo.constructorExpression,
                classInfo.classDeclaration.typeParameters.size,
                classInfo.classCategory,
                classInfo.isViewGroup,
                classMiniApiLevel,
                resolvedAttributes,
                resolvedSetters,
                codeBlocks,
                classInfo.indexedClass
        )
        saveResult(result)

        return result
    }

    private fun ensureSuperClassesHaveResolvedAttributes(classInfo: ClassInfo) {
        println("\t--retrieving super classes info")
        val superClassesInfo = classRegistry.getSuperClasses(classInfo)
                .sortedBy { it.superClassNames.size }
        for (superClassInfo in superClassesInfo) {
            if (!superClassInfo.hasResolvedAttributes) {
                println("\t--super class ${superClassInfo.targetClassName} not yet resolved, analyze source")
                analyzeSourceOfClass(superClassInfo, checkIfSuperClassesAreResolved = false)
            }
        }
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
            classInfo.indexedClass.sourcererResult?.toSnapshot()
        }
    }

    fun getSavedResult(resolvedClass: SymbolResolvedClass): SourcererResult? {
        return Store.transactional {
            resolvedClass.indexedClass.sourcererResult?.toSnapshot()
        }
    }

    private fun getSavedResult(indexedClass: IndexedClass): SourcererResult? {
        return Companion[indexedClass]
    }

    private fun getDefaultLayoutParamClass(classInfo: ClassInfo): ClassName? {
        return if (classInfo.classCategory == ClassCategory.View) {
            Store.transactional { classInfo.widget.layoutParamClasses.firstOrNull()?.targetClassName }
        } else null
    }

    companion object {
        operator fun get(indexedClass: IndexedClass): SourcererResult? {
            return Store.transactional {
                indexedClass.sourcererResult?.toSnapshot()
            }
        }
        operator fun get(className: ClassName): SourcererResult? {
            val indexedClass = ClassRegistry[className] ?: return null
            return this[indexedClass]
        }
    }
}


data class SourcererResult(
        val targetClassName: ClassName,
        val superClassName: ClassName?,
        val fallbackClassName: ClassName?,
        val defaultLayoutParamsClassName: ClassName?,
        val isAbstract: Boolean,
        val isFinal: Boolean,
        val constructorExpression: ConstructorExpression,
        val numberOfTypeVariables: Int,
        val classCategory: ClassCategory,
        val isViewGroup: Boolean,
        val minimumApiLevel: Int,
        val attributes: Map<String, Attribute>,
        val setters: Map<Int, Setter>,
        val codeBlocks: List<CodeBlock>,
        val indexedClass: IndexedClass
) {
    fun toEntity(): XdSourcererResult {
        val xdResult = XdSourcererResult.new()
        xdResult.targetClass = this.indexedClass
        xdResult.targetClassCanonicalName = this.targetClassName.canonicalName
        if (this.superClassName != null) xdResult.superClassCanonicalName = this.superClassName.canonicalName
        if (this.fallbackClassName != null) xdResult.fallbackClassCanonicalName = this.fallbackClassName.canonicalName
        if (this.defaultLayoutParamsClassName != null) xdResult.defaultLayoutParamsClassName = this.defaultLayoutParamsClassName.canonicalName
        xdResult.isAbstract = this.isAbstract
        xdResult.isFinal = this.isFinal
        xdResult.constructorExpression = this.constructorExpression.toEntity()
        xdResult.numberOfTypeVariables = this.numberOfTypeVariables
        xdResult.classCategory = this.classCategory.toEntity()
        xdResult.isViewGroup = this.isViewGroup
        xdResult.minimumApiLevel = this.minimumApiLevel

        val xdSetters = mutableMapOf<Int, XdSetter>()
        for (setter in this.setters) {
            val xdSetter = setter.value.toEntity(xdResult)
            xdSetters[setter.key] = xdSetter
            xdResult.setters.add(xdSetter)
        }
        val xdAttributes = mutableMapOf<String, XdAttribute>()
        for (attribute in this.attributes) {
            val xdAttribute = attribute.value.toEntity(xdSetters, xdResult)
            xdAttributes[attribute.key] = xdAttribute
            xdResult.attributes.add(xdAttribute)
        }
        for (codeBlock in this.codeBlocks) {
            xdResult.codeBlocks.add(
                    codeBlock.toEntity(xdResult, xdSetters, xdAttributes)
            )
        }
        return xdResult
    }
}


class XdSourcererResult(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdSourcererResult>()

    var targetClass : IndexedClass by xdParent(IndexedClass::sourcererResult)
    var targetClassCanonicalName by xdRequiredStringProp()
    var superClassCanonicalName by xdStringProp()
    var fallbackClassCanonicalName by xdStringProp()
    var defaultLayoutParamsClassName by xdStringProp()
    var isAbstract by xdBooleanProp()
    var isFinal by xdBooleanProp()
    var constructorExpression by xdLink1(XdConstructorExpression)
    var numberOfTypeVariables by xdRequiredIntProp()
    var classCategory by xdLink1(XdClassCategory)
    var isViewGroup by xdBooleanProp()
    var minimumApiLevel by xdRequiredIntProp()
    val attributes by xdChildren0_N(XdAttribute::sourcererResult)
    val setters by xdChildren0_N(XdSetter::sourcererResult)
    val codeBlocks by xdChildren0_N(XdCodeBlock::sourcererResult)

    fun toSnapshot(transaction: Boolean = true): SourcererResult {
        val block = {
            val attributes = this.attributes.toList().associate {
                Pair(it.name, it.toSnapshot(false))
            }
            val setters = this.setters.toList().associate {
                val setter = it.toSnapshot(false)
                Pair(setter.hashCode(), setter)
            }
            SourcererResult(
                    this.targetClass.targetClassName,
                    if (this.superClassCanonicalName != null) ClassName.bestGuess(this.superClassCanonicalName as String) else null,
                    if (this.fallbackClassCanonicalName != null) ClassName.bestGuess(this.fallbackClassCanonicalName as String) else null,
                    if (this.defaultLayoutParamsClassName != null) ClassName.bestGuess(this.defaultLayoutParamsClassName as String) else null,
                    this.isAbstract,
                    this.isFinal,
                    this.constructorExpression.toEnum(false),
                    this.numberOfTypeVariables,
                    this.classCategory.toEnum(false),
                    this.isViewGroup,
                    this.minimumApiLevel,
                    attributes,
                    setters,
                    this.codeBlocks.toList().map {
                        it.toSnapshot(setters, attributes, transaction = false)
                    },
                    this.targetClass
            )
        }
        return if(transaction) Store.transactional { block() } else block()
    }

}
