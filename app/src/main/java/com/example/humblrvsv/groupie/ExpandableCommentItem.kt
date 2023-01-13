package com.example.humblrvsv.groupie


import android.view.View
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.ViewHolderCommentBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.viewbinding.BindableItem

class ExpandableCommentItem(
    private val comment: Comment,
    private val depth: Int,
) : BindableItem<ViewHolderCommentBinding>(), ExpandableItem{

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewBinding: ViewHolderCommentBinding, position: Int) {
        viewBinding.userName.text = "author"
        viewBinding.root.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_comment
    }

    override fun initializeViewBinding(view: View): ViewHolderCommentBinding {
        return ViewHolderCommentBinding.bind(view)
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }
}

