package com.neugelb.domain

import androidx.paging.PagingData
import com.neugelb.domain.entity.Movie
import com.neugelb.domain.repository.MoviesRepository
import com.neugelb.domain.usecase.GetMoviesUseCase
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    private val repository = mock<MoviesRepository>()
    private lateinit var useCase: GetMoviesUseCase

    @Before
    fun setUp() {
        useCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `should emit a page from the repository`() {
        runBlocking {

            //Given
            whenever(repository.getMovies()) doReturn flow { PagingData.empty<Movie>() }

            //When
            val act = useCase(Unit)

            //Then
            Assert.assertNotNull(act)
        }
    }

    @Test
    fun `should call get movies from MoviesRepository`() {
        runBlocking {
            //When
            useCase.invoke(Unit)

            //Then
            verify(repository, times(1)).getMovies()
        }
    }

}