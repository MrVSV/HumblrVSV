package com.example.humblrvsv.presentation.singlepost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.humblrvsv.databinding.FragmentSinglePostBinding
import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.presentation.adapter.ThingListAdapter
import com.example.humblrvsv.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SinglePostFragment : BaseFragment<FragmentSinglePostBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentSinglePostBinding.inflate(inflater)

    private val viewModel by viewModels<SinglePostViewModel>()

    private val adapter by lazy {
        ThingListAdapter { clickableView, item -> onClick(clickableView, item) }
    }

    private val args by navArgs<SinglePostFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadSinglePost(args.url)
        settingAdapter()
        observeSinglePostData()
        loadStateItemsObserve()

    }

    private fun settingAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator?.changeDuration = 0
    }

    private fun observeSinglePostData() {
        viewModel.post.onEach { friends ->
            adapter.submitList(friends)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.LOADING_STAGE_1 ->
                        binding.progressBar.isVisible = true
                    LoadState.ERROR -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT).show()
                    }
                    else -> binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun onClick(clickableView: ClickableView, item: Thing) {
        when(clickableView){
            ClickableView.UP_VOTE -> Toast.makeText(requireContext(), "voteUp", Toast.LENGTH_SHORT).show()
            ClickableView.DOWN_VOTE -> Toast.makeText(requireContext(), "voteDown", Toast.LENGTH_SHORT).show()
            ClickableView.SAVE -> Toast.makeText(requireContext(), "post saved", Toast.LENGTH_SHORT).show()
            ClickableView.USER_C -> {
                item as Comment
                findNavController().navigate(
                    SinglePostFragmentDirections.actionSinglePostFragmentToUserFragment(item.author)
                )
            }
            ClickableView.POST_TITLE -> TODO()
            ClickableView.COMMENT -> TODO()
            ClickableView.SUBREDDIT -> TODO()
            ClickableView.SUBSCRIBE -> TODO()
            ClickableView.USER -> {
                item as Post
                findNavController().navigate(
                    SinglePostFragmentDirections.actionSinglePostFragmentToUserFragment(item.author)
                )
            }
            ClickableView.SHARE -> {
                item as Post
                shareLinkOnPhoto(item.url)
            }
        }
    }

    private fun shareLinkOnPhoto(url: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(sharingIntent, "Share"))
    }
}

