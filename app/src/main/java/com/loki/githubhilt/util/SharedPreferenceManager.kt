package com.loki.githubhilt.util

import android.content.Context

object SharedPreferenceManager {

    fun savePreviousSearch(context: Context?, user: String) {

        val sharedPreferences = context?.getSharedPreferences("recentSearch", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        editor?.putString("user", user)
        editor?.apply()
    }
}