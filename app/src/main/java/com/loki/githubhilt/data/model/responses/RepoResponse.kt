package com.loki.githubhilt.data.model.responses

import com.google.gson.annotations.SerializedName
import com.loki.githubhilt.data.model.Repos

data class RepoResponse(
    @SerializedName("items")
    val users: List<Repos>,
)