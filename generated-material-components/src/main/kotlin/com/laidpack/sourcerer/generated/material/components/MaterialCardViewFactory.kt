package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.card.MaterialCardView
import com.laidpack.sourcerer.generated.cardview.CardViewFactory
import java.lang.Class
import kotlin.String

open class MaterialCardViewFactory<TView : MaterialCardView, TAttributes : MaterialCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = MaterialCardView(context)

    companion object {
        const val elementType: String = "materialCardView"

        inline operator fun <reified TView : MaterialCardView, reified TAttributes : MaterialCardViewAttributes> invoke() = MaterialCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
