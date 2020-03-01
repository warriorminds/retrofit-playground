package com.warriorminds.retrofitplayground.network

import com.warriorminds.retrofitplayground.models.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/search/movie")
    suspend fun searchMovie(@Query("query") query: String): Response<MovieResponse>
}