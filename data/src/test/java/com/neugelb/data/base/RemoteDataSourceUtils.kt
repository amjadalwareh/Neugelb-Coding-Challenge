package com.neugelb.data.base

import com.neugelb.data.remote.api.MoviesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        val response = MockResponse()
            .setResponseCode(code)
            .setBody(source.readString(StandardCharsets.UTF_8))
        enqueue(response)
    }
}

internal fun initMockWebserver(): MockWebServer = MockWebServer()

internal fun initMockOkHttpClient(): OkHttpClient = OkHttpClient()
    .newBuilder()
    .build()

internal fun initMockRetrofit(
    mockWebServer: MockWebServer,
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal fun initMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)