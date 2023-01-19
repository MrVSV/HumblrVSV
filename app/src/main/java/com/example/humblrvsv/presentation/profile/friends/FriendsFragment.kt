package com.example.humblrvsv.presentation.profile.friends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.FragmentFriendsBinding
import com.example.humblrvsv.presentation.base.BaseFragment


class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentFriendsBinding.inflate(inflater)

}