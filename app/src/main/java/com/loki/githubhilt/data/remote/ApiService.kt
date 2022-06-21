package com.loki.githubhilt.data.remote

import com.loki.githubhilt.data.model.responses.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun searchGithubUsers(
        @Query("q") query: String,
        @Header("Authorization") accessToken: String
    ): Call<UserResponse>
}