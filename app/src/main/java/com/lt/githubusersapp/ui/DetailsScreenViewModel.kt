package com.lt.githubusersapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lt.githubusersapp.domain.GetUserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val getUserDetailsUseCase: GetUserDetailsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Init)
    val state: StateFlow<DetailsScreenState> = _state

    fun fetchUserDetails(login: String) {
        _state.value = DetailsScreenState.Loading(true)
        viewModelScope.launch {
            flowOf(getUserDetailsUseCase.getUserDetails(login))
                .catch { ex ->
                    _state.value = DetailsScreenState.Loading(false)
                    _state.value = DetailsScreenState.Error(ex.localizedMessage ?: "Unknown error occurred")
                }
                .collect {
                    _state.value = DetailsScreenState.DetailsSuccess(it)
                }
        }
    }
}


