package com.example.humblrvsv.interceptor

import android.content.Context
import com.example.humblrvsv.TOKEN_SHARED_KEY
import com.example.humblrvsv.TOKEN_SHARED_NAME


class AuthTokenProvider(private val context: Context) {

    fun getToken(): String? {
        val pref = context.getSharedPreferences(TOKEN_SHARED_NAME, Context.MODE_PRIVATE)

        return pref.getString(TOKEN_SHARED_KEY, "")
    }

}