package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.laidpack.sourcerer.generator.target.*
import jetbrains.exodus.database.TransientEntityStore
import jetbrains.exodus.database.TransientStoreSession
import jetbrains.exodus.entitystore.QueryCancellingPolicy
import kotlinx.dnq.XdModel
import kotlinx.dnq.query.*
import kotlinx.dnq.store.container.StaticStoreContainer
import kotlinx.dnq.util.initMetaData
import java.lang.IllegalStateException

object Store {
    private lateinit var instance : TransientEntityStore
    fun init(env: SourcererEnvironment) {
        XdModel.registerNodes(
                XdSourcererResult,
                XdClassCategory,
                XdClass,
                XdFile,
                XdPackage,
                Widget,
                XdWidgetSource,
                XdFormat,
                XdParameter,
                XdSetter,
                XdAttribute,
                XdTransformingCode,
                XdAttributeTypesForSetter,
                XdStyleableAttributeFlag,
                XdStyleableAttributeEnumValue,
                XdCodeBlock,
                XdGetter,
                XdResolvedStatus,
                XdCallSignatureMap,
                XdAttributeToParameterIndex,
                XdConstructorExpression
        )

        val store = StaticStoreContainer.init(
                dbFolder = env.indexedDir,
                environmentName = "generator"
        )

        initMetaData(XdModel.hierarchy, store)

        instance = store
    }

    operator fun invoke(): TransientEntityStore {
        return instance
    }
    fun <T> transactional(
            readonly: Boolean = false,
            queryCancellingPolicy: QueryCancellingPolicy? = null,
            isNew: Boolean = false,
            block: (TransientStoreSession) -> T
    ): T {
        return instance.transactional(readonly, queryCancellingPolicy, isNew, block)
    }

    fun deleteAll() {
        instance.transactional {
            XdFile.all().toList().forEach { f ->
                f.delete()
            }
            XdPackage.all().toList().forEach { p ->
                p.delete()
            }
        }
    }

    fun deleteSourcererResult(simpleName: String) {
        instance.transactional {
            val indexedClasses = XdClass.query(
                    XdClass::simpleName eq simpleName
            ).toList()
            if (indexedClasses.size == 1) {
                val xdResult = indexedClasses.first().sourcererResult as XdSourcererResult
                xdResult.attributes.toList().forEach { xdAttr ->
                    xdAttr.getters.toList().forEach { xdGetter ->
                        xdGetter.parameters.entityIterable.forEach { e -> e.delete() }
                        xdGetter.delete()
                    }
                    xdAttr.typesPerSetter.entityIterable.forEach { e -> e.delete() }
                    xdAttr.delete()
                }
                xdResult.setters.toList().forEach { xdSetter ->
                    xdSetter.parameters.entityIterable.forEach { e -> e.delete() }
                    xdSetter.delete()
                }
                xdResult.codeBlocks.entityIterable.forEach { e -> e.delete() }
                xdResult.delete()

            } else throw IllegalStateException(if (indexedClasses.size > 1) "More than one class found for $simpleName" else "No classes found for $simpleName")
        }
    }

    fun deleteSourcererResults() {
        instance.transactional {
            XdClass.query(
                    XdClass::sourcererResult ne null
            ).asSequence().forEach {
                it.sourcererResult = null
            }
            XdGetter.all().entityIterable.forEach { e -> e.delete() }
            XdAttributeToParameterIndex.all().entityIterable.forEach { e -> e.delete() }
            XdCallSignatureMap.all().entityIterable.forEach { e -> e.delete() }
            XdAttributeTypesForSetter.all().entityIterable.forEach { e -> e.delete() }
            XdParameter.all().entityIterable.forEach { e -> e.delete() }
            XdSetter.all().entityIterable.forEach { e -> e.delete() }
            XdAttribute.all().entityIterable.forEach { e -> e.delete() }
            XdCodeBlock.all().entityIterable.forEach { e -> e.delete() }
            XdSourcererResult.all().entityIterable.forEach { entity ->
                entity.delete()
            }
        }
    }
}