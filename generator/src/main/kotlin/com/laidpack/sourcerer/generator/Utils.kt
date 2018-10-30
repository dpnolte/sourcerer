package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment

fun initParserAndStore(env: SourcererEnvironment) {
    JavaParserContext.init(env)
    Store.init(env)
}


fun String.toCamelCase(): String {
    return this.split('_').joinToString("") {
        it.capitalize() }
}
fun String.indexOfFirstCapitalChar(): Int {
    for (index in 0 until this.length) {
        if (this[index].isUpperCase()) {
            return index
        }
    }
    return 0
}
fun String.splitByCapitalChar(): List<String> {
    return Regex("[A-Z]+[a-z0-9]*").findAll(this).map {
        it.value
    }.toList()
}