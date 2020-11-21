package com.srikanthpuram.githubapidemo.presentation.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits
import com.srikanthpuram.githubapidemo.domain.api.GithubApiClient
import com.srikanthpuram.githubapidemo.util.Constants.Companion.GITHUB_OWNER
import com.srikanthpuram.githubapidemo.util.Constants.Companion.GITHUB_REPO
import com.srikanthpuram.githubapidemo.util.Constants.Companion.PAGE_SIZE
import com.srikanthpuram.githumapidemo.data.remote.api.base.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CommitsListDataSource (private val githubApiClient: GithubApiClient): PageKeyedDataSource<Int, GithubCommits>() {

    private val dataSourceJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + dataSourceJob)
    val loadStateLiveData: MutableLiveData<Status> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GithubCommits>) {
        scope.launch {
            loadStateLiveData.postValue(Status.LOADING)
            //Owner and Repo pass it to the API
            val response = githubApiClient.getOwnerCommits(GITHUB_OWNER,GITHUB_REPO,1, PAGE_SIZE)
            when(response.status) {
                Status.ERROR -> loadStateLiveData.postValue(Status.ERROR)
                Status.EMPTY -> loadStateLiveData.postValue(Status.EMPTY)
                else -> {
                    response.data?.let {
                        callback.onResult(it, null, 2)
                        loadStateLiveData.postValue(Status.SUCCESS)
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubCommits>) {
        scope.launch {
            val response = githubApiClient.getOwnerCommits(GITHUB_OWNER,GITHUB_REPO,params.key, PAGE_SIZE)
            response.data?.let {
                callback.onResult(it, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubCommits>) {

    }
}