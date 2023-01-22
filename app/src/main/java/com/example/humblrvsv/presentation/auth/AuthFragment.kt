package com.example.humblrvsv.presentation.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.humblrvsv.*
import com.example.humblrvsv.databinding.FragmentAuthBinding
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentAuthBinding.inflate(inflater)

    private val viewModel by viewModels<AuthViewModel>()
    private val args by navArgs<AuthFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAuth()
        tokenObserve(createSharedPreference(TOKEN_SHARED_NAME))
        loadingObserve(createSharedPreference(TOKEN_SHARED_NAME))
        viewModel.createToken(args.code)
        getUserName(createSharedPreference(SHARED_PROFILE))
        Log.e(TAG, "createToken: ${args.code}")

    }

    private fun getUserName(preferences: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profileInfo.collect {info ->
                val userName = info.name
                preferences.edit().putString(SHARED_PROFILE_USER_NAME, userName).apply()
            }
        }
    }

    private fun startAuth() {
        binding.btnAuth.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(CALL))
            startActivity(intent)
        }
    }

    private fun tokenObserve(preferences: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.token.collect { token ->
                preferences.edit().putString(TOKEN_SHARED_KEY, token).apply()
                preferences.edit().putBoolean(TOKEN_ENABLED_KEY, true).apply()
            }
        }
    }

    private fun loadingObserve(preferences: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadState ->
                if (preferences.getBoolean(TOKEN_ENABLED_KEY, false))
                    LoadState.SUCCESS
                Log.d(TAG, "loadingObserve: $loadState")
                when (loadState) {
                    LoadState.START ->
                        setLoadState(
                            buttonIsEnabled = true,
                            textIsVisible = false,
                            progressIsVisible = false
                        )
                    LoadState.LOADING_STAGE_1 ->
                        setLoadState(
                            buttonIsEnabled = false,
                            textIsVisible = false,
                            progressIsVisible = true
                        )
                    LoadState.LOADING_STAGE_2 ->
                        setLoadState(
                            buttonIsEnabled = false,
                            textIsVisible = false,
                            progressIsVisible = true
                        )
                    LoadState.SUCCESS -> {
                        setLoadState(
                            buttonIsEnabled = false,
                            textIsVisible = true,
                            progressIsVisible = false
                        )
                        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToHomeFragment())
                    }
                    LoadState.ERROR -> {
                        setLoadState(
                            buttonIsEnabled = false,
                            textIsVisible = false,
                            progressIsVisible = false
                        )
                        Log.e(TAG, "loadingObserve: ${loadState.message}")
                        binding.text.text = loadState.message
                    }

                }
            }
        }
    }

    private fun setLoadState(
        buttonIsEnabled: Boolean,
        textIsVisible: Boolean,
        progressIsVisible: Boolean,
    ) {
        binding.btnAuth.isEnabled = buttonIsEnabled
        binding.text.isVisible = textIsVisible
        binding.progressBar.isVisible = progressIsVisible
    }
}