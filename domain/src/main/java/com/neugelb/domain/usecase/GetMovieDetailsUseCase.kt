package com.neugelb.domain.usecase

import com.neugelb.domain.entity.MovieDetails
import com.neugelb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MoviesRepository
) : BaseUseCase<Int, MovieDetails> {

    override suspend fun invoke(params: Int): Flow<MovieDetails> =
        repository.getMovieDetails(params)

}