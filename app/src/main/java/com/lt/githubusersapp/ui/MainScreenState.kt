package com.lt.githubusersapp.ui

import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails

sealed class MainScreenState {
    data object Init : MainScreenState()
    data class MainSuccess(val users: List<User>) : MainScreenState()
    data class Loading(val isLoading: Boolean) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
}