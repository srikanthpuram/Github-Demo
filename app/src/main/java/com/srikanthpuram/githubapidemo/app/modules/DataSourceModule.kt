package com.srikanthpuram.githubapidemo.app.modules

import com.srikanthpuram.githubapidemo.presentation.datasource.CommitsListDataSourceFactory
import com.srikanthpuram.githubapidemo.presentation.datasource.UsersListDataSourceFactory
import org.koin.dsl.module

val usersListDataSourceFactory = module {
    single { UsersListDataSourceFactory(get()) }
}

val commitsListDataSourceFactory = module {
    single { CommitsListDataSourceFactory(get()) }
}