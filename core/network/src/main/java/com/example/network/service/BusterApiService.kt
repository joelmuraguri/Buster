package com.example.network.service

import com.example.network.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BusterApiService {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = "cc094bc888f690a84f4bc1439cd65f70",
    ) : FilmResponse

}