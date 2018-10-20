package com.laidpack.sourcerer.generator.generators.delegates

import android.animation.LayoutTransition
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.TextUtils
import android.view.Display
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Getter
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
abstract class DelegateGeneratorBase(val attributesParam: ParameterSpec, private val contextParam: ParameterSpec) {
    fun addTransformingMethodIfNeeded(valueProvider: (MutableList<Any>) -> String, attr: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat, args: MutableList<Any>): String {
        val typeName = typesForThisSetter.resolvedSetterType
        val arrayAccessorTransformer = if (!typesForThisSetter.unassociatedToParameter) {
            getArrayAccessorTransformer(attr, typeName, format)
        } else noTransform
        val resolvedTypeName = if (arrayAccessorTransformer != noTransform) {
            // we access array to retrieve compounded type.. check if we need to transform the compounded type
            ClassName.bestGuess(typeName.toString().replace("[]", ""))
        } else typeName
        val typeTransformer = if (!typesForThisSetter.unassociatedToParameter) {
            getFormatToTypeTransformer(attr, resolvedTypeName, format)
        } else noTransform

        return applyTransform(valueProvider, attr, args, typeTransformer, arrayAccessorTransformer)
    }

    fun addTransformingMethodIfNeeded(valueProvider: (MutableList<Any>) -> String, attr: Attribute, getter: Getter, typesForThisSetter: AttributeTypesForSetter, args: MutableList<Any>): String {
        val typeName = getter.typeName
        val format = typesForThisSetter.formats.first()
        val arrayAccessorTransformer = getArrayAccessorTransformer(attr, typeName, format)
        val resolvedTypeName = if (arrayAccessorTransformer != noTransform) {
            // we access array to retrieve compounded type.. check if we need to transform the compounded type
            ClassName.bestGuess(typeName.toString().replace("[]", ""))
        } else typeName
        // only apply type transformer when setter type and compounded type differ
        val typeTransformer = if (resolvedTypeName != typesForThisSetter.resolvedSetterType) {
            getTypeToFormatTransformer(attr, resolvedTypeName, format)
        } else noTransform

        return applyTransform(valueProvider, attr, args, typeTransformer, arrayAccessorTransformer)
    }

    private fun applyTransform(
            valueProvider: (MutableList<Any>) -> String,
            attr: Attribute,
            args: MutableList<Any>,
            typeTransformer: transformingMethod,
            arrayAccessorTransformer: transformingMethod
    ): String {
        return when {
            arrayAccessorTransformer != noTransform && typeTransformer != noTransform
                -> composeTransform(typeTransformer, arrayAccessorTransformer)(valueProvider, args, contextParam, attr)
            arrayAccessorTransformer != noTransform
                -> arrayAccessorTransformer(valueProvider, args, contextParam, attr)
            typeTransformer != noTransform
                -> typeTransformer(valueProvider, args, contextParam, attr)
            else ->
                valueProvider(args)
        }
    }

    private fun getArrayAccessorTransformer(attribute: Attribute, typeName: TypeName, format: StyleableAttributeFormat): transformingMethod {
        return when {
            transformArrayAccessorMap.containsKey(Triple(attribute.className.canonicalName, format, typeName))
            -> transformArrayAccessorMap[Triple(attribute.className.canonicalName, format, typeName)] as transformingMethod
            transformArrayAccessorMap.containsKey(Triple(wildcard, format, typeName))
            -> transformArrayAccessorMap[Triple(wildcard, format, typeName)] as transformingMethod
            else -> noTransform
        }
    }

    private fun getFormatToTypeTransformer(attribute: Attribute, typeName: TypeName, format: StyleableAttributeFormat): transformingMethod {
        return when {
            !format.requiresQualifier && format.toClass().asTypeName() == typeName -> noTransform
            format == StyleableAttributeFormat.Dimension && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Enum && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Flags && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Reference && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Color && typeName == intTypeName -> noTransform
            //formats == StyleableAttributeFormat.Unspecified && typeName == boolTypeName -> noTransform
            else -> {
                val conversionPair = Pair(format, typeName)
                val conversionPairAlternative = Pair(StyleableAttributeFormat.Integer, typeName)
                return when {
                    transformFormatToTypeMap.containsKey(conversionPair) -> transformFormatToTypeMap[conversionPair] as transformingMethod
                    transformFormatToTypeMap.containsKey(conversionPairAlternative) -> transformFormatToTypeMap[conversionPairAlternative] as transformingMethod
                    format == StyleableAttributeFormat.Unspecified -> noTransform
                    else -> throw IllegalStateException("No transforming method available from ${conversionPair.first} to ${conversionPair.second} (attribute: ${attribute.name})")
                }
            }
        }
    }

