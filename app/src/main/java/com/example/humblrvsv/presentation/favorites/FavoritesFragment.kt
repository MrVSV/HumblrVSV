package com.example.humblrvsv.presentation.favorites

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.humblrvsv.*
import com.example.humblrvsv.databinding.FragmentFavoritesBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.setSelectedTabListener
import com.example.humblrvsv.presentation.base.BaseFragment
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentFavoritesBinding.inflate(inflater)

    private val viewModel by viewModels<FavoritesViewModel>()
    private val adapter by lazy {
        HomePagingAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        setTabLayout(createSharedPreference(SHARED_SELECTED_TAB_NAME))
        observePagingData(createSharedPreference(SHARED_PROFILE))
        setModel(createSharedPreference(SHARED_SELECTED_TAB_NAME))
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observePagingData(preferences: SharedPreferences) {
        val source = preferences.getString(SHARED_PROFILE_USER_NAME, "")
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.favoritesList(source).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setTabLayout(prefs: SharedPreferences) {
        val modelTabPosition = prefs.getInt(SHARED_SELECTED_TAB_SAVED_MODEL, 0)
        binding.toggleModel.selectTab(binding.toggleModel.getTabAt(modelTabPosition))
    }

    private fun setModel(prefs: SharedPreferences) {
        binding.toggleModel.setSelectedTabListener { position ->
            viewModel.setModel(position)
            prefs.edit()
                .putInt(SHARED_SELECTED_TAB_SAVED_MODEL, binding.toggleModel.selectedTabPosition)
                .apply()
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
            ClickableView.UP_VOTE -> Toast.makeText(requireContext(), "voteUp", Toast.LENGTH_SHORT)
                .show()
            ClickableView.DOWN_VOTE -> Toast.makeText(
                requireContext(),
                "voteDown",
                Toast.LENGTH_SHORT
            ).show()
            ClickableView.SAVE -> Toast.makeText(requireContext(), "post saved", Toast.LENGTH_SHORT)
                .show()
            ClickableView.PHOTO -> TODO()
            ClickableView.POST_TITLE -> TODO()
            ClickableView.COMMENT -> TODO()
            ClickableView.SUBREDDIT -> {
                item as Subreddit
                findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToSingleSubredditFragment(
                        item.namePrefixed
                    )
                )
            }
            ClickableView.SUBSCRIBE -> Toast.makeText(
                requireContext(),
                "subscribed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        Log.d("onDestroy", "favorites")
        super.onDestroy()
    }
}