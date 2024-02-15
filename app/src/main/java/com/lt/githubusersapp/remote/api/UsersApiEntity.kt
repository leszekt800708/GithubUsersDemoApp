package com.lt.githubusersapp.remote.api

import com.google.gson.annotations.SerializedName

data class UsersApiEntity(
    @SerializedName("id") val userId: String,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String

)
