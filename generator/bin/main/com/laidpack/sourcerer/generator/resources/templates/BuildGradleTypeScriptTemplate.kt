package com.laidpack.sourcerer.generator.resources.templates

fun getBuildGradleContentForTypeScript(otherModuleDependencies: String): String {
    return """
apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'

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

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${'$'}kotlin_version"
    compileOnly "com.github.dpnolte.ts-rhymer:annotation:${'$'}tsRhymerVersion"
    kapt project(":generator-kapt")
$otherModuleDependencies
}

kapt {
    arguments {
        arg("typescript.module", "AttributeTypes") // becomes: declare module "Types"
        arg("typescript.outputDir", "${'$'}{rootDir}/js") // where the file will be saved
        arg("typescript.filename", "attribute.types.d.ts") // where the file will be saved
        arg("typescript.indent", "  ") // indentation (defaults to 2 spaces)
    }
}

""".trimIndent()
}