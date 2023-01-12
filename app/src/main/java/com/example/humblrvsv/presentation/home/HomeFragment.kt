package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.presentation.BaseFragment
import com.example.humblrvsv.presentation.home.adapter.SubredditPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        SubredditPagingAdapter { item -> onClick(item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.subList().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun onClick(item: Subreddit) {

    }
}