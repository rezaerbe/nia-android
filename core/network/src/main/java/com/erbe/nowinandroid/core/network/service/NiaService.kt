package com.erbe.nowinandroid.core.network.service

import com.erbe.nowinandroid.core.network.model.ArticleResponse
import retrofit2.http.GET

interface NiaService {

    @GET("article")
    suspend fun getArticles(): List<ArticleResponse>
}