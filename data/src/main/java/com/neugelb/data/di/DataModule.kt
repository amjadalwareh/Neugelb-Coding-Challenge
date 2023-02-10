package com.neugelb.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neugelb.data.BuildConfig
import com.neugelb.data.BuildConfig.BASE_URL
import com.neugelb.data.base.TokenInterceptor
import com.neugelb.data.remote.api.MoviesApi
import com.neugelb.data.repository.MoviesRepositoryImpl
import com.neugelb.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(TokenInterceptor())
        .addInterceptor(getLoggingInterceptor())
        .build()

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun providesRepository(api: MoviesApi): MoviesRepository = MoviesRepositoryImpl(api)

}