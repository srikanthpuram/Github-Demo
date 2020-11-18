package com.srikanthpuram.githubapidemo.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Commit(
    @SerializedName("author") var author: Author,
    @SerializedName("message") var message: String
)