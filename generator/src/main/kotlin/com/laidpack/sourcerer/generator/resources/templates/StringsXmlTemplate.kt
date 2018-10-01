package com.laidpack.sourcerer.generator.resources.templates

fun getStringXmlTemplate(): String {
    return """
<resources>
    <string name="app_name">generated</string>
</resources>
""".trimIndent()
}