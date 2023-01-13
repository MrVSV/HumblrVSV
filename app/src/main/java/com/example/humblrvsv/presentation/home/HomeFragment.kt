package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.presentation.BaseFragment
import com.example.humblrvsv.presentation.home.adapter.SubredditPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        SubredditPagingAdapter { item -> onClick(item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        observe("")
        setSource()
        loadStateItemsObserve()

//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.getSubList().collect { pagingData ->
//                adapter.submitData(pagingData)
//            }
//        }

    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observe(source: String?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getSubList(source).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setSource(){
        binding.toggleOrder.addOnButtonCheckedListener { _, _, _ ->
            if (binding.btnPop.isChecked) observe("")
            if (binding.btnNew.isChecked) observe("new")
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


    private fun onClick(item: Subreddit) {

    }
}