package com.srikanthpuram.githubapidemo.app.modules

import com.srikanthpuram.githubapidemo.presentation.viewmodel.CommitsListViewModel
import com.srikanthpuram.githubapidemo.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
    
val usersListViewModel = module {
    viewModel { UsersListViewModel(get()) }
}

val commitsListViewModel = module {
    viewModel { CommitsListViewModel(get()) }
}

