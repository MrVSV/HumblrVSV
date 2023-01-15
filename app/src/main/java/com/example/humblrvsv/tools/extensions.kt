package com.example.humblrvsv.tools

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.humblrvsv.data.api.dto.commentdto.CommentDto
import com.example.humblrvsv.data.api.dto.commentdto.CommentListingDto
import com.example.humblrvsv.data.api.dto.linkdto.PostDto
import com.example.humblrvsv.data.api.dto.subredditdto.SubredditDto
import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.domain.model.CommentListing
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Subreddit
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

fun List<CommentListingDto>.toListCommentListing(): List<CommentListing> =
    this.map { item -> item.toCommentListing() }

fun List<CommentDto>.toListComment(): List<Comment> =
    this.map { item -> item.toComment() }

fun List<SubredditDto>.toListSubreddit(): List<Subreddit> {
    val newList = mutableListOf<Subreddit>()
    this.forEach { item ->
        newList.add(item.toSubreddit())
    }
    return newList
}

fun List<PostDto>.toListPost(): List<Post> {
    val newList = mutableListOf<Post>()
    this.forEach { item ->
        newList.add(item.toPost())
    }
    return newList
}

fun TabLayout.setSelectedTabListener(block: (position: Int) -> Unit){
    this.addOnTabSelectedListener(object : OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab != null) { block(tab.position) }
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}

fun ImageView.loadImage(urls: String) {
    Log.d("Glide", "loadImage: ")
    Glide.with(this)
        .load(urls)
//        .error(R.drawable.error_image)
//        .diskCacheStrategy(DiskCacheStrategy.ALL)
//        .placeholder(R.drawable.placeholder)
        .into(this)
}