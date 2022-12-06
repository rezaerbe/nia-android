package com.erbe.nowinandroid.feature.home.article

import android.content.Context
import android.view.View
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.common.base.click
import com.erbe.nowinandroid.core.common.design.dp
import com.erbe.nowinandroid.core.design.loadImage
import com.erbe.nowinandroid.feature.home.databinding.ItemArticleBinding
import com.google.android.material.chip.Chip

class ArticleAdapter(
    private val onArticleClick: (ArticleUiState) -> Unit = {}
) : BaseAdapter<ArticleUiState, ItemArticleBinding>(ItemArticleBinding::inflate) {

    override fun onItemBind(): (ArticleUiState, ItemArticleBinding, View) -> Unit =
        { item, binding, itemView ->
            binding.authorImage.loadImage(item.author.imageUrl, CircleCropTransformation())
            binding.authorName.text = item.author.name

            binding.articleTitle.text = item.article.description
            binding.articleDateTime.text = itemView.resources.getString(
                com.erbe.nowinandroid.feature.home.R.string.article_date_time,
                item.article.publishDate,
                item.article.readTime
            )
            binding.articleImage.loadImage(
                item.article.imageUrl,
                RoundedCornersTransformation(8f.dp)
            )

            item.listTopic?.forEach { topic ->
                topic?.let {
                    val chip = customChip(topic.name, itemView.context)
                    binding.topicList.addView(chip)
                }
            }

            binding.itemArticle.setOnClickListener(click {
                onArticleClick(item)
            })
        }
}

private fun customChip(label: String, context: Context): Chip {
    return Chip(context).apply {
        id = View.generateViewId()
        text = label
        textSize = 12f
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        textStartPadding = 0f
        textEndPadding = 0f
        setEnsureMinTouchTargetSize(false)
    }
}