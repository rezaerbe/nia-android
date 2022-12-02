package com.erbe.nowinandroid.feature.home.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.base.itemDivider
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.state.process
import com.erbe.nowinandroid.core.common.state.state
import com.erbe.nowinandroid.feature.home.databinding.FragmentListArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListArticleFragment :
    BaseFragment<FragmentListArticleBinding>(FragmentListArticleBinding::inflate) {

    private val listArticleViewModel: ListArticleViewModel by viewModels()
    private lateinit var articleAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObservation()
    }

    private fun initAdapter() {
        articleAdapter = ArticleAdapter()
        binding.recyclerView.adapter = articleAdapter
        binding.recyclerView.addItemDecoration(itemDivider(requireContext()))
    }

    private fun initObservation() {
        listArticleViewModel.articleUiState.launchAndCollectIn(viewLifecycleOwner) { articleUiState ->
            articleUiState.state(
                binding.progressBar, binding.textError, binding.recyclerView
            )
            articleUiState.process(
                onError = { exception ->
                    binding.textError.text = exception?.message ?: "Error"
                },
                onSuccess = { data ->
                    articleAdapter.submitList(data)
                }
            )
        }
    }
}