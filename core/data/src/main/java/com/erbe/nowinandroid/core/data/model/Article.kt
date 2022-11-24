package com.erbe.nowinandroid.core.data.model

import com.erbe.nowinandroid.core.data.util.formatPublishDate
import com.erbe.nowinandroid.core.data.util.formatReadTime
import com.erbe.nowinandroid.core.network.model.ArticleResponse

data class Article(
    val id: Int?,
    val title: String?,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val publishDate: String?,
    val readTime: String?,
    val author: Int?,
    val topics: List<Int?>?,
    val url: String?
) {

    val articleNull = Article(
        id = null,
        title = null,
        description = null,
        content = null,
        imageUrl = null,
        publishDate = null,
        readTime = null,
        author = null,
        topics = listOf(),
        url = null
    )
}

fun ArticleResponse.asDomain() = Article(
    id,
    title,
    description,
    content,
    imageUrl,
    formatPublishDate(publishDate),
    formatReadTime(readTime),
    author,
    topics,
    url
)