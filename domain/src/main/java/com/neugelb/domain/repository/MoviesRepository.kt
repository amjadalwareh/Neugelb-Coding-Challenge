package com.neugelb.domain.repository

import androidx.paging.PagingData
import com.neugelb.domain.entity.Movie
import com.neugelb.domain.entity.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Movie>>

    fun getMovieDetails(movieId: Int): Flow<MovieDetails>

}