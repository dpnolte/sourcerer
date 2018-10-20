package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.CompilationUnit
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.peeker.serializer.JavaParserJsonDeserializer
import com.laidpack.sourcerer.generator.peeker.serializer.JavaParserJsonSerializer
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
                XdClass.query(
                        XdClass::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false // triggers new class symbol resolvement to determine super classes
                }
            }
            xdFile.delete()
        }
    }
    fun deleteFileIndexByClass(xdClass: XdClass) {
        val classesInFile = getClassesInFile(xdClass)
        Store.transactional {
            classesInFile.forEach { classInFile ->
                // delete package references
                XdPackage.query(
                        XdPackage::classes contains classInFile
                ).toList().forEach { p ->
                    p.classes.remove(classInFile)
                }
                // delete subclass references
                XdClass.query(
                        XdClass::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false
                }
            }

            val file = xdClass.file
            file.delete()
        }
    }


    fun getFilePathForClass(xdClass: XdClass): String {
        return Store.transactional {
            xdClass.file.urlAsString
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

    fun getClassesInFile(xdClass: XdClass): List<XdClass> {
        return Store.transactional {
            XdClass.query(
                    XdClass::file eq xdClass.file
            ).toList()
        }
    }

    fun getClassesInFile(file: XdFile): List<XdClass> {
        return Store.transactional {
            XdClass.query(
                    XdClass::file eq file
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
    fun serializeCompilationUnit(cu: CompilationUnit): String {
        val jsonWriter = StringWriter()
        generatorFactory.createGenerator(jsonWriter).use { generator -> serializer.serialize(cu, generator) }
        return jsonWriter.toString()
    }
    fun deserializeCompilationUnit(blob: String): CompilationUnit {
        return deserializer.deserializeObject(
                Json.createReader(StringReader(blob))
        ) as? CompilationUnit ?: throw IllegalStateException("serialized file blob does not deserialize into CompilationUnit")
    }
}

fun URL.readText(): String {
    return if (this.isFileInJar()) {
        /*
        val inputStream = ClassLoader.getSystemResourceAsStream(this.toString())
                ?: throw IllegalArgumentException("File not found in jar @ $this")*/
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