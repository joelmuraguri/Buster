package com.example.network.repo

import com.example.network.model.FilmResponse
import com.example.network.service.BusterApiService
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PagedFilmsRepository @Inject constructor (
    private val apiService: BusterApiService
) {

    suspend fun getPopularFilms(page : Int) : FilmResponse{
        delay(1500L)
        return apiService.getPopularFilms(page = page)
    }

    suspend fun getUpcomingFilms(page : Int) : FilmResponse{
        delay(1500L)
        return apiService.getUpcomingFilms(page = page)
    }

    suspend fun getTrendingFilms(page : Int) : FilmResponse{
        delay(1500L)
        return apiService.getTrendingFilms(page = page)
    }
   suspend fun getTopRatedFilms(page : Int) : FilmResponse{
        delay(1500L)
        return apiService.getTopRatedFilms(page = page)
    }

}