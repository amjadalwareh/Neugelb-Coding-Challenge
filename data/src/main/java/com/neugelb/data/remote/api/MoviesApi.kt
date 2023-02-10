package com.neugelb.data.remote.api

import com.neugelb.data.entity.MovieDetailsResponse
import com.neugelb.data.entity.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesApi {

    /**
     * Fetch Movies list from the endpoint and return [MovieListResponse]
     * @param page: page number to request.
     */
    @GET("discover/movie")
    suspend fun fetchMovies(@Query("page") page: Int): MovieListResponse

    /**
     * Fetch Movie details from the endpoint and return [MovieDetailsResponse]
     * @param id: Movie id.
     */
    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") id: Int): MovieDetailsResponse

}