package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.serialization.JavaParserJsonDeserializer
import com.github.javaparser.serialization.JavaParserJsonSerializer
import com.laidpack.sourcerer.generator.Store
import kotlinx.dnq.query.*
import java.io.File
import java.io.StringReader
import java.io.StringWriter
import java.lang.IllegalArgumentException
import java.net.URI
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.json.Json
import kotlin.IllegalStateException
import kotlin.system.measureTimeMillis

object FileRegistry {
    fun isFileIndexed(url: URL): Boolean {
        val indexedFile = this[url] ?: return false
        val validIndex = Store.transactional {
            val file = if (indexedFile.url.isFileInJar()) {
                indexedFile.scanPath.toFile()
            } else url.toFile()
            indexedFile.lastModified == file.lastModified()
                && indexedFile.size == file.length()
        }
        if (!validIndex) {
            deleteIndexIfExists(indexedFile) // delete invalid index
        }
        return validIndex
    }
    fun all(): List<XdFile> {
        return Store.transactional {
            XdFile.all().toList()
        }
    }
    fun deleteIndexIfExists(xdFile: XdFile) {
        val classesInFile = getClassesInFile(xdFile)
        Store.transactional {
            classesInFile.forEach { classInFile ->
                // delete package references
                XdPackage.query(
                        XdPackage::classes contains classInFile
                ).toList().forEach { p ->
                    p.classes.remove(classInFile)
                }
                // delete subclass references
                XdDeclaredType.query(
                        XdDeclaredType::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false // triggers new class symbol resolvement to determine super classes
                }
            }
            xdFile.delete()
        }
    }
    fun deleteFileIndexByClass(xdDeclaredType: XdDeclaredType) {
        val classesInFile = getClassesInFile(xdDeclaredType)
        Store.transactional {
            classesInFile.forEach { classInFile ->
                // delete package references
                XdPackage.query(
                        XdPackage::classes contains classInFile
                ).toList().forEach { p ->
                    p.classes.remove(classInFile)
                }
                // delete subclass references
                XdDeclaredType.query(
                        XdDeclaredType::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false
                }
            }

            val file = xdDeclaredType.file
            file.delete()
        }
    }


    fun getFilePathForClass(xdDeclaredType: XdDeclaredType): String {
        return Store.transactional {
            xdDeclaredType.file.urlAsString
        }
    }

    fun findOrCreate(scanPath: Path, url: URL, compilationUnit: CompilationUnit): XdFile {
        val providedUrlAsString = url.toString()
        return Store.transactional {
            XdFile.query(XdFile::urlAsString eq providedUrlAsString).firstOrNull() ?:
                    XdFile.new {
                        val file = if (url.isFileInJar()) {
                            scanPath.toFile()
                        } else url.toFile()
                        this.urlAsString = providedUrlAsString
                        this.lastModified = file.lastModified()
                        this.size = file.length()
                        this.scanPathString = scanPath.toString()
                        this.compilationUnitBlob = serializeCompilationUnit(compilationUnit)
                    }
        }
    }

    fun getClassesInFile(xdDeclaredType: XdDeclaredType): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(
                    XdDeclaredType::file eq xdDeclaredType.file
            ).toList()
        }
    }

    fun getClassesInFile(file: XdFile): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(
                    XdDeclaredType::file eq file
            ).toList()
        }
    }

    operator fun get(file: File): XdFile? {
        val pathAsString = file.toString()
        return this[pathAsString]
    }
    operator fun get(url: URL): XdFile? {
        val urlAsString = url.toString()
        return this[urlAsString]
    }
    operator fun get(urlAsString: String): XdFile? {
        return Store.transactional {
            XdFile.query(XdFile::urlAsString eq urlAsString).firstOrNull()
        }
    }


    private val generatorFactory = Json.createGeneratorFactory(mapOf<String, Any>())
    private val serializer = JavaParserJsonSerializer()
    private val deserializer = JavaParserJsonDeserializer()
    private val cachedCompilationsUnits = LinkedHashMap<String, CompilationUnit>()
    fun serializeCompilationUnit(cu: CompilationUnit): String {
        val jsonWriter = StringWriter()
        generatorFactory.createGenerator(jsonWriter).use { generator -> serializer.serialize(cu, generator) }
        return jsonWriter.toString()
    }
    fun deserializeCompilationUnit(fileUrl: URL, blob: String): CompilationUnit {
        val fileKey = fileUrl.toString()
        if (cachedCompilationsUnits.containsKey(fileKey)) {
            val cu = cachedCompilationsUnits[fileKey] as CompilationUnit
            // move cached item to tail
            cachedCompilationsUnits.remove(fileKey)
            cachedCompilationsUnits[fileKey] = cu
            return cu
        }
        var cu: CompilationUnit? = null
        val elapsedTime = measureTimeMillis {
            cu = deserializer.deserializeObject(
                    Json.createReader(StringReader(blob))
            ) as? CompilationUnit ?: throw IllegalStateException("file content blob is not deserialized as CompilationUnit")
            if (cachedCompilationsUnits.size >= 50) {
                val toBeRemovedKey = cachedCompilationsUnits.iterator().next().key
                cachedCompilationsUnits.remove(toBeRemovedKey)
            }
            cachedCompilationsUnits[fileKey] = cu as CompilationUnit
        }
        println("\t\t\tDeserialized $fileKey - $elapsedTime ms")

        return cu as CompilationUnit
    }
}

fun URL.readText(): String {
    return if (this.isFileInJar()) {
        val inputStream = this.openStream()
                ?: throw IllegalArgumentException("File not found in jar @ $this")
        val s = Scanner(inputStream).useDelimiter("\\A")
        val contents = if (s.hasNext()) s.next() else ""
        inputStream.close()
        contents
    } else {
        Paths.get(this.toURI()).toFile().readText()
    }
}


fun URL.isFileInJar(): Boolean {
    return this.protocol == "jar"
}
fun URL.toFile(): File {
    if (this.isFileInJar()) throw IllegalStateException("Url points to file in jar")
    return Paths.get(this.toURI()).toFile()
}

fun Path.toJarUri(): URI {
    return URI("jar", this.toUri().toString(), null)
}
/*
        URL jarUrl = new URL("jar:file:/C:/proj/parser/jar/parser.jar!/test.xml");
        assertEquals("jar", jarUrl.getProtocol());
        assertEquals("file:/C:/proj/parser/jar/parser.jar!/test.xml", jarUrl.getFile());
        URL fileUrl = new URL(jarUrl.getFile());
        assertEquals("file", fileUrl.getProtocol());
        assertEquals("/C:/proj/parser/jar/parser.jar!/test.xml", fileUrl.getFile());
        String[] parts = fileUrl.getFile().split("!");
        assertEquals("/C:/proj/parser/jar/parser.jar", parts[0]);
 */