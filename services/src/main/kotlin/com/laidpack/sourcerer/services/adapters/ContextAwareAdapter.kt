package com.laidpack.sourcerer.services.adapters

import android.content.Context
import java.lang.ref.WeakReference

interface ContextAwareAdapter {
    var context: WeakReference<Context>
}