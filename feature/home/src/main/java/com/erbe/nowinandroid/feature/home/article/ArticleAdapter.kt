package com.erbe.nowinandroid.feature.home.article

import android.content.Context
import android.view.View
import android.widget.ImageView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.common.design.toDensity
import com.erbe.nowinandroid.feature.home.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ArticleAdapter : BaseAdapter<ArticleUiState, ItemArticleBinding>(
    ItemArticleBinding::inflate,
    onItemBind = { item, binding, itemView ->
        binding.authorImage.loadImage(item.author.imageUrl, CircleCropTransformation())
        binding.authorName.text = item.author.name

        binding.articleTitle.text = item.article.title
        binding.articleDateTime.text = itemView.resources.getString(
            com.erbe.nowinandroid.feature.home.R.string.article_date_time,
            item.article.publishDate,
            item.article.readTime
        )
        binding.articleImage.loadImage(
            item.article.imageUrl,
            RoundedCornersTransformation(8f.toDensity(itemView.context))
        )

        item.listTopic?.forEach { topic ->
            topic?.let {
                binding.topicList.addChip(itemView.context, it.name)
            }
        }
    }
)

private fun ImageView.loadImage(imageUrl: String?, transformation: Transformation) {
    this.load(imageUrl) {
        crossfade(true)
        transformations(transformation)
        placeholder(com.erbe.nowinandroid.core.design.R.drawable.image)
        error(com.erbe.nowinandroid.core.design.R.drawable.image)
        scale(Scale.FILL)
    }
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
        setChipSpacing(0)
        chipSpacingVertical = 20
        chipSpacingHorizontal = 20
        addView(this)
    }
}