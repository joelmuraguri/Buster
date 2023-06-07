package com.example.network.repo

import androidx.paging.PagingData
import com.example.network.model.Film
import com.example.network.service.BusterApiService
import kotlinx.coroutines.flow.Flow


class FilmRepository @Inject constructor (
    private val apiService: BusterApiService
) {

    suspend fun getTrendingMovies() : Flow<PagingData<Film>> {

    }
}