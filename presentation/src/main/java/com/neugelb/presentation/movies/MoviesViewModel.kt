package com.neugelb.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.neugelb.domain.entity.Movie
import com.neugelb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies: Flow<PagingData<Movie>> get() = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase(Unit)
                .cachedIn(viewModelScope)
                .collect {
                    _movies.value = it
                }
        }
    }

}