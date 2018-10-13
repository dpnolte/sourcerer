// do not edit, auto-generated
package com.laidpack.sourcerer.services

import com.laidpack.sourcerer.services.api.Bootstrapper
import java.lang.Class
import java.lang.ClassNotFoundException
import kotlin.String

open class ServicesBootstrapper {
    open fun bootstrap(serializer: SerializerComponent, inflater: InflaterComponent) {
        bootstrapGeneratedModules(serializer, inflater)
    }

    protected fun bootstrapGeneratedModules(serializer: SerializerComponent, inflater: InflaterComponent) {
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.GeneratedBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.appcompat.GeneratedAppcompatBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.car.GeneratedCarBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.cardview.GeneratedCardviewBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.coordinatorlayout.GeneratedCoordinatorlayoutBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.core.GeneratedCoreBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.drawerlayout.GeneratedDrawerlayoutBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.emoji.GeneratedEmojiBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.emoji.appcompat.GeneratedEmojiAppcompatBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.gridlayout.GeneratedGridlayoutBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.leanback.GeneratedLeanbackBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.material.components.GeneratedMaterialComponentsBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.media.widget.GeneratedMediaWidgetBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.recyclerview.GeneratedRecyclerviewBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.slice.view.GeneratedSliceViewBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.slidingpanelayout.GeneratedSlidingpanelayoutBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.swiperefreshlayout.GeneratedSwiperefreshlayoutBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.viewpager.GeneratedViewpagerBootstrapper")
        bootstrapModule(serializer, inflater, "com.laidpack.sourcerer.generated.wear.GeneratedWearBootstrapper")
    }

    protected fun bootstrapModule(
        serializer: SerializerComponent,
        inflater: InflaterComponent,
        classNameAsString: String
    ) {
        try {
            val bootstrapper = Class.forName(classNameAsString).newInstance() as Bootstrapper
            bootstrapper.bootstrap(serializer, inflater)
        }
        catch (e: ClassNotFoundException) {
            // module not included as dependency
        }
    }
}
