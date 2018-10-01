package com.laidpack.sourcerer.generator.resources.templates

fun getBuildGradleContent(extraDependencies: String): String {
    return """
apply plugin: 'com.github.dcendents.android-maven'
group='com.github.dpnolte.sourcerer'

apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'

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

}

task sourceJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier "source"
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${'$'}kotlin_version"
    implementation "androidx.appcompat:appcompat:${'$'}androidX"
    implementation "com.squareup.moshi:moshi:${'$'}moshi"
    api 'com.github.dpnolte.ts-rhymer:annotation:0.1'
$extraDependencies
}
""".trimIndent()
}