package com.srikanthpuram.githubapidemo

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.srikanthpuram.githubapidemo.data.remote.api.models.GithubCommits
import com.srikanthpuram.githubapidemo.domain.api.GithubApiClient
import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListDataSource
import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListDataSourceFactory
import com.srikanthpuram.githubapidemo.presentation.viewmodel.CommitsListViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.initMocks


open class CommitsListViewModelTest {


    lateinit var commitsListViewModel: CommitsListViewModel
    @Mock
    lateinit var commitsListDataSourceFactory: CommitsListDataSourceFactory
    @Mock
    lateinit var commitsLiveData: LiveData<PagedList<GithubCommits>>
    @Mock
    val isWaiting: ObservableField<Boolean> = ObservableField()
    @Mock
    val errorMessage: ObservableField<String> = ObservableField()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun initUsersListFactoryTest() {
        commitsListViewModel = CommitsListViewModel(commitsListDataSourceFactory)
        Assert.assertNotNull(commitsListViewModel)
    }

    @After
    fun tearDown() {

    }
}