package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.card.MaterialCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class MaterialCardViewFactory<TView : MaterialCardView, TAttributes : MaterialCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "materialCardView"

    override fun createInstance(context: Context): View = MaterialCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(MaterialCardViewFactory<MaterialCardView, MaterialCardViewAttributes>())
        }

        inline operator fun <reified TView : MaterialCardView, reified TAttributes : MaterialCardViewAttributes> invoke() = MaterialCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
