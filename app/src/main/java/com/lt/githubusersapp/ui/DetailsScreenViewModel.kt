package com.lt.githubusersapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.lt.githubusersapp.domain.GetUserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsScreenViewModel(private val getUserDetailsUseCase: GetUserDetailsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Init)
    val state: StateFlow<DetailsScreenState> = _state

    fun fetchUserDetails(login: String) {
        _state.value = DetailsScreenState.Loading(true)
        viewModelScope.launch {
            try {
                val userDetails = getUserDetailsUseCase.getUserDetails(login)
                _state.value = DetailsScreenState.Loading(false)
                _state.value = DetailsScreenState.DetailsSuccess(userDetails)


            } catch (ex: IOException) {
                _state.value = DetailsScreenState.Loading(false)
                _state.value = DetailsScreenState.Error(ex.localizedMessage ?: "Unknown error occurred")

            } catch (ex: HttpException) {
                _state.value = DetailsScreenState.Loading(false)
                _state.value = DetailsScreenState.Error(ex.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}


