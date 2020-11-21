package com.srikanthpuram.githubapidemo.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits
import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListDataSource
import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListDataSourceFactory
import com.srikanthpuram.githubapidemo.util.Constants.Companion.PAGE_SIZE
import java.util.concurrent.Executors

class CommitsListViewModel (private val commitsListDataSourceFactory: CommitsListDataSourceFactory) : ViewModel() {

    var dataSource: MutableLiveData<CommitsListDataSource>
    lateinit var commitsLiveData: LiveData<PagedList<GithubCommits>>
    val isWaiting: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()
    val totalCount: ObservableField<Long> = ObservableField()

    init {
        isWaiting.set(true)
        errorMessage.set(null)
        dataSource = commitsListDataSourceFactory.liveData
        initUsersListFactory()
    }

    private fun initUsersListFactory() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(3)
            .build()

        val executor = Executors.newFixedThreadPool(5)

        commitsLiveData = LivePagedListBuilder<Int, GithubCommits>(commitsListDataSourceFactory, config)
            .setFetchExecutor(executor)
            .build()
    }
}
