package com.srikanthpuram.githubapidemo.app

import android.app.Application
import com.srikanthpuram.githubapidemo.BuildConfig
import com.srikanthpuram.githubapidemo.app.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(
                usersListViewModel,
                githubApiModule,
                githubApiClientModule,
                usersListDataSourceFactory,
                commitsListDataSourceFactory,
                commitsListViewModel
            ))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}