package com.example.humblrvsv.tools

enum class ClickableView(val vote: Int = 0) {
    UP_VOTE(vote = 1),
    DOWN_VOTE(vote = -1),
    SAVE,
    PHOTO,
    TITLE,
    COMMENT
}
