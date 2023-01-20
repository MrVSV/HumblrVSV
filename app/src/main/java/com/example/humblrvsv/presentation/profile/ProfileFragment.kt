package com.example.humblrvsv.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.FragmentProfileBinding
import com.example.humblrvsv.domain.model.Profile
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.tools.loadImage
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentProfileBinding.inflate(inflater)

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUserInfo()
        infoLoadingObserve()

    }

    private fun observeUserInfo() {
        viewModel.getUserInfo()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userFlow.collect { user -> bindUserInfo(user) }
        }
    }

    private fun infoLoadingObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.START -> {
                        setLoadState(
                            textIsVisible = false,
                            progressIsVisible = false,
                            buttonIsEnabled = false
                        )
                    }
                    LoadState.LOADING_STAGE_1 -> {
                        setLoadState(
                            textIsVisible = false,
                            progressIsVisible = true,
                            buttonIsEnabled = false
                        )
                    }
                    LoadState.ERROR -> {
                        setLoadState(
                            textIsVisible = false,
                            progressIsVisible = false,
                            buttonIsEnabled = false
                        )
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT).show()
                    }
                    LoadState.SUCCESS -> {
                        setLoadState(
                            textIsVisible = true,
                            progressIsVisible = false,
                            buttonIsEnabled = true
                        )
                    }
                    LoadState.LOADING_STAGE_2 -> {}
                }
            }
        }
    }

    private fun bindUserInfo(user: Profile) {
        with(binding) {
            userId.text = getString(R.string.user_id, user.id)
            userAvatar.loadImage(user.avatarFull)
            userName.text = user.name
            karma.text = getString(R.string.karma, user.totalKarma)
            followers.text = getString(R.string.followers, user.subscribers)
            btnFriends.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToFriendsFragment()
                findNavController().navigate(action)
            }
            btnLogout.setOnClickListener {
                viewModel.onClick()
            }
        }
    }

    private fun setLoadState(
        textIsVisible: Boolean,
        progressIsVisible: Boolean,
        buttonIsEnabled: Boolean,
    ) {
        with(binding) {
            userId.isVisible = textIsVisible
            userName.isVisible = textIsVisible
            karma.isVisible = textIsVisible
            followers.isVisible = textIsVisible
            progressBar.isVisible = progressIsVisible
            btnFriends.isEnabled = buttonIsEnabled
            btnLogout.isEnabled = buttonIsEnabled
        }
    }
}