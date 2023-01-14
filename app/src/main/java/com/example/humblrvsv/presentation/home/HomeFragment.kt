package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.BaseFragment
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import com.example.humblrvsv.tools.setSelectedTabListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        HomePagingAdapter { item -> onClick(item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        observe()
        setModel()
        setSource()
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getSubList().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setModel(){
        binding.toggleModel.setSelectedTabListener { position ->
            when(position){
                0 -> viewModel.setModel(Listing.LINK) { adapter.refresh() }
                1 -> viewModel.setModel(Listing.SUBREDDIT) { adapter.refresh() }
            }
        }
    }

    private fun setSource() {
        binding.toggleOrder.setSelectedTabListener { position ->
            when (position) {
                0 -> viewModel.setSource("") { adapter.refresh() }
                1 -> viewModel.setSource("new") { adapter.refresh() }
            }
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


    private fun onClick(item: Thing) {}
    private fun onLinkClick(item: Link) {}

}