package com.neugelb.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.neugelb.data.entity.MovieResponse
import com.neugelb.data.remote.api.MoviesApi

private const val STARTING_PAGE_INDEX = 1

internal class MoviesRemotePagingSource constructor(
    private val api: MoviesApi
) : PagingSource<Int, MovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val pageNumber = if (params is LoadParams.Refresh) STARTING_PAGE_INDEX else params.key
                ?: STARTING_PAGE_INDEX

            val response = api.fetchMovies(pageNumber)

            val nextPageNumber = when {
                response.results.isEmpty() -> null
                else -> pageNumber + 1
            }

            LoadResult.Page(
                data = response.results,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

}