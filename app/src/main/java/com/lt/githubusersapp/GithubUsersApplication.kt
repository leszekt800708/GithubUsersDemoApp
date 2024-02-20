package com.lt.githubusersapp

import android.app.Application
import com.lt.githubusersapp.di.AppComponent
import com.lt.githubusersapp.di.DaggerAppComponent

class GithubUsersApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .baseUrl("https://api.github.com/")
            .context(this)
            .pageSize(20)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}