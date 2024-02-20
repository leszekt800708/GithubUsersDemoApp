package com.lt.githubusersapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,
    val login: String,
    val avatarUrl: String
)