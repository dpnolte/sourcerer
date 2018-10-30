package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.index.*
import com.laidpack.sourcerer.generator.resources.widgets.XdWidgetSource
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import java.io.FileNotFoundException
import java.net.URL


class Widget (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<Widget>()
    var source by xdLink1(XdWidgetSource)
    var viewClass by xdLink0_1(XdDeclaredType::widgetAsBeingView)
    val layoutParamClasses by xdLink0_N(XdDeclaredType::widgetAsBeingLayoutParams)
    var file : XdFile by xdParent(XdFile::widgets)
    var attributesXmlUrlAsString by xdStringProp()
    val attributesXmlUrl: URL
        get() = Store.transactional {
            URL(attributesXmlUrlAsString)
        }
    val hasAttributesFile: Boolean
        get() = Store.transactional {
            if (attributesXmlUrl.isFileInJar()) {
                try {
                    attributesXmlUrl.openStream().close()
                    true
                } catch (e: FileNotFoundException) {
                    false
                }
            } else {
                attributesXmlUrl.toFile().exists()
            }
        }
}


