package com.laidpack.sourcerer.generator.resources.templates

fun getAndroidManifestTemplate(packageName: String): String {
    return """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="$packageName" />
""".trimIndent()
}