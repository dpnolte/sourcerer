package com.laidpack.sourcerer.generator.index

import kotlinx.dnq.query.XdMutableQuery

interface XdMember {
    val name: String
    val declaredInType: XdDeclaredType
    val classes: XdMutableQuery<XdDeclaredType>
    val comment: String?
    val line: Int
    val isPublic: Boolean
    val memberIndex: Int
    val accessorHashCode: Int
    val hasAnyJavaDocAttributeBlockTag: Boolean
    val blockTags: Set<String>
    val attributeNamesFromBlockTags: Set<String>
    val canBeGetter: Boolean
    val canBeSetter: Boolean
    val isStatic: Boolean
    val nullable: Boolean
}