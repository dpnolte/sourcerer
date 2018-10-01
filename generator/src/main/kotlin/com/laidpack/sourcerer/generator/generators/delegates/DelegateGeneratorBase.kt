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
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Getter
import com.laidpack.sourcerer.generator.generators.FormatEnumGenerator
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
abstract class DelegateGeneratorBase(val attributesParam: ParameterSpec, private val contextParam: ParameterSpec) {
    protected fun addTransformingMethodIfNeeded(valueProvider: (MutableList<Any>) -> String, attr: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat, args: MutableList<Any>): String {
        val tm = if (!typesForThisSetter.unassociatedToParameter) {
             getTransformingMethod(
                    attr,
                    typesForThisSetter,
                    format
            )
        } else noTransform
        return tm.apply(valueProvider, args, contextParam)
    }

    private fun addTransformingMethodIfNeeded(valueProvider: (MutableList<Any>) -> String, attr: Attribute, getter: Getter, format: StyleableAttributeFormat, args: MutableList<Any>): String {
        val tm = getTransformingMethod(
                attr,
                getter,
                format
        )
        return tm.apply(valueProvider, args, contextParam)
    }

    private fun getTransformingMethod(attribute: Attribute, getter: Getter, format: StyleableAttributeFormat): TransformingMethod {
        val getterType = getter.typeName
        return getTransformingMethod(attribute, getterType, format)
    }

    private fun getTransformingMethod(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): TransformingMethod {
        val setterParameterType = typesForThisSetter.resolvedSetterType
        return getTransformingMethod(attribute, setterParameterType, format)
    }

    private fun getTransformingMethod(attribute: Attribute, typeName: TypeName, format: StyleableAttributeFormat): TransformingMethod {
        return when {
            !format.requiresQualifier && format.toClass().asTypeName() == typeName -> noTransform
            format == StyleableAttributeFormat.Dimension && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Enum && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Reference && typeName == intTypeName -> noTransform
            format == StyleableAttributeFormat.Color && typeName == intTypeName -> noTransform
            //format == StyleableAttributeFormat.Unspecified && typeName == boolTypeName -> noTransform
            else -> {
                val conversionPair = Pair(format, typeName)
                val conversionPairAlternative = Pair(StyleableAttributeFormat.Integer, typeName)
                return when {
                    transformingMethodTypes.containsKey(conversionPair) -> transformingMethodTypes[conversionPair] as TransformingMethod
                    transformingMethodTypes.containsKey(conversionPairAlternative) -> transformingMethodTypes[conversionPairAlternative] as TransformingMethod
                    format == StyleableAttributeFormat.Unspecified -> noTransform
                    else -> throw IllegalStateException("No transforming method available from ${conversionPair.first} to ${conversionPair.second} (attribute: ${attribute.name})")
                }

            }
        }
    }

