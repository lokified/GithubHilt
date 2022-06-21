package com.loki.githubhilt.data.domain

import androidx.lifecycle.MutableLiveData
import com.loki.githubhilt.BuildConfig
import com.loki.githubhilt.data.model.Users
import com.loki.githubhilt.data.model.responses.UserResponse
import com.loki.githubhilt.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {

    fun getUsers(query:String, liveData: MutableLiveData<List<Users>>) {

        val call: Call<UserResponse> = apiService.searchGithubUsers(query, "token ${BuildConfig.ACCESS_TOKEN}")

        call.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                liveData.postValue(response.body()?.users)

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                
            }
        })
    }
}