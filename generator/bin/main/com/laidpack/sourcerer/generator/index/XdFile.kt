package com.laidpack.sourcerer.generator.index

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.Widget
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths

class XdFile(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<XdFile>()
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
    var compilationUnitBlob by xdRequiredBlobStringProp()
    val classes by xdChildren0_N(XdDeclaredType::file)
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