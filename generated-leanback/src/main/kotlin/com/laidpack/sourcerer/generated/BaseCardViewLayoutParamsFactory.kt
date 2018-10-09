package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BaseCardViewLayoutParamsFactory<TLayoutParams : BaseCardView.LayoutParams, TAttributes : BaseCardViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.leanback.widget.BaseCardView.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = BaseCardView.LayoutParams(BaseCardView.LayoutParams.MATCH_PARENT, BaseCardView.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(BaseCardViewLayoutParamsFactory<BaseCardView.LayoutParams, BaseCardViewLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : BaseCardView.LayoutParams, reified TAttributes : BaseCardViewLayoutParamsAttributes> invoke() = BaseCardViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
