package com.lt.githubusersapp.ui

import com.lt.githubusersapp.domain.UserDetails

sealed class DetailsScreenState {
    data object Init : DetailsScreenState()
    data class Loading(val isLoading: Boolean) : DetailsScreenState()
    data class Error(val message: String) : DetailsScreenState()
    data class DetailsSuccess(val userDetails: UserDetails) : DetailsScreenState()
}