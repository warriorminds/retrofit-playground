package com.warriorminds.retrofitplayground

import com.warriorminds.retrofitplayground.models.MovieResponse
import com.warriorminds.retrofitplayground.network.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    private val logging = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .method(originalRequest.method, originalRequest.body)
                .build()

            chain.proceed(request)
        }
        .addInterceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", "api_key_val")
                .build()

            val request = chain.request().newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    private val movieService = retrofit.create(MovieService::class.java)

    suspend fun searchMovie(): Response<MovieResponse>?
            = movieService.searchMovie("star")
}