    private fun getTypeToFormatTransformer(attribute: Attribute, typeName: TypeName, format: StyleableAttributeFormat): transformingMethod {
        return when {
            !format.requiresQualifier && format.toClass().asTypeName() == typeName -> noTransform
            format == StyleableAttributeFormat.Dimension && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Enum && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Reference && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Color && typeName == intTypeName -> noTransform
            //formats == StyleableAttributeFormat.Unspecified && typeName == boolTypeName -> noTransform
            else -> {
                val conversionPair = Pair(typeName, format)
                val conversionPairAlternative = Pair(typeName, StyleableAttributeFormat.Integer)
                return when {
                    transformTypeToFormatMap.containsKey(conversionPair) -> transformTypeToFormatMap[conversionPair] as transformingMethod
                    transformTypeToFormatMap.containsKey(conversionPairAlternative) -> transformTypeToFormatMap[conversionPairAlternative] as transformingMethod
                    format == StyleableAttributeFormat.Unspecified -> noTransform
                    else -> throw IllegalStateException("No transforming method available from ${conversionPair.first} to ${conversionPair.second} (attribute: ${attribute.name})")
                }
            }
        }
    }


    private fun composeTransform(vararg functions: transformingMethod): transformingMethod {
        return {initialValueProvider, args, contextParam, attribute ->
            var valueProvider = initialValueProvider
            val argsPerFunction = mutableMapOf<Int, MutableList<Any>>()
            functions.forEachIndexed { index, f ->
                argsPerFunction[index] = mutableListOf()
                val result = f(valueProvider, argsPerFunction[index] as MutableList<Any>, contextParam, attribute)
                valueProvider = {a ->
                    a.addAll(argsPerFunction[index] as MutableList<Any>)
                    result
                }
            }
            val result = valueProvider(args)
            result
        }
    }

    companion object {
        // TODO: choose design support lib
        private val referenceToDrawable: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(ClassName("androidx.core.content", "ContextCompat"))
            args.add(context)
            val code = "%T.getDrawable(%N, ${valueProvider(args)}) as %T"
            args.add(Drawable::class.asTypeName())
            code
        }

