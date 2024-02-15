package com.lt.githubusersapp.remote.api

import com.google.gson.annotations.SerializedName

data class UserDetailsApiEntity(
    @SerializedName("id") val userId: String,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val bio: String?,
    @SerializedName("public_repos") val publicRepos: Int,
    val followers: Int,
    val following: Int

)
