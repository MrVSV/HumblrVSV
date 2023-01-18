package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.humblrvsv.R
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseFragment
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.ClickableView.*
import com.example.humblrvsv.domain.tools.setSelectedTabListener
import dagger.hilt.android.AndroidEntryPoint

private const val POSTS = "posts"
private const val SUBREDDITS = "subreddits"
private const val POPULAR = "popular"
private const val NEW = "new"

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
//        if (savedInstanceState != null) {
//            binding.tabPosts.isSelected = savedInstanceState.getBoolean(POSTS)
//            binding.tabSubreddits.isSelected = savedInstanceState.getBoolean(SUBREDDITS)
//            binding.tabPopular.isSelected = savedInstanceState.getBoolean(POPULAR)
//            binding.tabNew.isSelected = savedInstanceState.getBoolean(NEW)
//        }


        settingAdapter()
        observePagingData()
        setModel()
        setSource()
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

    private fun setModel() {
        binding.toggleModel.setSelectedTabListener { position ->
            when (position) {
                0 -> viewModel.setModel(Listing.POST) { adapter.refresh() }
                1 -> viewModel.setModel(Listing.SUBREDDIT) { adapter.refresh() }
            }
        }
    }

    private fun setSource() {
        binding.toggleSource.setSelectedTabListener { position ->
            when (position) {
                0 -> viewModel.setSource("") { adapter.refresh() }
                1 -> viewModel.setSource("new") { adapter.refresh() }
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


    private fun onClick(clickableView: ClickableView, item: Thing) {
        when (clickableView) {
            UP_VOTE -> Toast.makeText(requireContext(), "voteUp", Toast.LENGTH_SHORT).show()
            DOWN_VOTE -> Toast.makeText(requireContext(), "voteDown", Toast.LENGTH_SHORT).show()
            SAVE -> Toast.makeText(requireContext(), "post saved", Toast.LENGTH_SHORT).show()
            PHOTO -> TODO()
            POST_TITLE -> TODO()
            COMMENT -> TODO()
            SUBREDDIT -> {
                item as Subreddit
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSingleSubredditFragment(
                        item.namePrefixed
                    )
                )
            }
            SUBSCRIBE -> Toast.makeText(requireContext(), "subscribed", Toast.LENGTH_SHORT).show()
        }
    }
}


