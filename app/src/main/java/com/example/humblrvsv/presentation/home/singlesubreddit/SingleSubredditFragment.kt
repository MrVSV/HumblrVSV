package com.example.humblrvsv.presentation.home.singlesubreddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.humblrvsv.databinding.FragmentSingleSubredditBinding
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.presentation.base.BaseFragment
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleSubredditFragment : BaseFragment<FragmentSingleSubredditBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentSingleSubredditBinding.inflate(inflater)

    private val viewModel by viewModels<SingleSubredditViewModel>()
    private val adapter by lazy {
        HomePagingAdapter { clickableView, item -> onClick(clickableView, item) }
    }
    private val args by navArgs<SingleSubredditFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSubredditInfo(args.subredditName)
        infoLoadingObserve()
        settingAdapter()
        observePagingData()
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.postList(args.subredditName).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow.collect { state ->
                if (state.refresh is LoadState.Loading || state.append is LoadState.Loading)
                    binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun observeSubredditInfo(subredditName: String){
        viewModel.getSubredditInfo(subredditName)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.subredditInfo.collect{ subreddit ->
                binding.subredditName.text = subreddit.namePrefixed
                binding.subscribers.text = "Subscribers: ${subreddit.subscribers}"
                binding.subredditDescription.text = subreddit.description
                binding.btnExpand.setOnClickListener {
                    when (binding.detailInfo.visibility) {
                        View.GONE -> binding.detailInfo.visibility = View.VISIBLE
                        View.VISIBLE -> binding.detailInfo.visibility = View.GONE
                        View.INVISIBLE -> {}
                    }
                }
                binding.btnSubscribe.setOnClickListener {
                    binding.btnSubscribe.isSelected =! binding.btnSubscribe.isSelected
                    Toast.makeText(requireContext(), "subscribed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun infoLoadingObserve(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadState.collect{loadState ->
                when(loadState){
                    com.example.humblrvsv.domain.tools.LoadState.START -> {
                        binding.info.isVisible = false
                        binding.progressBarTop.isVisible = false
                    }
                    com.example.humblrvsv.domain.tools.LoadState.LOADING -> {
                        binding.info.isVisible = false
                        binding.progressBarTop.isVisible = true
                    }
                    com.example.humblrvsv.domain.tools.LoadState.ERROR -> {
                        binding.info.isVisible = false
                        binding.progressBarTop.isVisible = false
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT).show()
                    }
                    com.example.humblrvsv.domain.tools.LoadState.SUCCESS -> {
                        binding.info.isVisible = true
                        binding.progressBarTop.isVisible = false
                    }
                }
            }
        }
    }

    private fun onClick(clickableView: ClickableView, item: Thing) {}
}