        private val colorToDrawable: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(ColorDrawable::class.asTypeName())
            val code = "%T(${valueProvider(args)})"
            code
        }

        private val referenceToColorStateList: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(ClassName("androidx.core.content.res", "ResourcesCompat"))
            args.add("getColorStateList")
            args.add(context)
            args.add("resources")
            val code = "%T.%N(%N.%N, ${valueProvider(args)}, null)" // TODO: propagate theme?
            code
        }
        private val colorStateListToColor: transformingMethod = { valueProvider, args, context, attribute ->
            val code = "${valueProvider(args)}.defaultColor"
            code
        }
        private val colorToColorStateList: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(ColorStateList::class.asTypeName())
            val code = "%T.valueOf(${valueProvider(args)})"
            code
        }

        private val intToFloat: transformingMethod = { valueProvider, args,  _na, attribute ->
            "${valueProvider(args)}.toFloat()"
        }
        private val floatToInt: transformingMethod = { valueProvider, args,  _na, attribute ->
            "${valueProvider(args)}.toInt()"
        }
        private val intToLong: transformingMethod = { valueProvider, args,  _na, attribute  ->
            "${valueProvider(args)}.toLong()"
        }
        private val intToBool: transformingMethod = { valueProvider, args,  _na, attribute  ->
            "(${valueProvider(args)} == 1)"
        }
        private val boolToInt: transformingMethod = { valueProvider, args,  _na, attribute ->
            "(if (${valueProvider(args)}) 1 else 0)"
        }

        private val toPorterDuffTypeName = ClassName(SourcererEnvironment.servicesApiPackageName, "toPorterDuffMode")
        private val enumToPorterDuffMode: transformingMethod = { valueProvider, args, _na, attribute ->
            val value = valueProvider(args)
            args.add(toPorterDuffTypeName)
            "$value.%T()"
        }
        private val toScaleTypeName = ClassName(SourcererEnvironment.servicesApiPackageName, "toScaleType")
        private val enumToScaleType: transformingMethod = { valueProvider, args, _na, attribute ->
            val value = valueProvider(args)
            args.add(toScaleTypeName)
            "$value.%T()"
        }
        private val toTruncateAtTypeName = ClassName(SourcererEnvironment.servicesApiPackageName, "toTruncateAt")
        private val enumToTruncateAt: transformingMethod = { valueProvider, args, _na, attribute ->
            val value = valueProvider(args)
            args.add(toTruncateAtTypeName)
            "$value.%T()"
        }

        private val noTransform: transformingMethod = { valueProvider, args, _na, attribute ->
            valueProvider(args)
        }

        private val booleanToLayoutTransition: transformingMethod = { valueProvider, args, ___na, attribute ->
            val valueLiteral = valueProvider(args)
            args.add(layoutTransitionTypeName)
            "if ($valueLiteral) %T() else null"
        }

        private val intToInputFilter: transformingMethod = { valueProvider, args, _na, attribute ->
            args.add(InputFilter.LengthFilter::class.asTypeName())
            "%T(${valueProvider(args)})"
        }

        private val referenceToInterpolator: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(AnimationUtils::class.asTypeName())
            args.add(context)
            val value = valueProvider(args)
            "%T.loadInterpolator(%N, $value)"
        }

        private val anyToList: transformingMethod = { valueProvider, args, context, attribute ->
            val value = valueProvider(args)
            "listOf($value)"
        }
        private val listToFirstValue: transformingMethod = { valueProvider, args, contextParam, attribute ->
            val value = valueProvider(args)
            "$value.first()"
        }

        private val referenceToMotionSpec: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(motionSpecClassName)
            args.add(context)
            val value = valueProvider(args)
            "%T.createFromResource(%N, $value)"
        }

        private val referenceToString: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(context)
            val value = valueProvider(args)
            "%N.resources.getString($value)"
        }

        private val stringToBehavior: transformingMethod = { valueProvider, args, context, attribute ->
            args.add(ClassName("com.laidpack.sourcerer.generated.coordinatorlayout", "BehaviorUtils"))
            val value = valueProvider(args)
            args.add(context)
            "%T.createFromName($value, %N)"
        }

        // array accessor transformers
        private val drawableArrayClassName = ClassName.bestGuess("android.graphics.drawable.Drawable[]")
        private val referenceToDrawables: transformingMethod = { valueProvider, args, context, attribute ->
            val value = valueProvider(args)
            val lowerCaseName = attribute.name.toLowerCase()
            when {
                lowerCaseName.contains("left") || lowerCaseName.contains("start")
                    -> args.add(0)
                lowerCaseName.contains("top")
                    -> args.add(1)
                lowerCaseName.contains("right") || lowerCaseName.contains("end")
                    -> args.add(2)
                lowerCaseName.contains("bottom")
                    -> args.add(3)
                else -> throw IllegalArgumentException("Could not transform attribute ${attribute.name} to Drawable[] using referenceToDrawables transform")
            }
            "$value[%L]"
        }

        private val intTypeName = Int::class.asTypeName()
        private val boolTypeName = Boolean::class.asTypeName()
        private val layoutTransitionTypeName = LayoutTransition::class.asTypeName()
        private val motionSpecClassName = ClassName("com.google.android.material.animation", "MotionSpec")

        val transformFormatToTypeMap = mapOf(
                Pair(StyleableAttributeFormat.Reference, Drawable::class.asTypeName()) to referenceToDrawable,
                Pair(StyleableAttributeFormat.Color, Drawable::class.asTypeName()) to colorToDrawable,
                Pair(StyleableAttributeFormat.Integer, Drawable::class.asTypeName()) to referenceToDrawable,
                Pair(StyleableAttributeFormat.Integer, Float::class.asTypeName()) to intToFloat,
                Pair(StyleableAttributeFormat.Integer, Long::class.asTypeName()) to intToLong,
                Pair(StyleableAttributeFormat.Integer, boolTypeName) to intToBool,
                Pair(StyleableAttributeFormat.Dimension, Float::class.asTypeName()) to intToFloat,
                Pair(StyleableAttributeFormat.Color, ColorStateList::class.asTypeName()) to colorToColorStateList,
                Pair(StyleableAttributeFormat.Reference, ColorStateList::class.asTypeName()) to referenceToColorStateList,
                Pair(StyleableAttributeFormat.Integer, ColorStateList::class.asTypeName()) to referenceToColorStateList,
                Pair(StyleableAttributeFormat.Enum, PorterDuff.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, PorterDuff.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, Display.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, ImageView.ScaleType::class.asTypeName()) to enumToScaleType,
                Pair(StyleableAttributeFormat.Enum, ImageView.ScaleType::class.asTypeName()) to enumToScaleType,
                Pair(StyleableAttributeFormat.Integer, TextUtils.TruncateAt::class.asTypeName()) to enumToTruncateAt,
                Pair(StyleableAttributeFormat.Enum, TextUtils.TruncateAt::class.asTypeName()) to enumToTruncateAt,
                Pair(StyleableAttributeFormat.Boolean, layoutTransitionTypeName) to booleanToLayoutTransition,
                //Pair(StyleableAttributeFormat.Boolean, intTypeName) to boolToInt,
                Pair(StyleableAttributeFormat.Integer, InputFilter::class.asTypeName()) to intToInputFilter,
                Pair(StyleableAttributeFormat.Reference, Interpolator::class.asTypeName()) to referenceToInterpolator,
                Pair(StyleableAttributeFormat.Unspecified, Interpolator::class.asTypeName()) to referenceToInterpolator,
                Pair(StyleableAttributeFormat.Reference, motionSpecClassName) to referenceToMotionSpec,
                Pair(StyleableAttributeFormat.Unspecified, motionSpecClassName) to referenceToMotionSpec,
                Pair(StyleableAttributeFormat.String, ClassName.bestGuess("java.lang.String[]")) to anyToList,
                Pair(StyleableAttributeFormat.Reference, String::class.asTypeName()) to referenceToString,
                Pair(StyleableAttributeFormat.Integer, String::class.asTypeName()) to referenceToString,
                Pair(StyleableAttributeFormat.String, ClassName("androidx.coordinatorlayout.widget.CoordinatorLayout", "Behavior")) to stringToBehavior
        )
        val transformTypeToFormatMap = mapOf(
                Pair(Float::class.asTypeName(), StyleableAttributeFormat.Integer) to floatToInt,
                Pair(Long::class.asTypeName(), StyleableAttributeFormat.Integer) to floatToInt,
                Pair(boolTypeName, StyleableAttributeFormat.Integer) to boolToInt,
                Pair(Float::class.asTypeName(), StyleableAttributeFormat.Dimension) to floatToInt,
                Pair(intTypeName, StyleableAttributeFormat.Boolean) to intToBool,
                Pair(ColorStateList::class.asTypeName(), StyleableAttributeFormat.Color) to colorStateListToColor,
                Pair(ColorStateList::class.asTypeName(), StyleableAttributeFormat.Integer) to colorStateListToColor,
                Pair(ColorStateList::class.asTypeName(), StyleableAttributeFormat.Unspecified) to colorStateListToColor,
                Pair(ClassName.bestGuess("java.lang.String[]"), StyleableAttributeFormat.String) to listToFirstValue
        )
        private const val wildcard = "*"
        val transformArrayAccessorMap = mapOf(
                Triple(TextView::class.java.canonicalName, StyleableAttributeFormat.Reference, drawableArrayClassName)
                        to referenceToDrawables,
                Triple(TextView::class.java.canonicalName, StyleableAttributeFormat.Color, drawableArrayClassName)
                        to referenceToDrawables

        )
    }

}
typealias transformingMethod = (valueProvider: (MutableList<Any>) -> String, args: MutableList<Any>, contextParam: ParameterSpec, attribute: Attribute) -> String