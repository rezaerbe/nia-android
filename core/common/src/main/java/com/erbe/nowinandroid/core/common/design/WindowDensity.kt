package com.erbe.nowinandroid.core.common.design

import android.content.Context

fun Float.toDensity(context: Context): Float {
    return this * context.resources.displayMetrics.density
}