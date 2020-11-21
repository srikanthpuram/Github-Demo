package com.srikanthpuram.githubapidemo.domain.api

import com.srikanthpuram.githubapidemo.data.remote.api.GithubApi
import com.srikanthpuram.githubapidemo.data.remote.api.base.Resource
import com.srikanthpuram.githubapidemo.data.remote.api.models.GetGithubCommitsResponseModel
import com.srikanthpuram.githubapidemo.data.remote.api.models.GetGithubUserResponseModel
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubApiClientImpl(private val githubApi: GithubApi) : GithubApiClient {

    override suspend fun getUsersList(
        page: Int,
        pageSize: Int
    ): Resource<GetGithubUserResponseModel> = withContext(Dispatchers.IO) {
        try {
            val response = githubApi.getUsersList(page, pageSize)
            if (response.isSuccessful) {
                Resource.success(response.body())

            } else {
                Resource.error(response.message())
            }
        } catch (ex: Throwable) {
            Resource.error<GetGithubUserResponseModel>("${ex.message}")
        }
    }

    override suspend fun getOwnerCommits(
        owner: String,
        repo: String,
        page: Int,
        pageSize: Int
    ): Resource<List<GithubCommits>> = withContext(Dispatchers.IO) {

        try {
            val response = githubApi.getOwnerCommits(owner,repo,page,pageSize)

            if (response.isSuccessful) {
                Resource.success(response.body())

            } else {
                Resource.error(response.message())
            }
        } catch (ex: Throwable) {
            Resource.error<List<GithubCommits>>("${ex.message}")
        }
    }
}