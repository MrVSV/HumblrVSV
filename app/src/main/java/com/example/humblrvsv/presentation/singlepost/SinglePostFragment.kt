package com.example.humblrvsv.presentation.singlepost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.FragmentSinglePostBinding
import com.example.humblrvsv.presentation.base.BaseFragment

class SinglePostFragment : BaseFragment<FragmentSinglePostBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentSinglePostBinding.inflate(inflater)
}
