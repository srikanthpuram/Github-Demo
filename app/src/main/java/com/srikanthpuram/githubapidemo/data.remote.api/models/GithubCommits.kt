package com.srikanthpuram.githubapidemo.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class GithubCommits(
    @SerializedName("sha") var sha: String,
    @SerializedName("commit") var commit: Commit

)