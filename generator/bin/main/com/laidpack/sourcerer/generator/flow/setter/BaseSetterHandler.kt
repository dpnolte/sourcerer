package com.laidpack.sourcerer.generator.flow.setter

import com.github.javaparser.ast.Node
import com.laidpack.sourcerer.generator.flow.BaseHandler
import kotlin.reflect.KClass

abstract class BaseSetterHandler<T : Node>(protected val flow: SetterFlow, targetType: KClass<T>)
    : BaseHandler<T>(targetType) {

}