package com.erbe.nowinandroid.core.common.design

import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowMetricsCalculator

enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }

fun computeWindowSizeClasses(activity: AppCompatActivity): WindowSizeClass {
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(activity)

    val widthDp = metrics.bounds.width() /
            activity.resources.displayMetrics.density
    val widthWindowSizeClass = when {
        widthDp < 600f -> WindowSizeClass.COMPACT
        widthDp < 840f -> WindowSizeClass.MEDIUM
        else -> WindowSizeClass.EXPANDED
    }

    return widthWindowSizeClass
}