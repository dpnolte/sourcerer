package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import java.io.File
import java.nio.file.Path

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
fun Path.getExistingOrCreateFile(): File {
    val file = this.toFile()
    return if (file.exists()) {
        file
    } else {
        val created = file.createNewFile()
        if (!created) throw IllegalStateException("Failed to create file $this")
        file
    }
}