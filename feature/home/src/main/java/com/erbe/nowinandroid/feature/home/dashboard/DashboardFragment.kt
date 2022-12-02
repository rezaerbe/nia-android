package com.erbe.nowinandroid.feature.home.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.base.click
import com.erbe.nowinandroid.feature.home.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInteraction()
    }

    private fun initInteraction() {
        binding.article.setOnClickListener(click {
            findNavController().navigate(
                DashboardFragmentDirections.actionDashboardFragmentToListArticleFragment()
            )
        })
    }
}