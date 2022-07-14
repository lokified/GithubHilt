package com.loki.githubhilt.data.model.responses

import com.google.gson.annotations.SerializedName
import com.loki.githubhilt.data.model.Users

data class UserResponse(
    @SerializedName("items")
    var results: List<Users>
)