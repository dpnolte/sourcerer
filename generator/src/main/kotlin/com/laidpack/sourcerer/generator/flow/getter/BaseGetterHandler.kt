package com.laidpack.sourcerer.generator.flow.getter

import com.github.javaparser.ast.Node
import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import kotlin.reflect.KClass

abstract class BaseGetterHandler<T : Node>(protected val flow: GetterFlow, targetType: KClass<T>)
    : BaseNodeHandler<T>(targetType) {

}