    data class ValueForFormat(val expression: String, val args: MutableList<Any>)
    fun getMultiFormattedValue(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueForFormat {
        val valueProvider = {a: MutableList<Any> ->
            a.add(attributesParam.name)
            a.add(attribute.name)
            a.add(format.name.decapitalize())
            "%N.%N.%N"
        }
        val args = mutableListOf<Any>()
        val expression = addTransformingMethodIfNeeded(valueProvider, attribute, typesForThisSetter, format, args)
        return ValueForFormat(expression, args)
    }

    fun getSingleFormattedValue(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueForFormat {
        val valueProvider = {a: MutableList<Any> ->
            if (typesForThisSetter.hasEnumAsAttributeType) {
                a.add(attributesParam.name)
                a.add(attribute.name)
                "%L.%L.value"
            } else {
                a.add(attributesParam.name)
                a.add(attribute.name)
                "%L.%L"
            }
        }
        val args = mutableListOf<Any>()
        val expression = addTransformingMethodIfNeeded(valueProvider, attribute, typesForThisSetter, format, args)
        return ValueForFormat(expression, args)
    }

    fun getValuePerLiteral(valueLiteral: String, attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueForFormat {
        val valueProvider = {a: MutableList<Any> ->
            a.add(valueLiteral)
            "%L"
        }
        val args = mutableListOf<Any>()
        val expression = addTransformingMethodIfNeeded(
                valueProvider,
                attribute,
                typesForThisSetter,
                typesForThisSetter.formats.first(),
                args
        )
        return ValueForFormat(expression, args)
    }

    fun getValuePerLiteral(valueLiteral: String, attribute: Attribute, getter: Getter, format: StyleableAttributeFormat): ValueForFormat {
        val valueProvider = {a: MutableList<Any> ->
            a.add(valueLiteral)
            "%L"
        }
        val args = mutableListOf<Any>()
        val expression = addTransformingMethodIfNeeded(
                valueProvider,
                attribute,
                getter,
                format,
                args
        )
        return ValueForFormat(expression, args)
    }

    protected fun getValueFormat(valueProvider: (MutableList<Any>) -> String, attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueForFormat {
        val args = mutableListOf<Any>()
        val expression = addTransformingMethodIfNeeded(
                valueProvider,
                attribute,
                typesForThisSetter,
                typesForThisSetter.formats.first(),
                args
        )
        return ValueForFormat(expression, args)
    }


    protected fun getDefaultValueProvider(attributeParam: ParameterSpec, attr: Attribute, typesPerSetter: AttributeTypesForSetter): (MutableList<Any>) -> String {
        return {a: MutableList<Any> ->
            if (typesPerSetter.hasEnumAsAttributeType) {
                a.add(attributesParam.name)
                a.add(attr.name)
                "${attributesParam.name}.${attr.name}.value"
            } else {
                a.add(attributesParam.name)
                a.add(attr.name)
                "${attributesParam.name}.${attr.name}"
            }
        }
    }

    class TransformingMethod(
            val apply : (valueProvider: (MutableList<Any>) -> String, MutableList<Any>, contextParam: ParameterSpec) -> String
    )

    companion object {
        // TODO: choose design support lib
        private val referenceToDrawable = TransformingMethod(
                apply = { valueProvider, args, context ->
                    args.add(ClassName("androidx.core.content", "ContextCompat"))
                    args.add(context)
                    val code = "%T.getDrawable(%N, ${valueProvider(args)}) as %T"
                    args.add(Drawable::class.asTypeName())
                    code
                }
        )

        private val colorToDrawable = TransformingMethod(
                apply = { valueProvider, args, context ->
                    args.add(ColorDrawable::class.asTypeName())
                    val code = "%T(${valueProvider(args)})"
                    code
                }
        )

        private val colorToColorStateList = TransformingMethod(
                apply = { valueProvider, args, context ->
                    args.add(ClassName("androidx.core.content.res", "ResourcesCompat"))
                    args.add("getColorStateList")
                    args.add(context)
                    args.add("resources")
                    val code = "%T.%N(%N.%N, ${valueProvider(args)}, null)" // TODO: propagate theme?
                    code
                }
        )

        private val intToFloat = TransformingMethod(
                apply = { valueProvider, args,  _na ->
                    "${valueProvider(args)}.toFloat()"
                }
        )
        private val intToLong = TransformingMethod(
                apply = { valueProvider, args,  _na  ->
                    "${valueProvider(args)}.toLong()"
                }
        )
        private val intToBool = TransformingMethod(
                apply = { valueProvider, args,  _na  ->
                    "${valueProvider(args)} == 1"
                }
        )
        private val boolToInt = TransformingMethod(
                apply = { valueProvider, args,  _na ->
                    "if (${valueProvider(args)}) 1 else 0"
                }
        )

        private val enumToPorterDuffMode = TransformingMethod(
                apply = { valueProvider, args, _na ->
                    val value = valueProvider(args)
                    args.add("toPorterDuffMode")
                    "$value.%N()"

                }
        )
        private val enumToScaleType= TransformingMethod(
                apply = { valueProvider, args, _na ->
                    val value = valueProvider(args)
                    args.add("toScaleType")
                    "$value.%N()"

                }
        )
        private val enumToTruncateAt= TransformingMethod(
                apply = { valueProvider, args, _na ->
                    val value = valueProvider(args)
                    args.add("toTruncateAt")
                    "$value.%N()"

                }
        )

        private val noTransform = TransformingMethod(
                apply = { valueProvider, args, _na ->
                    valueProvider(args)
                }
        )

        private val booleanToLayoutTransition = TransformingMethod(
                apply = { valueProvider, args, ___na ->
                    val valueLiteral = valueProvider(args)
                    args.add(layoutTransitionTypeName)
                    "if ($valueLiteral) %T() else null"
                }
        )

        private val intToInputFilter = TransformingMethod(
                apply = { valueProvider, args, _na ->
                    args.add(InputFilter.LengthFilter::class.asTypeName())
                    "%T(${valueProvider(args)})"
                }
        )

        private val referenceToInterpolator = TransformingMethod(
                apply = { valueProvider, args, context ->
                    args.add(AnimationUtils::class.asTypeName())
                    args.add(context)
                    val value = valueProvider(args)
                    "%T.loadInterpolator(%N, $value)"
                }
        )

        private val anyToList = TransformingMethod(
                apply = { valueProvider, args, context ->
                    val value = valueProvider(args)
                    "listOf($value)"
                }
        )

        private val referenceToMotionSpec = TransformingMethod(
                apply = { valueProvider, args, context ->
                    args.add(motionSpecClassName)
                    args.add(context)
                    val value = valueProvider(args)
                    "%T.createFromResource(%N, $value)"
                }
        )

        private val intTypeName = Int::class.asTypeName()
        private val boolTypeName = Boolean::class.asTypeName()
        //private val floatTypeName = Float::class.asTypeName()
        private val layoutTransitionTypeName = LayoutTransition::class.asTypeName()
        private val motionSpecClassName = ClassName("com.google.android.material.animation", "MotionSpec")

        val transformingMethodTypes = mapOf(
                Pair(StyleableAttributeFormat.Reference, Drawable::class.asTypeName()) to referenceToDrawable,
                Pair(StyleableAttributeFormat.Color, Drawable::class.asTypeName()) to colorToDrawable,
                Pair(StyleableAttributeFormat.Integer, Drawable::class.asTypeName()) to referenceToDrawable,
                Pair(StyleableAttributeFormat.Integer, Float::class.asTypeName()) to intToFloat,
                Pair(StyleableAttributeFormat.Integer, Long::class.asTypeName()) to intToLong,
                Pair(StyleableAttributeFormat.Integer, boolTypeName) to intToBool,
                Pair(StyleableAttributeFormat.Dimension, Float::class.asTypeName()) to intToFloat,
                Pair(StyleableAttributeFormat.Color, ColorStateList::class.asTypeName()) to colorToColorStateList,
                Pair(StyleableAttributeFormat.Integer, ColorStateList::class.asTypeName()) to colorToColorStateList,
                Pair(StyleableAttributeFormat.Enum, PorterDuff.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, PorterDuff.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, Display.Mode::class.asTypeName()) to enumToPorterDuffMode,
                Pair(StyleableAttributeFormat.Integer, ImageView.ScaleType::class.asTypeName()) to enumToScaleType,
                Pair(StyleableAttributeFormat.Enum, ImageView.ScaleType::class.asTypeName()) to enumToScaleType,
                Pair(StyleableAttributeFormat.Integer, TextUtils.TruncateAt::class.asTypeName()) to enumToTruncateAt,
                Pair(StyleableAttributeFormat.Enum, TextUtils.TruncateAt::class.asTypeName()) to enumToTruncateAt,
                Pair(StyleableAttributeFormat.Boolean, layoutTransitionTypeName) to booleanToLayoutTransition,
                Pair(StyleableAttributeFormat.Boolean, intTypeName) to boolToInt,
                Pair(StyleableAttributeFormat.Integer, InputFilter::class.asTypeName()) to intToInputFilter,
                Pair(StyleableAttributeFormat.Reference, Interpolator::class.asTypeName()) to referenceToInterpolator,
                Pair(StyleableAttributeFormat.Unspecified, Interpolator::class.asTypeName()) to referenceToInterpolator,
                Pair(StyleableAttributeFormat.Reference, motionSpecClassName) to referenceToMotionSpec,
                Pair(StyleableAttributeFormat.Unspecified, motionSpecClassName) to referenceToMotionSpec,
                Pair(StyleableAttributeFormat.String, ClassName.bestGuess("java.lang.String[]")) to anyToList
        )
        private val formatEnumClassName = FormatEnumGenerator.formatEnumClassName
    }



}