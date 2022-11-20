package com.erbe.nowinandroid.core.network.service

import com.erbe.nowinandroid.core.network.model.ArticleResponse
import com.erbe.nowinandroid.core.network.model.AuthorResponse
import com.erbe.nowinandroid.core.network.model.TopicResponse
import retrofit2.http.GET

interface NiaService {

    @GET("article")
    suspend fun getArticles(): List<ArticleResponse>

    @GET("author")
    suspend fun getAuthors(): List<AuthorResponse>

    @GET("topic")
    suspend fun getTopics(): List<TopicResponse>
}