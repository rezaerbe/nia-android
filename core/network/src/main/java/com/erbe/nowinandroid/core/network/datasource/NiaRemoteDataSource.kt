package com.erbe.nowinandroid.core.network.datasource

import com.erbe.nowinandroid.core.network.model.ArticleResponse
import kotlinx.coroutines.flow.Flow

interface NiaRemoteDataSource {

    fun getArticles(): Flow<List<ArticleResponse>>
}