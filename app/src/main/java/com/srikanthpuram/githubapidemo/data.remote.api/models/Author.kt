package com.srikanthpuram.githubapidemo.data.remote.api.models

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("date") var date: String,
    @SerializedName("name") var name: String
)