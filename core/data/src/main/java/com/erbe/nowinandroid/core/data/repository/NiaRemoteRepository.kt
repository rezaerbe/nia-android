package com.erbe.nowinandroid.core.data.repository

import com.erbe.nowinandroid.core.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NiaRemoteRepository {

    fun getArticles(): Flow<List<Article>>
}