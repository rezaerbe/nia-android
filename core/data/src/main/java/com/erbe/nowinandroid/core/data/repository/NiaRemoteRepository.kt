package com.erbe.nowinandroid.core.data.repository

import com.erbe.nowinandroid.core.data.model.Article
import com.erbe.nowinandroid.core.data.model.Author
import com.erbe.nowinandroid.core.data.model.Topic
import kotlinx.coroutines.flow.Flow

interface NiaRemoteRepository {

    fun getArticles(): Flow<List<Article>>
    fun getAuthors(): Flow<List<Author>>
    fun getTopics(): Flow<List<Topic>>
}