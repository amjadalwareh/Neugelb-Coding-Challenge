package com.neugelb.data

import androidx.paging.PagingSource
import com.neugelb.data.base.FakeMovies
import com.neugelb.data.remote.api.MoviesApi
import com.neugelb.data.remote.datasource.MoviesRemotePagingSource
import com.neugelb.data.repository.MoviesRepositoryImpl
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class MoviesRepositoryTest {

    private val api = mock<MoviesApi>()

    private lateinit var repositoryImpl: MoviesRepositoryImpl

    @Before
    fun setUp() {
        repositoryImpl = MoviesRepositoryImpl(api)
    }

    @Test
    fun `get movies list should return a paging data`() {
        runBlocking {
            val pagingSource = MoviesRemotePagingSource(api)

            whenever(api.fetchMovies(1)) doReturn FakeMovies.getFakeMovieList()

            val actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ) as PagingSource.LoadResult.Page

            val expected = PagingSource.LoadResult.Page(
                data = FakeMovies.getFakeMovieList().results,
                prevKey = null,
                nextKey = 1
            )

            Assert.assertEquals(
                actual.data,
                expected.data
            )
        }
    }

    @Test
    fun `call getMovieDetails should return correct movie details`() {
        runBlocking {
            whenever(api.fetchMovieDetails(1)) doReturn FakeMovies.getFakeMovieDetails()

            val actual = repositoryImpl.getMovieDetails(1).first()

            Assert.assertEquals(
                actual.budget,
                250000000
            )
        }
    }

    @Test
    fun `call fetchMovies should call it once`() {
        runBlocking {
            api.fetchMovies(1)

            verify(api, times(1)).fetchMovies(1)
        }
    }

    @Test
    fun `call fetchMovieDetails should call it once`() {
        runBlocking {
            api.fetchMovieDetails(1)

            verify(api, times(1)).fetchMovieDetails(1)
        }
    }

}