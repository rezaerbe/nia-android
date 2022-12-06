package com.erbe.nowinandroid.core.design

import android.widget.ImageView
import coil.load
import coil.size.Scale
import coil.transform.Transformation

fun ImageView.loadImage(imageUrl: String?, transformation: Transformation) {
    this.load(imageUrl) {
        crossfade(true)
        transformations(transformation)
        placeholder(R.drawable.image)
        error(R.drawable.image)
        scale(Scale.FILL)
    }
}