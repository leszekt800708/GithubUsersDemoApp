package com.lt.githubusersapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lt.githubusersapp.domain.GetUsersUseCase
import com.lt.githubusersapp.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    private val _usersState: MutableStateFlow<PagingData<User>> = MutableStateFlow(value = PagingData.empty())
    val usersState: StateFlow<PagingData<User>> = _usersState

    init {
        fetchUsers()
    }

    private suspend fun getUsers(pageSize: Int) {
        getUsersUseCase.getUsers(pageSize)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _usersState.value = it
            }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            getUsers(10)
        }
    }
}




