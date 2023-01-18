package com.example.humblrvsv.domain.tools

class OnChange<T>(val value: T)

class LocalChange{
    var isVoted = mutableMapOf<String, Boolean?>()
}