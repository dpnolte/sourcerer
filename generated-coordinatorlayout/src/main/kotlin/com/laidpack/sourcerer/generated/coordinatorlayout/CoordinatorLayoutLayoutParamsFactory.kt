package com.laidpack.sourcerer.generated.coordinatorlayout

import android.content.Context
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsFactory
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = CoordinatorLayoutLayoutParamsFactory.elementType,
        attributesClazz = CoordinatorLayoutLayoutParamsAttributes::class
)
open class CoordinatorLayoutLayoutParamsFactory<TLayoutParams : CoordinatorLayout.LayoutParams, TAttributes : CoordinatorLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is CoordinatorLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_behavior?.let {
                    val localLayoutBehavior = BehaviorUtils.createFromName(it, context)
                    if (behavior != localLayoutBehavior) {
                        behavior = localLayoutBehavior
                    }
                }
                attributes.layout_anchor?.let {
                    if (anchorId != it) {
                        anchorId = it
                    }
                }
                attributes.layout_keyline?.let {
                    if (it != keyline) {
                        keyline = it
                    }
                }
                if (attributes.layout_anchorGravity.hasInteger || attributes.layout_anchorGravity.hasFlags) {
                    val localLayoutAnchorGravity = when {
                        attributes.layout_anchorGravity.hasInteger -> attributes.layout_anchorGravity.integer
                        else -> attributes.layout_anchorGravity.flags
                    }
                    if (localLayoutAnchorGravity != anchorGravity) {
                        anchorGravity = localLayoutAnchorGravity
                    }
                }
                attributes.layout_insetEdge?.let {
                    if (it.value != insetEdge) {
                        insetEdge = it.value
                    }
                }
                attributes.layout_dodgeInsetEdges?.let {
                    val localLayoutDodgeInsetEdges = it.value
                    if (localLayoutDodgeInsetEdges != dodgeInsetEdges) {
                        dodgeInsetEdges = localLayoutDodgeInsetEdges
                    }
                }
                attributes.android_layout_gravity?.let {
                    if (it != gravity) {
                        gravity = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String =
                "androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : CoordinatorLayout.LayoutParams, reified TAttributes : CoordinatorLayoutLayoutParamsAttributes> invoke() = CoordinatorLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
