package com.lt.githubusersapp.di

import android.content.Context
import com.lt.githubusersapp.GithubUsersApplication
import com.lt.githubusersapp.domain.GetUserDetailsUseCase
import com.lt.githubusersapp.domain.GetUsersUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(
    modules = [
        NetworkModule::class,
        UsersDataSourceModule::class,
        UsersRepositoryModule::class,
        DatabaseModule::class,
        UsersPagedDataStoreModule::class
    ]
)
@ApplicationScope
interface AppComponent {

    fun inject(application: GithubUsersApplication)
    fun getUsersUseCase(): GetUsersUseCase
    fun getUserDetailsUseCase(): GetUserDetailsUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@Named("BASE_URL") baseUrl: String): Builder

        @BindsInstance
        fun pageSize(@Named("PAGE_SIZE") pageSize: Int): Builder

        @BindsInstance
        fun context(applicationContext: Context): Builder
        fun build(): AppComponent
    }
}
