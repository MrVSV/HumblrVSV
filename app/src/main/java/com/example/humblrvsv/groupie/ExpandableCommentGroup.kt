package com.example.humblrvsv.groupie

import com.xwray.groupie.ExpandableGroup

class ExpandableCommentGroup(
    comment: Comment,
    depth: Int = 0,
) : ExpandableGroup(ExpandableCommentItem(comment, depth)) {

    init {
        for (comm in comment.children) {
            add(ExpandableCommentGroup(comm, depth.plus(1)))
        }
    }
}