package com.lt.githubusersapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lt.githubusersapp.di.ApiComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(private val apiComponent: ApiComponent): ViewModel() {
    private val _state = MutableStateFlow<MainScreenState>(MainScreenState.Init)
    val state: StateFlow<MainScreenState> = _state

   fun fetchUsers(query: String = "", perPage: Int  = 10, page: Int = 1){
        _state.value = MainScreenState.Loading(true)
        viewModelScope.launch {
            flowOf(apiComponent.getUsersUseCase().getUsers(query, perPage, page))
                .catch { ex ->
                    _state.value = MainScreenState.Loading(false)
                    _state.value = MainScreenState.Error(ex.localizedMessage?: "Unknown error occurred")
                }
                .collect {_state.value = MainScreenState.MainSuccess(it)
                }
        }
    }
}


