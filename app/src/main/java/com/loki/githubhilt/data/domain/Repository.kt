package com.loki.githubhilt.data.domain

import com.loki.githubhilt.BuildConfig
import com.loki.githubhilt.data.model.responses.UserResponse
import com.loki.githubhilt.data.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getUsers(searchTerm: String, errorText:(String) -> Unit): UserResponse? {

        return try {
            val users = apiService.searchGithubUsers(searchTerm, "token ${BuildConfig.ACCESS_TOKEN}")

            users
        }
        catch (e: Exception) {
            errorText(e.toString())
            null
        }
    }
}