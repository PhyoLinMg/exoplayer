package com.elemental.exoplayertesting.ViewModel.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elemental.exoplayertesting.repository.HomeRepository.HomeRepository

class HomeViewModelFactory(private val homeRepository: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}