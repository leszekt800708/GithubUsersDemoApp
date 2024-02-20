package com.lt.githubusersapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lt.githubusersapp.di.AppComponent

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            MainScreenViewModel(appComponent.getUsersUseCase()) as T
        } else {
            DetailsScreenViewModel(appComponent.getUserDetailsUseCase()) as T
        }
}