package com.lt.githubusersapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lt.githubusersapp.di.ApiComponent

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiComponent: ApiComponent) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            MainScreenViewModel(apiComponent.getUsersUseCase()) as T
        } else {
            DetailsScreenViewModel(apiComponent.getUserDetailsUseCase()) as T
        }
}