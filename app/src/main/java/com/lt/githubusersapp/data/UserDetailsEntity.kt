package com.lt.githubusersapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailsEntity(
    @PrimaryKey
    val id: String,
    val login: String,
    val avatarUrl: String,
    val bio: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)