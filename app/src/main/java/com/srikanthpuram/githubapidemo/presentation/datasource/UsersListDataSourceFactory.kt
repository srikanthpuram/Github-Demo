package com.srikanthpuram.githubapidemo.presentation.datasource

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubUserModel
import com.srikanthpuram.githubapidemo.domain.api.GithubApiClient

class UsersListDataSourceFactory(private val githubApiClient: GithubApiClient): DataSource.Factory<Int, GithubUserModel>() {

    val liveData: MutableLiveData<UsersListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, GithubUserModel> {
        val usersListDataSource = UsersListDataSource(githubApiClient)
        liveData.postValue(usersListDataSource)
        return usersListDataSource
    }
}