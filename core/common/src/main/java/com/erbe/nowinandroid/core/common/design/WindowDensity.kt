package com.erbe.nowinandroid.core.common.design

import android.content.res.Resources

val Float.dp: Float
    get() = this * Resources.getSystem().displayMetrics.density