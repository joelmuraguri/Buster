package com.example.network.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.network.model.FilmDTO
import com.example.network.paging.TrendingFilmSource
import com.example.network.service.BusterApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FilmRepository @Inject constructor (
    private val apiService: BusterApiService
) {

    fun getTrendingMovies() : Flow<PagingData<FilmDTO>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingFilmSource(apiService = apiService)
            }
        ).flow
    }
}