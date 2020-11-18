package com.srikanthpuram.githubapidemo.data.remote.api

import com.srikanthpuram.githubapidemo.data.remote.api.models.*
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {

    @GET("search/users?q=repos:>1")
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<GetGithubUserResponseModel>

    @GET("repos/{owner}/{repo}/commits")
    suspend fun getOwnerCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<GetGithubCommitsResponseModel>
}