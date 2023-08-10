package com.example.movieappmvi.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvi.network.RetrofitInstance
import com.example.movieappmvi.repository.RemoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MovieViewModel : ViewModel() {
    private val repository = RemoteRepository(RetrofitInstance.getApiService())
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Init)
    val state: StateFlow<MainState> get() = _state
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUsers -> callMovies(it.page)
                }
            }
        }
    }

    private fun callMovies(pge: Int) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            delay(500L)
            try {
                val responce = repository.getMovies()
                if (responce.isSuccessful) {
                    _state.value = MainState.Success(responce.body()?.results!!)
                }
            } catch (e: Exception) {
                _state.value = MainState.Error(e.message!!)
            }
        }
    }
}