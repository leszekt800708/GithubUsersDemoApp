package com.lt.githubusersapp.di

import com.lt.githubusersapp.domain.GetUserDetailsUseCase
import com.lt.githubusersapp.domain.GetUsersUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(
    modules = [
        NetworkModule::class,
        UsersDataSourceModule::class,
        UsersRepositoryModule::class
    ]
)
@ApplicationScope
interface ApiComponent {
    fun getUsersUseCase(): GetUsersUseCase
    fun getUserDetailsUseCase(): GetUserDetailsUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@Named("BASE_URL") baseUrl: String): Builder
        fun build(): ApiComponent
    }
}
