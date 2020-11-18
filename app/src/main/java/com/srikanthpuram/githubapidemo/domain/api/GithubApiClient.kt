package com.srikanthpuram.githubapidemo.domain.api

import com.srikanthpuram.githubapidemo.data.remote.api.base.Resource
import com.srikanthpuram.githubapidemo.data.remote.api.models.*

interface GithubApiClient {

    suspend fun getUsersList(page: Int, pageSize: Int): Resource<GetGithubUserResponseModel>

    suspend fun getOwnerCommits(owner: String, repo: String): Resource<GetGithubCommitsResponseModel>

}