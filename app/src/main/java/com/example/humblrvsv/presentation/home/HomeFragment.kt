package com.example.humblrvsv.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.humblrvsv.databinding.FragmentHomeBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.presentation.BaseFragment
import com.example.humblrvsv.presentation.home.linkadapter.LinkPagingAdapter
import com.example.humblrvsv.presentation.home.subredditadapter.SubredditPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModels<HomeViewModel>()
    private val subredditAdapter by lazy {
        SubredditPagingAdapter { item -> onSubredditClick(item) }
    }
    private val linkAdapter by lazy {
        LinkPagingAdapter { item -> onLinkClick(item) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingAdapter()
        observe("")
        setSource()
        loadStateItemsObserve()
    }

    private fun settingAdapter() {
        binding.toggleModel.addOnButtonCheckedListener { _, _, _ ->
            if (binding.btnLink.isChecked) binding.recycler.adapter = linkAdapter
            if (binding.btnSub.isChecked) binding.recycler.adapter = subredditAdapter
        }
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observe(source: String?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getSubList(source).collect { pagingData ->
                subredditAdapter.submitData(pagingData)
            }
        }
    }

    private fun setSource() {
        binding.toggleOrder.addOnButtonCheckedListener { _, _, _ ->
            if (binding.btnPop.isChecked) observe("")
            if (binding.btnNew.isChecked) observe("new")
        }
    }


    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            subredditAdapter.loadStateFlow.collect { state ->
                if (state.refresh is LoadState.Loading || state.append is LoadState.Loading)
                    binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.GONE
            }
            linkAdapter.loadStateFlow.collect { state ->
                if (state.refresh is LoadState.Loading || state.append is LoadState.Loading)
                    binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.GONE
            }
        }
    }


    private fun onSubredditClick(item: Subreddit) {}
    private fun onLinkClick(item: Link) {}
}