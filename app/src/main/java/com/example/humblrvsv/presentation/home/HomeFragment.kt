package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseFragment
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.ClickableView.*
import com.example.humblrvsv.domain.tools.setSelectedTabListener
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        HomePagingAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        observePagingData()
        tableLayoutSelectedListener(binding.toggleModel,false)
        tableLayoutSelectedListener(binding.toggleSource,true)
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.thingList.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun tableLayoutSelectedListener(tabLayout: TabLayout, isSource: Boolean) {
        tabLayout.setSelectedTabListener {position ->
            viewModel.setQuery(position,isSource)
        }
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow.collect { state ->
                binding.progressBar.isVisible =
                    state.refresh is LoadState.Loading || state.append is LoadState.Loading
            }
        }
    }


    private fun onClick(clickableView: ClickableView, item: Thing) {
        when (clickableView) {
            UP_VOTE -> Toast.makeText(requireContext(), "voteUp", Toast.LENGTH_SHORT).show()
            DOWN_VOTE -> Toast.makeText(requireContext(), "voteDown", Toast.LENGTH_SHORT).show()
            SAVE -> Toast.makeText(requireContext(), "post saved", Toast.LENGTH_SHORT).show()
            PHOTO -> TODO()
            POST_TITLE -> TODO()
            COMMENT -> TODO()
            SUBREDDIT -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSingleSubredditFragment(
                        (item as Subreddit).namePrefixed
                    )
                )
            }
            SUBSCRIBE -> Toast.makeText(requireContext(), "subscribed", Toast.LENGTH_SHORT).show()
        }
    }
}


