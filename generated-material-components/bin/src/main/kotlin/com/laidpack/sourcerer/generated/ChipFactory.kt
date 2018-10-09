package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.chip.Chip
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ChipFactory<TView : Chip, TAttributes : ChipAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "chip"

    override fun createInstance(context: Context): View = Chip(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Chip) {
            view.init {
                attributes.chipIconTint?.let {
                    if (id != it) {
                        setChipIconTintResource(it)
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ChipFactory<Chip, ChipAttributes>())
        }

        inline operator fun <reified TView : Chip, reified TAttributes : ChipAttributes> invoke() = ChipFactory(TView::class.java, TAttributes::class.java)
    }
}
