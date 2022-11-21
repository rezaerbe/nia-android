package com.erbe.nowinandroid.feature.home.dashboard

import android.view.View
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.design.R
import com.erbe.nowinandroid.feature.home.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip

class ArticleAdapter : BaseAdapter<ArticleUiState, ItemArticleBinding>(
    ItemArticleBinding::inflate,
    onItemBind = { item, binding, itemView ->
        item.author?.let { author ->
            author.imageUrl?.let {
                binding.authorImage.load(it) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.image)
                    error(R.drawable.image)
                    scale(Scale.FILL)
                }
            }
            author.name?.let {
                binding.authorName.text = it
            }
        }

        item.article?.let { article ->
            article.title?.let {
                binding.articleTitle.text = it
            }
            val publishDateTime = StringBuilder()
            article.publishDate?.let {
                publishDateTime.append(it)
            }
            article.readTime?.let {
                publishDateTime.append("  \u00b7  $it")
            }
            binding.articleDateTime.text = publishDateTime
            article.imageUrl?.let {
                binding.articleImage.load(it) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(8f.toDensity(itemView)))
                    placeholder(R.drawable.image)
                    error(R.drawable.image)
                    scale(Scale.FILL)
                }
            }
        }

        item.listTopic?.forEach { topic ->
            topic?.let {
                val chip = Chip(itemView.context).apply {
                    text = it.name
                    textSize = 12f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textStartPadding = 0f
                    textEndPadding = 0f
                    chipMinHeight = 24f.toDensity(itemView)
                }
                binding.topicList.addView(chip)
            }
        }
    }
)

private fun Float.toDensity(view: View): Float {
    return this * view.resources.displayMetrics.density
}