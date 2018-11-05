package com.laidpack.sourcerer.generator.kapt

import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.type.DeclaredType

object AnnotationUtils {
    fun getAnnotationMirrorsOfType(element: Element, annotationType: Class<*>): List<AnnotationMirror> {
        return element.annotationMirrors.filter {
            it.annotationType.asTypeName() == annotationType.asTypeName()
        }
    }
    fun firstAnnotationMirrorOfType(element: Element, annotationType: Class<*>): AnnotationMirror {
        return element.annotationMirrors.first {
            it.annotationType.asTypeName() == annotationType.asTypeName()
        }
    }
    fun getTypeNamesFromAnnotationMirror(annotationMirror: AnnotationMirror): Map<String, TypeName> {
        val declaredTypeMembers = annotationMirror.elementValues.filter {
            it.value.value is DeclaredType
        }
        return declaredTypeMembers.entries.associate {
            Pair(it.key.simpleName.toString(), (it.value.value as DeclaredType).asTypeName())
        }
    }

    fun getTypeNamesFromFirstAnnotationMirrorOfType(element: Element, annotationType: Class<*>): Map<String, TypeName> {
        val annotationMirror = firstAnnotationMirrorOfType(element, annotationType)
        return getTypeNamesFromAnnotationMirror(annotationMirror)
    }
    /*fun getClassNameFromAnnotationMember(compoundedAttribute: Attribute.Compound, memberName: String): ClassName {
        /*val attributePair = compoundedAttribute.values.find {
            it.fst. == memberName
        }
            ?: throw ArrayIndexOutOfBoundsException("$memberIndex is out of bounds of compounded attribute. $compoundedAttribute")

        val typeName = attributePair.snd.type.asTypeName()
        return typeName as? ClassName
            ?: throw TypeCastException("typename $typeName is not an instance of ClassName")*/
    }*/
}