package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AppCompatButtonFactory<TView : AppCompatButton, TAttributes : AppCompatButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = AppCompatButton(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatButtonFactory<AppCompatButton, AppCompatButtonAttributes>())
        }

        inline operator fun <reified TView : AppCompatButton, reified TAttributes : AppCompatButtonAttributes> invoke() = AppCompatButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
