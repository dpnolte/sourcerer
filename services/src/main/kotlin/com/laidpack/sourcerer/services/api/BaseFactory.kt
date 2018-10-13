package com.laidpack.sourcerer.services.api

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup

abstract class BaseFactory<TInstance, TAttributes>(
        private val instanceType: Class<TInstance>,
        private val attributesType: Class<TAttributes>
) : LayoutElement.Factory {
    abstract override val elementType: String

    protected fun getTypedInstance(instance: Any): TInstance {
        return instanceType.cast(instance)
                ?: throw ClassCastException("Attributes could not be cast.\nExpected: $attributesType, actual: ${instance::class.java.canonicalName}")
    }
    protected fun getTypedAttributes(attrs: IAttributes): TAttributes {
        return attributesType.cast(attrs)
                ?: throw ClassCastException("Attributes could not be cast.\nExpected: $attributesType, actual: ${attrs::class.java.canonicalName}")
    }
}

abstract class BaseViewFactory<TView : View, TAttributes: IAttributes>(
        viewType: Class<TView>,
        attributesType : Class<TAttributes>
) : BaseFactory<TView, TAttributes>(viewType, attributesType), LayoutElement.ViewFactory {
    override fun create(context: Context, attributes: IAttributes, theme: Int): View {
        val ctx = wrapThemeContextIfNeeded(context, theme)
        val view = createInstance(ctx)
        init(
                view,
                ctx,
                getTypedAttributes(attributes)
        )
        return view
    }
    override fun update(view: View, context: Context, attributes: IAttributes) {
        init(
                view,
                context,
                getTypedAttributes(attributes)
        )
    }

    protected abstract fun createInstance(context: Context): View
    protected abstract fun init(view: View, context: Context, attributes: TAttributes)

    private class ExtendedContextThemeWrapper(base: Context?, val theme: Int) : ContextThemeWrapper(base, theme)
    companion object {
        fun wrapThemeContextIfNeeded(ctx: Context, theme: Int): Context {
            return if (theme != 0 && (ctx !is ExtendedContextThemeWrapper || ctx.theme != theme)) {
                // If the context isn't a ContextThemeWrapper, or it is but does not have
                // the same theme as we need, wrap it in a new wrapper
                ExtendedContextThemeWrapper(ctx, theme)
            } else {
                ctx
            }
        }
    }
}


abstract class BaseLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes: IAttributes>(
        layoutParamsType: Class<TLayoutParams>,
        attributesType : Class<TAttributes>
) : BaseFactory<TLayoutParams, TAttributes>(layoutParamsType, attributesType), LayoutElement.LayoutParamsFactory {

    override fun create(context: Context, attributes: IAttributes, theme: Int): ViewGroup.LayoutParams {
        val layoutParams = createInstance(context)
        init(
                layoutParams,
                context,
                getTypedAttributes(attributes)
        )
        return layoutParams
    }

    override fun update(layoutParams: ViewGroup.LayoutParams, context: Context, attributes: IAttributes) {
        init(
                layoutParams,
                context,
                getTypedAttributes(attributes)
        )
    }

    protected abstract fun createInstance(context: Context): ViewGroup.LayoutParams
    protected abstract fun init(layoutParams: ViewGroup.LayoutParams, context: Context, attributes: TAttributes)
}