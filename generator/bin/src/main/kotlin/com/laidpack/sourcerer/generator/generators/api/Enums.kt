package com.laidpack.sourcerer.generator.generators.api

import android.content.Context
import android.graphics.PorterDuff
import android.text.TextUtils
import android.view.animation.*
import android.widget.ImageView


fun Int.toPorterDuffMode(): PorterDuff.Mode {
    when (this) {
        0 -> return PorterDuff.Mode.CLEAR
        1 -> return PorterDuff.Mode.SRC
        2 -> return PorterDuff.Mode.DST
        3 -> return PorterDuff.Mode.SRC_OVER
        4 -> return PorterDuff.Mode.DST_OVER
        5 -> return PorterDuff.Mode.SRC_IN
        6 -> return PorterDuff.Mode.DST_IN
        7 -> return PorterDuff.Mode.SRC_OUT
        8 -> return PorterDuff.Mode.DST_OUT
        9 -> return PorterDuff.Mode.SRC_ATOP
        10 -> return PorterDuff.Mode.DST_ATOP
        11 -> return PorterDuff.Mode.XOR
        16 -> return PorterDuff.Mode.DARKEN
        17 -> return PorterDuff.Mode.LIGHTEN
        13 -> return PorterDuff.Mode.MULTIPLY
        14 -> return PorterDuff.Mode.SCREEN
        12 -> return PorterDuff.Mode.ADD
        15 -> return PorterDuff.Mode.OVERLAY
        else -> throw IllegalStateException("PorterDuff.Mode has no enum value of $this")
    }
}

fun Int.toScaleType(): ImageView.ScaleType {
    return when(this) {
        0 -> ImageView.ScaleType.MATRIX
        1 -> ImageView.ScaleType.FIT_XY
        2 -> ImageView.ScaleType.FIT_START
        3 -> ImageView.ScaleType.FIT_CENTER
        4 -> ImageView.ScaleType.FIT_END
        5 -> ImageView.ScaleType.CENTER
        6 -> ImageView.ScaleType.CENTER_CROP
        7 -> ImageView.ScaleType.CENTER_INSIDE
        else -> throw IllegalStateException("ImageView.ScaleType has no enum value of $this")
    }
}

fun Int.toTruncateAt(): TextUtils.TruncateAt {
    return when(this) {
        0 -> TextUtils.TruncateAt.START
        1 -> TextUtils.TruncateAt.MIDDLE
        2 -> TextUtils.TruncateAt.END
        3 -> TextUtils.TruncateAt.MARQUEE
        else -> throw IllegalStateException("TextUtils.TruncateAt has no enum value of $this")
    }
}
