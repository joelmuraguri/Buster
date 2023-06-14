package com.example.network.service

import com.example.network.model.FilmDTO
import com.example.network.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BusterApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularFilms(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "cc094bc888f690a84f4bc1439cd65f70",
    ) : FilmResponse

    @GET("/3/movie/latest")
    suspend fun getLatestFilm(
        @Query("api_key") apiKey: String = "cc094bc888f690a84f4bc1439cd65f70",
    ) : FilmDTO

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingFilms(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "cc094bc888f690a84f4bc1439cd65f70",
    ) : FilmResponse

}