package com.neugelb.data

import com.neugelb.domain.repository.MoviesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GitHubRepositoryTest {

    private val repository = mock<MoviesRepository>()

    @Test
    fun `should fetch flow paging data once`() {
        runBlocking {
            repository.getMovies()

            verify(repository, times(1)).getMovies()
        }
    }

}