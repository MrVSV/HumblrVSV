package com.example.humblrvsv.presentation.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.humblrvsv.SHARED_SELECTED_TAB_MODEL
import com.example.humblrvsv.SHARED_SELECTED_TAB_NAME
import com.example.humblrvsv.SHARED_SELECTED_TAB_SOURCE
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.ClickableView.*
import com.example.humblrvsv.domain.tools.setSelectedTabListener
import com.example.humblrvsv.presentation.adapter.ThingPagingAdapter
import com.example.humblrvsv.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        ThingPagingAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = createSharedPreference(SHARED_SELECTED_TAB_NAME)

        settingAdapter()
        setTabLayout(prefs)
        observePagingData()
        tabLayoutSelectedListener(binding.toggleModel, false, prefs)
        tabLayoutSelectedListener(binding.toggleSource, true, prefs)
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

    private fun setTabLayout(prefs: SharedPreferences){
        val modelTabPosition = prefs.getInt(SHARED_SELECTED_TAB_MODEL, 0)
        val sourceTabPosition = prefs.getInt(SHARED_SELECTED_TAB_SOURCE, 0)
        binding.toggleModel.selectTab(binding.toggleModel.getTabAt(modelTabPosition))
        binding.toggleSource.selectTab(binding.toggleSource.getTabAt(sourceTabPosition))
    }

    private fun tabLayoutSelectedListener(
        tabLayout: TabLayout,
        isSource: Boolean,
        prefs: SharedPreferences
    ) {
        tabLayout.setSelectedTabListener { position ->
            viewModel.setQuery(position, isSource)
            if (isSource) prefs.edit()
                .putInt(SHARED_SELECTED_TAB_SOURCE, binding.toggleSource.selectedTabPosition).apply()
            else prefs.edit()
                .putInt(SHARED_SELECTED_TAB_MODEL, binding.toggleModel.selectedTabPosition).apply()
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
            UP_VOTE -> viewModel.voteUp(item as Post)
            DOWN_VOTE -> viewModel.voteDown(item as Post)
            SAVE -> Toast.makeText(requireContext(), "post saved", Toast.LENGTH_SHORT).show()
            USER_C -> TODO()
            POST_TITLE -> {
                item as Post
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSinglePostFragment(item.id))
            }
            COMMENT -> {
                item as Post
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSinglePostFragment(item.id))
            }
            SUBREDDIT -> {
                item as Subreddit
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSingleSubredditFragment(
                        item.namePrefixed
                    )
                )
            }
            SUBSCRIBE -> Toast.makeText(requireContext(), "subscribed", Toast.LENGTH_SHORT).show()
            USER -> {
                item as Post
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToUserFragment(item.author)
                )
            }
            SHARE -> {
                item as Post
                shareLinkOnPhoto(item.url)
            }
        }
    }
    private fun shareLinkOnPhoto(url: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(sharingIntent, "Share"))
    }
}


