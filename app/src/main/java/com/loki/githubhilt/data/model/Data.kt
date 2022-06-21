package com.loki.githubhilt.data.model

import com.google.gson.annotations.SerializedName


data class Users(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("html_url")
    val html_url: String,
    @SerializedName("follower_url")
    val followers_url: String,
    @SerializedName("following_url")
    val following_url: String,

)

data class Repos(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
)