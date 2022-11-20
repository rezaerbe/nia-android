package com.erbe.nowinandroid.feature.home.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.state.DataState
import com.erbe.nowinandroid.feature.home.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservation()
    }

    private fun initObservation() {
        dashboardViewModel.articleUiState.launchAndCollectIn(viewLifecycleOwner) { articleUiState ->
            when (articleUiState) {
                is DataState.Loading -> Log.d("TAG", "Loading")
                is DataState.Error -> Log.d("TAG", articleUiState.exception.toString())
                is DataState.Success -> Log.d("TAG", articleUiState.data.toString())
            }
        }
    }
}