package com.jquiroga.kmpmovies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jquiroga.kmpmovies.data.Movie
import com.jquiroga.kmpmovies.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            movieRepository.movies.collect {
                if (it.isNotEmpty()) {
                    val mappedData = it.map { movie ->
                        Movie(
                            id = movie.id,
                            title = movie.title,
                            poster = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
                        )
                    }
                    _state.value = UiState(loading = false, movies = mappedData)
                } else {
                    _state.value = UiState(loading = false)
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}