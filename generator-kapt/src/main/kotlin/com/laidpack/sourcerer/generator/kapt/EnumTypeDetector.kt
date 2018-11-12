package com.laidpack.sourcerer.generator.kapt

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import me.eugeniomarletti.kotlin.metadata.KotlinClassMetadata
import me.eugeniomarletti.kotlin.metadata.classKind
import me.eugeniomarletti.kotlin.metadata.kotlinMetadata
import me.eugeniomarletti.kotlin.metadata.shadow.metadata.ProtoBuf
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.DeclaredType

data class EnumType(
        val name: ClassName,
        val enumValues: Map<String, EnumConstant>
)
class EnumConstant(
        val name: String,
        val value: TypeName,
        val annotations: Map<String, Map<String, String>>
)

object EnumTypeDetector {
    fun getEnumType(element: TypeElement): EnumType {
        val typeMetadata = element.kotlinMetadata as? KotlinClassMetadata
            ?: throw IllegalArgumentException("Element is not a class. Element: $element")
        val proto = typeMetadata.data.classProto
        if (proto.classKind != ProtoBuf.Class.Kind.ENUM_CLASS) {
            throw IllegalArgumentException("Element is not an enum class. Actual kind: ${proto.classKind}. Element: $element")
        }
        val enumValues = declaredEnumValues(element, proto, typeMetadata)

        val typeName = element.asType().asTypeName()
        val name = when (typeName) {
            is ClassName -> typeName
            is ParameterizedTypeName -> typeName.rawType
            else -> throw IllegalStateException("unexpected TypeName: ${typeName::class}")
        }

        return EnumType(name, enumValues)
    }

    /** Returns the propertiesOrEnumValues declared by `typeElement`. */
    private fun declaredEnumValues(
            typeElement: TypeElement,
            classProto: ProtoBuf.Class,
            typeMetadata: KotlinClassMetadata
    ): Map<String, EnumConstant> {
        val nameResolver = typeMetadata.data.nameResolver
        val variableElements = typeElement.enclosedElements
                .filter { it is VariableElement }
                .associateBy { it.simpleName.toString() }
        val result = mutableMapOf<String, EnumConstant>()
        for (enumEntry in classProto.enumEntryList) {
            val name = nameResolver.getString(enumEntry.name)
            val variableElement = variableElements[name] as VariableElement
            val annotations = getAnnotations(variableElement)
            val value = variableElement.asType().asTypeName()
            result[name] = EnumConstant(name, value, annotations)
        }

        return result
    }

    private fun getAnnotations(variableElement: VariableElement?): Map<String, Map<String, String>> {
        val annotations = mutableMapOf<String, Map<String, String>>()
        if (variableElement != null) {
            for (annotationMirror in variableElement.annotationMirrors) {
                val annotationMembers = mutableMapOf<String, String>()
                for (elementValue in annotationMirror.elementValues) {
                    val value = elementValue.value
                    val memberValue = when (value) {
                        is DeclaredType -> {
                            val typeName = value.asTypeName()
                            if (typeName is ClassName) {
                                typeName.canonicalName
                            } else typeName.toString()
                        }
                        else -> value.toString()
                    }
                    annotationMembers[elementValue.key.simpleName.toString()] = memberValue
                }
                annotations[annotationMirror.annotationType.asTypeName().toString()] = annotationMembers
            }
        }
        return annotations
    }
}