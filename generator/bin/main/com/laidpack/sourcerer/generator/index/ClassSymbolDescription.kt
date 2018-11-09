package com.laidpack.sourcerer.generator.index

import com.laidpack.sourcerer.generator.resources.Widget
import com.squareup.kotlinpoet.ClassName

data class ClassSymbolDescription (
        val targetClassName: ClassName,
        val widget: Widget?,
        val classCategory : ClassCategory,
        val isViewGroup: Boolean,
        val superClassNames: List<ClassName>,
        val xdDeclaredType: XdDeclaredType
)
