package com.srikanthpuram.githubapidemo.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class GetGithubCommitsResponseModel(
    @SerializedName("items") var items: List<GithubCommits>
)