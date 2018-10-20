package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.Node
import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import kotlin.reflect.KClass

abstract class BaseAttributesHandler<T : Node>(protected val flow: AttributeFlow, targetType: KClass<T>)
    : BaseNodeHandler<T>(targetType) {

}