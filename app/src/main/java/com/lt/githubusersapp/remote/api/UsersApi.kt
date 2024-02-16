package com.lt.githubusersapp.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {

    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") startId: Int
    ): List<UsersApiEntity>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): UserDetailsApiEntity
}