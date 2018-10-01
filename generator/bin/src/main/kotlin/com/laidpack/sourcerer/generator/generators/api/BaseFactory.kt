package com.laidpack.sourcerer.generator.generators.api

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup


inline fun <T: View> T.init (init: T.() -> Unit) {
    this.init()
}
inline fun <T: ViewGroup.LayoutParams> T.init (init: T.() -> Unit) {
    this.init()
}

inline fun <reified TView: View> createInstance(context: Context, provider: (Context) -> View): TView {
    return provider(context) as TView
}

abstract class BaseViewFactory<TView : View, TAttributes> : ViewFactoryComponent<TView, TAttributes> {
    override fun create(context: Context, attributes: TAttributes, theme: Int): View {
        val ctx = wrapThemeContextIfNeeded(context, theme)
        val view = createInstance(ctx)
        init(view, ctx, attributes)
        return view
    }

    override fun update(view: TView, context: Context, attributes: TAttributes) {
        init(view, context, attributes)
    }

    protected abstract fun createInstance(context: Context): View
    protected abstract fun init(view: View, context: Context, attributes: TAttributes)
    abstract override val elementName: String

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


abstract class BaseLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes> : LayoutParamsFactoryComponent<TLayoutParams, TAttributes> {
    override fun create(context: Context, attributes: TAttributes, theme: Int): ViewGroup.LayoutParams {
        val layoutParams = createInstance(context)
        init(layoutParams, context, attributes)
        return layoutParams
    }

    override fun update(layoutParams: TLayoutParams, context: Context, attributes: TAttributes) {
        init(layoutParams, context, attributes)
    }

    protected abstract fun createInstance(context: Context): ViewGroup.LayoutParams
    protected abstract fun init(layoutParams: ViewGroup.LayoutParams, context: Context, attributes: TAttributes)
    abstract override val elementName: String
}