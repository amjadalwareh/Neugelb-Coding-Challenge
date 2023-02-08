package com.neugelb.data

import com.neugelb.data.remote.api.MoviesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var api: MoviesApi

    @Before
    fun setUp() {
        mockWebServer = initMockWebserver()

        mockWebServer.start()

        okHttpClient = initMockOkHttpClient()

        retrofit = initMockRetrofit(mockWebServer, okHttpClient)

        api = initGitHubApi(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch Movies correctly given 200 response`() {
        mockWebServer.enqueueResponse("movies-response-200.json", 200)

        runBlocking {
            val actual = api.fetchMovies(1)
            assertNotNull(actual)
        }
    }

    @Test
    fun `should fetch Movies correctly given correct data`() {
        mockWebServer.enqueueResponse("movies-response-200.json", 200)

        runBlocking {
            val actual = api.fetchMovies(1)
            assertEquals(actual.results[0].originalTitle, "Black Panther: Wakanda Forever")
        }
    }
}