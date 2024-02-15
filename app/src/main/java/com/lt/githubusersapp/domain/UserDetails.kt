package com.lt.githubusersapp.domain

data class UserDetails(
    val id: String,
    val login: String,
    val avatarUrl: String,
    val bio: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int

)
