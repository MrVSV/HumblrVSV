package com.example.humblrvsv.presentation.singlepost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.humblrvsv.R

class SinglePostFragment : Fragment() {

    companion object {
        fun newInstance() = SinglePostFragment()
    }

    private lateinit var viewModel: SinglePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SinglePostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}