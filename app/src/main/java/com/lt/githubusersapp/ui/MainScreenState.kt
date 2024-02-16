package com.lt.githubusersapp.ui

import androidx.paging.PagingData
import com.lt.githubusersapp.domain.User
import kotlinx.coroutines.flow.Flow

sealed class MainScreenState {
    data object Init : MainScreenState()
    data class MainSuccess(val users: Flow<PagingData<User>>) : MainScreenState()
    data class Loading(val isLoading: Boolean) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
}