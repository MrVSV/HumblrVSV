package com.example.humblrvsv.presentation.user

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.humblrvsv.databinding.FragmentUserBinding
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.presentation.adapter.ThingListAdapter
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentUserBinding.inflate(inflater)

    private val viewModel by viewModels<UserViewModel>()

    private val adapter by lazy {
        ThingListAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    private fun onClick(clickableView: ClickableView, item: Thing) {

    }


}