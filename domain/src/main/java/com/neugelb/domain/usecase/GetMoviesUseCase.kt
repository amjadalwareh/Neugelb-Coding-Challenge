package com.neugelb.domain.usecase

import androidx.paging.PagingData
import com.neugelb.domain.entity.Movie
import com.neugelb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) : BaseUseCase<Unit, Flow<PagingData<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }

}