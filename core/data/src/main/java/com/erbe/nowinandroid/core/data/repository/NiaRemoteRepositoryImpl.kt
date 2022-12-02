package com.erbe.nowinandroid.core.data.repository

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher.Default
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.data.model.Article
import com.erbe.nowinandroid.core.data.model.Author
import com.erbe.nowinandroid.core.data.model.Topic
import com.erbe.nowinandroid.core.data.model.asDomain
import com.erbe.nowinandroid.core.network.datasource.NiaRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NiaRemoteRepositoryImpl @Inject constructor(
    private val niaRemoteDataSource: NiaRemoteDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher
) : NiaRemoteRepository {

    override fun getArticles(): Flow<List<Article>> =
        niaRemoteDataSource.getArticles().map { flowArticles ->
            flowArticles.mapNotNull { articles ->
                articles.asDomain()
            }
        }.flowOn(defaultDispatcher)

    override fun getAuthors(): Flow<List<Author>> =
        niaRemoteDataSource.getAuthors().map { flowAuthors ->
            flowAuthors.mapNotNull { authors ->
                authors.asDomain()
            }
        }.flowOn(defaultDispatcher)

    override fun getTopics(): Flow<List<Topic>> =
        niaRemoteDataSource.getTopics().map { flowTopics ->
            flowTopics.mapNotNull { topics ->
                topics.asDomain()
            }
        }.flowOn(defaultDispatcher)
}