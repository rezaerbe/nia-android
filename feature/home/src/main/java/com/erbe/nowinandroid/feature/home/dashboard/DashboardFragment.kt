package com.erbe.nowinandroid.feature.home.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.base.itemDivider
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.state.process
import com.erbe.nowinandroid.feature.home.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val dashboardViewModel: DashboardViewModel by viewModels()
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
        dashboardViewModel.articleUiState.launchAndCollectIn(viewLifecycleOwner) { articleUiState ->
            articleUiState.process(
                onLoading = {},
                onError = {},
                onSuccess = { data ->
                    articleAdapter.submitList(data)
                }
            )
        }
    }
}