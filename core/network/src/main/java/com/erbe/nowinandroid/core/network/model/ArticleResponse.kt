package com.erbe.nowinandroid.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "content")
    val content: String?,
    @field:Json(name = "imageUrl")
    val imageUrl: String?,
    @field:Json(name = "publishDate")
    val publishDate: String?,
    @field:Json(name = "readTime")
    val readTime: Int?,
    @field:Json(name = "author")
    val author: Int?,
    @field:Json(name = "topics")
    val topics: List<Int?>?,
    @field:Json(name = "url")
    val url: String?
)