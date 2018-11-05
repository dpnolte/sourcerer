package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.chip.Chip
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.appcompat.AppCompatCheckBoxFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = ChipFactory.elementType,
        attributesClazz = ChipAttributes::class
)
open class ChipFactory<TView : Chip, TAttributes : ChipAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatCheckBoxFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Chip(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Chip) {
            view.apply {
                attributes.chipIconTint?.let {
                    if (id != it) {
                        setChipIconTintResource(it)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "chip"

        inline operator fun <reified TView : Chip, reified TAttributes : ChipAttributes> invoke() = ChipFactory(TView::class.java, TAttributes::class.java)
    }
}
