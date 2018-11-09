package com.laidpack.sourcerer.generator.resources.templates

import com.laidpack.generator.api.TypeScriptNameProvider

fun getBuildGradleContent(packageName: String, extraDependencies: String): String {
    return """
apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'com.github.dcendents.android-maven'
apply plugin: 'kotlin-kapt'

group='com.github.dpnolte.sourcerer'

android {
    compileSdkVersion rootProject.ext.compileSdkVersion

    defaultConfig {
        minSdkVersion rootProject.ext.minSdkVersion
        targetSdkVersion rootProject.ext.targetSdkVersion
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    sourceSets {
        main.java.srcDirs += 'src/main/kotlin/'
        test.java.srcDirs += 'src/test/kotlin/'
    }
    compileOptions {
        sourceCompatibility 1.7
        targetCompatibility 1.7
    }

}

task sourcesJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier = 'sources'
}
artifacts {
    archives sourcesJar
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${'$'}kotlin_version"
    implementation "androidx.appcompat:appcompat:${'$'}androidX"
    implementation "com.squareup.moshi:moshi:${'$'}moshi"
    kapt "com.squareup.moshi:moshi-kotlin-codegen:${'$'}moshi"
    compileOnly project(":generator-api")
    compileOnly "com.github.dpnolte.ts-rhymer:annotation:${'$'}tsRhymerVersion"
    kapt project(":generator-kapt")
$extraDependencies
}

kapt {
    arguments {
        arg("typescript.namespace", "${TypeScriptNameProvider.getModuleName(packageName)}") // becomes: declare namespace "..."
        arg("typescript.outputDir", "${'$'}{rootDir}/js") // where the file will be saved
        arg("typescript.filename", "${TypeScriptNameProvider.getAttributesFileName(packageName)}") // where the file will be saved
        arg("typescript.indent", "  ") // indentation (defaults to 2 spaces)
    }
}
""".trimIndent()
}