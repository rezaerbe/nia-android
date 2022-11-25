package com.erbe.nowinandroid.feature.home.dashboard

import android.content.Context
import android.view.View
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.design.R
import com.erbe.nowinandroid.feature.home.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ArticleAdapter : BaseAdapter<ArticleUiState, ItemArticleBinding>(
    ItemArticleBinding::inflate,
    onItemBind = { item, binding, itemView ->
        item.author?.let { author ->
            binding.authorImage.load(author.imageUrl) {
                loadImage(CircleCropTransformation())
            }
            binding.authorName.text = author.name
        }

        item.article?.let { article ->
            binding.articleTitle.text = article.title
            binding.articleDateTime.text = StringBuilder()
                .append(article.publishDate)
                .append("  \u00b7  ${article.readTime}")
            binding.articleImage.load(article.imageUrl) {
                loadImage(RoundedCornersTransformation(8f.toDensity(itemView.context)))
            }
        }

        item.listTopic?.forEach { topic ->
            topic?.let {
                binding.topicList.addChip(itemView.context, it.name)
            }
        }
    }
)

private fun Float.toDensity(context: Context): Float {
    return this * context.resources.displayMetrics.density
}

private fun ImageRequest.Builder.loadImage(transformation: Transformation) {
    crossfade(true)
    transformations(transformation)
    placeholder(R.drawable.image)
    error(R.drawable.image)
    scale(Scale.FILL)
}

private fun ChipGroup.addChip(context: Context, label: String) {
    Chip(context).apply {
        id = View.generateViewId()
        text = label
        textSize = 12f
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        textStartPadding = 0f
        textEndPadding = 0f
        setEnsureMinTouchTargetSize(false)
        chipSpacingVertical = 20
        chipSpacingHorizontal = 20
        chipMinHeight = 0f
        height = 80
        addView(this)
    }
}