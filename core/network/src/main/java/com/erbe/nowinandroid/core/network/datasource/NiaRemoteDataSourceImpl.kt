package com.erbe.nowinandroid.core.network.datasource

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher.IO
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.network.model.ArticleResponse
import com.erbe.nowinandroid.core.network.model.AuthorResponse
import com.erbe.nowinandroid.core.network.model.TopicResponse
import com.erbe.nowinandroid.core.network.service.NiaService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NiaRemoteDataSourceImpl @Inject constructor(
    private val niaService: NiaService,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : NiaRemoteDataSource {

    override fun getArticles(): Flow<List<ArticleResponse>> =
        flow {
            val articles = niaService.getArticles()
            emit(articles)
        }.flowOn(ioDispatcher)

    override fun getAuthors(): Flow<List<AuthorResponse>> =
        flow {
            val authors = niaService.getAuthors()
            emit(authors)
        }.flowOn(ioDispatcher)

    override fun getTopics(): Flow<List<TopicResponse>> =
        flow {
            val topics = niaService.getTopics()
            emit(topics)
        }.flowOn(ioDispatcher)
}