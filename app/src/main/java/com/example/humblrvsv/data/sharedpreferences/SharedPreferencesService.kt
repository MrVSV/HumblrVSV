package com.example.humblrvsv.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

interface SharedPreferencesService {

    fun create(context: Context): SharedPreferences

    fun save(key: String, data: Any)

    fun load(key: String): Any

}