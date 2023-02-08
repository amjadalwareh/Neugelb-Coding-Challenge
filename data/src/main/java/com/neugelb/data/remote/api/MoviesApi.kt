package com.neugelb.data.remote.api

import com.neugelb.data.entity.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    /**
     * Fetch Movies list from the endpoint and return [MovieListResponse]
     * @param page: page number to request.
     */
    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("page") page: Int
    ): MovieListResponse
}