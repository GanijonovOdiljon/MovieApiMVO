package com.example.movieappmvi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.movieappmvi.network.RetrofitInstance
import com.example.movieappmvi.repository.RemoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val repository = RemoteRepository(RetrofitInstance.getApiService())
    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState.Loading)
    val state get() = _state.asStateFlow()
    val channel = Channel<SearchIntent> { Channel.UNLIMITED }

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when (it) {
                    is SearchIntent.OnSearched -> SearchMovies(it.query)
                }
            }
        }
    }

    private fun SearchMovies(query: String) {
        viewModelScope.launch {
            _state.value = SearchState.Loading
            delay(500L)
            try {
                val responce = repository.searchMovie(query)
                if (responce.isSuccessful) {
                    _state.value = SearchState.Success(responce.body()?.results!!)
                }
            } catch (e: Exception) {
                _state.value = SearchState.Error(e.message!!)
            }
        }

    }
}