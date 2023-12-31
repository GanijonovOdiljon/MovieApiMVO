package com.example.movieappmvi.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmvi.repository.LocalRepository

class FavoriteViewModelFactory(
    private val repository: LocalRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModelFactory(repository) as T
    }
}