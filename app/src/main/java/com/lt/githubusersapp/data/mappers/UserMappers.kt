package com.lt.githubusersapp.data.mappers

import com.lt.githubusersapp.data.UserDetailsEntity
import com.lt.githubusersapp.data.UserEntity
import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails

fun UserEntity.toUser(): User {
    return User(
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}

fun UserDetailsEntity.toUserDetails(): UserDetails {
    return UserDetails(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        bio = bio,
        publicRepos = publicRepos,
        following = following,
        followers = followers
    )
}