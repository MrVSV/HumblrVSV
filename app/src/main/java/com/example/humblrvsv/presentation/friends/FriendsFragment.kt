package com.example.humblrvsv.presentation.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.humblrvsv.databinding.FragmentFriendsBinding
import com.example.humblrvsv.domain.model.Friend
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.presentation.adapter.FriendsAdapter
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentFriendsBinding.inflate(inflater)

    private val viewModel by viewModels<FriendsViewModel>()

    private val adapter by lazy {
        FriendsAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        observeFriendsData()
        loadStateItemsObserve()
    }

    private fun onClick(clickableView: ClickableView, item: Friend) {}

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observeFriendsData() {
        viewModel.friends.onEach { friends ->
            adapter.setData(friends)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.LOADING_STAGE_1 ->
                        binding.progressBar.isVisible = true
                    LoadState.ERROR -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT).show()
                    }
                    else -> binding.progressBar.isVisible = false
                }
            }
        }
    }
}

