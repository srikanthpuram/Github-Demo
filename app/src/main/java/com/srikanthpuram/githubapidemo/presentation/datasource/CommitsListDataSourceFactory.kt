package com.srikanthpuram.githubapidemo.presentation.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits
import com.srikanthpuram.githubapidemo.domain.api.GithubApiClient

class CommitsListDataSourceFactory(private val githubApiClient: GithubApiClient): DataSource.Factory<Int, GithubCommits>() {

    val liveData: MutableLiveData<CommitsListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, GithubCommits> {
        val itemsListDataSource = CommitsListDataSource(githubApiClient)
        liveData.postValue(itemsListDataSource)
        return itemsListDataSource
    }
}