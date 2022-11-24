package com.erbe.nowinandroid.feature.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbe.nowinandroid.core.common.state.DataState
import com.erbe.nowinandroid.core.common.state.asDataState
import com.erbe.nowinandroid.core.data.model.Article
import com.erbe.nowinandroid.core.data.model.Author
import com.erbe.nowinandroid.core.data.model.Topic
import com.erbe.nowinandroid.core.data.repository.NiaRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    niaRemoteRepository: NiaRemoteRepository
) : ViewModel() {

    private var _articleUiState =
        MutableStateFlow<DataState<List<ArticleUiState>>>(DataState.Loading)
    val articleUiState = _articleUiState.asStateFlow()

    private val articleCombine: Flow<List<ArticleUiState>> =
        combine(
            niaRemoteRepository.getArticles(),
            niaRemoteRepository.getAuthors(),
            niaRemoteRepository.getTopics()
        ) { articles, authors, topics ->
            articles.map { article ->
                if (article == article.articleNull) {

                }
                ArticleUiState(
                    article = article,
                    author = article.author?.let { author ->
                        authors.find { it.id == author }
                    },
                    listTopic = article.topics?.map { topic ->
                        topics.find { it.id == topic }
                    }
                )
            }
        }

    init {
        getArticleUiState()
    }

    private fun getArticleUiState() {
        viewModelScope.launch {
            articleCombine.asDataState().collect { articleCombine ->
                _articleUiState.value = articleCombine
            }
        }
    }
}

data class ArticleUiState(
    val article: Article?,
    val author: Author?,
    val listTopic: List<Topic?>?
)