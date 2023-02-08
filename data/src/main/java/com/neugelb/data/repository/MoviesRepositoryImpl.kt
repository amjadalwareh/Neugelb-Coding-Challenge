package com.neugelb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.neugelb.data.entity.toDomain
import com.neugelb.data.remote.api.MoviesApi
import com.neugelb.data.remote.datasource.MoviesRemotePagingSource
import com.neugelb.domain.entity.Movie
import com.neugelb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PAGE_SIZE = 30

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepository {

    //#region Get Movies List
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { MoviesRemotePagingSource(api) }
        ).flow.map { page ->
            page.map { response ->
                response.toDomain()
            }
        }
    }
    //#endregion

}