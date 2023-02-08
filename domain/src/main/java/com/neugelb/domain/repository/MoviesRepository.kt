package com.neugelb.domain.repository

import androidx.paging.PagingData
import com.neugelb.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Movie>>

}