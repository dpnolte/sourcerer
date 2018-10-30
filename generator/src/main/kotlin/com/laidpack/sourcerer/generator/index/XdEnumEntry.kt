package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.EnumConstantDeclaration
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*

class XdEnumEntry(entity: Entity) : XdEntity(entity) {
    var name by xdRequiredStringProp()
    var declaredInType: XdDeclaredType by xdParent(XdDeclaredType::enumEntries)
    var comment by xdStringProp()
    var line by xdRequiredIntProp()
    var entryIndex by xdRequiredIntProp()
    companion object : XdNaturalEntityType<XdEnumEntry>() {
        fun createFromEnumConstantDeclaration(
                enumConstantDeclaration: EnumConstantDeclaration,
                providedEntryIndex: Int
        ): XdEnumEntry {
            return XdEnumEntry.new {
                name = enumConstantDeclaration.nameAsString
                if (enumConstantDeclaration.comment.isPresent) {
                    comment = enumConstantDeclaration.comment.get().content
                }
                line = enumConstantDeclaration.begin.get().line
                entryIndex = providedEntryIndex
            }
        }
    }
}