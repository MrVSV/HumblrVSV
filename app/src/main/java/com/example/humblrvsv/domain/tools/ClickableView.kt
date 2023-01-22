package com.example.humblrvsv.domain.tools

enum class ClickableView(val vote: Int = 0) {
    UP_VOTE(vote = 1),
    DOWN_VOTE(vote = -1),
    SAVE,
    USER_C,
    POST_TITLE,
    COMMENT,
    SUBREDDIT,
    SUBSCRIBE,
    USER,
    SHARE
}
