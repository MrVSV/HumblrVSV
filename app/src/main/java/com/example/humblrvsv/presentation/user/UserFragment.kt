package com.example.humblrvsv.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.humblrvsv.databinding.FragmentUserBinding
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.model.User
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.tools.loadImage
import com.example.humblrvsv.presentation.adapter.ThingListAdapter
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentUserBinding.inflate(inflater)

    private val viewModel by viewModels<UserViewModel>()

    private val adapter by lazy {
        ThingListAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    private val args by navArgs<UserFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadUserData(args.userName)
        settingAdapter()
        observeUserInfo()
        observeUserContent()
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observeUserContent() {
        viewModel.content.onEach { content ->
            adapter.submitList(content)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeUserInfo(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userInfo.collect{user ->
                bindUserInfo(user)
            }
        }
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.LOADING_STAGE_1 -> {
                        binding.progressBar.isVisible = true
                        binding.progressBarTop.isVisible = true
                    }
                    LoadState.ERROR -> {
                        binding.progressBar.isVisible = false
                        binding.progressBarTop.isVisible = false
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        binding.progressBar.isVisible = false
                        binding.progressBarTop.isVisible = false
                    }
                }
            }
        }
    }

    private fun bindUserInfo(user: User){
        with(binding) {
            userAvatar.loadImage(user.snoovatarImg)
            userName.text = "${user.name}"
            userId.text = user.id
            karma.text = user.totalKarma.toString()
        }

    }
    private fun onClick(clickableView: ClickableView, item: Thing) {

    }


}