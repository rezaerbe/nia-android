package com.erbe.nowinandroid.core.network.datasource

import com.erbe.nowinandroid.core.network.model.ArticleResponse
import com.erbe.nowinandroid.core.network.model.AuthorResponse
import com.erbe.nowinandroid.core.network.model.TopicResponse
import kotlinx.coroutines.flow.Flow

interface NiaRemoteDataSource {

    fun getArticles(): Flow<List<ArticleResponse>>
    fun getAuthors(): Flow<List<AuthorResponse>>
    fun getTopics(): Flow<List<TopicResponse>>
}