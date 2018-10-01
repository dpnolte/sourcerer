package com.laidpack.sourcerer.generator.resources.templates

fun getAndroidManifestTemplate(): String {
    return """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.laidpack.sourcerer.generated" />
""".trimIndent()
}