package com.example.movieappmvi.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvi.repository.LocalRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: LocalRepository
) : ViewModel() {
    val channel = Channel<DetailIntent>(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when (it) {
                    is DetailIntent.OnSavedClicked -> {
                        repository.saveMovie(it.movieEntity)
                    }
                    is DetailIntent.OnDeleteClicked -> {
                        repository.deleteMovie(it.movieEntity)
                    }
                }
            }
        }
    }
}