package com.erbe.nowinandroid.core.design

import android.content.Context
import android.widget.ImageView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.erbe.nowinandroid.core.common.design.toDensity

fun ImageView.loadImage(imageUrl: String?, transformation: Transformation) {
    this.load(imageUrl) {
        crossfade(true)
        transformations(transformation)
        placeholder(R.drawable.image)
        error(R.drawable.image)
        scale(Scale.FILL)
    }
}

fun circleTransformation(): Transformation {
    return CircleCropTransformation()
}

fun roundedTransformation(radius: Float, context: Context): Transformation {
    return RoundedCornersTransformation(radius.toDensity(context))
}