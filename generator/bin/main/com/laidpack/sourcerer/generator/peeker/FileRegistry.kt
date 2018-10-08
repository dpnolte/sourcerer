package com.laidpack.sourcerer.generator.peeker

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.resources.Widget
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.*
import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.net.URI
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

class IndexedFile(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<IndexedFile>()
    var urlAsString by xdRequiredStringProp(unique = true, trimmed = true)
    private var cachedUrl : URL? = null
    val url : URL
        get() {
            if (cachedUrl == null) {
                Store.transactional {
                    cachedUrl = URL(urlAsString)
                }
            }
            return cachedUrl as URL
        }
    val content
        get() = url.readText()
    var scanPathString by xdRequiredStringProp(trimmed = true)
    private var cachedScanPath : Path? = null
    val scanPath : Path
        get() {
            if (cachedScanPath == null) {
                Store.transactional {
                    cachedScanPath = Paths.get(scanPathString)
                }
            }
            return cachedScanPath as Path
        }
    var lastModified by xdRequiredLongProp()
    var size by xdRequiredLongProp()
    val classes by xdChildren0_N(IndexedClass::file)
    val widgets by xdChildren0_N(Widget::file)
    private var cachedFilePath : Path? = null
    val filePath : Path
        get() {
            if (cachedFilePath == null) {
                Store.transactional {
                    cachedFilePath = Paths.get(url.file)
                }
            }
            return cachedFilePath as Path
        }

}

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
    fun all(): List<IndexedFile> {
        return Store.transactional {
            IndexedFile.all().toList()
        }
    }
    fun deleteIndexIfExists(indexedFile: IndexedFile) {
        val classesInFile = getClassesInFile(indexedFile)
        Store.transactional {
            classesInFile.forEach { classInFile ->
                // delete package references
                IndexedPackage.query(
                        IndexedPackage::classes contains classInFile
                ).toList().forEach { p ->
                    p.classes.remove(classInFile)
                }
                // delete subclass references
                IndexedClass.query(
                        IndexedClass::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false // triggers new class symbol resolvement to determine super classes
                }
            }
            indexedFile.delete()
        }
    }
    fun deleteFileIndexByClass(indexedClass: IndexedClass) {
        val classesInFile = getClassesInFile(indexedClass)
        Store.transactional {
            classesInFile.forEach { classInFile ->
                // delete package references
                IndexedPackage.query(
                        IndexedPackage::classes contains classInFile
                ).toList().forEach { p ->
                    p.classes.remove(classInFile)
                }
                // delete subclass references
                IndexedClass.query(
                        IndexedClass::superClasses contains classInFile
                ).toList().forEach { c ->
                    c.superClasses.remove(classInFile)
                    c.resolvedClassSymbol = false
                    c.resolvedWidgetSymbol = false
                }
            }

            indexedClass.file.delete()
        }
    }


    fun getFilePathForClass(indexedClass: IndexedClass): String {
        return Store.transactional {
            indexedClass.file.urlAsString
        }
    }

    fun findOrCreate(scanPath: Path, url: URL): IndexedFile {
        val providedUrlAsString = url.toString()
        return Store.transactional {
            IndexedFile.query(IndexedFile::urlAsString eq providedUrlAsString).firstOrNull() ?:
                    IndexedFile.new {
                        val file = if (url.isFileInJar()) {
                            scanPath.toFile()
                        } else url.toFile()
                        this.urlAsString = providedUrlAsString
                        this.lastModified = file.lastModified()
                        this.size = file.length()
                        this.scanPathString = scanPath.toString()
                    }
        }
    }

    fun getClassesInFile(indexedClass: IndexedClass): List<IndexedClass> {
        return Store.transactional {
            IndexedClass.query(
                    IndexedClass::file eq indexedClass.file
            ).toList()
        }
    }

    fun getClassesInFile(file: IndexedFile): List<IndexedClass> {
        return Store.transactional {
            IndexedClass.query(
                    IndexedClass::file eq file
            ).toList()
        }
    }

    operator fun get(file: File): IndexedFile? {
        val pathAsString = file.toString()
        return this[pathAsString]
    }
    operator fun get(url: URL): IndexedFile? {
        val urlAsString = url.toString()
        return this[urlAsString]
    }
    operator fun get(urlAsString: String): IndexedFile? {
        return Store.transactional {
            IndexedFile.query(IndexedFile::urlAsString eq urlAsString).firstOrNull()
        }
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