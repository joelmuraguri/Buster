package com.joel.data.repository

import androidx.paging.PagingData
import com.joel.data.model.FilmDataModel
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getTopRatedMovies() : Flow<PagingData<FilmDataModel>>
    fun getTrendingMovies() : Flow<PagingData<FilmDataModel>>
    fun getNowPlayingMovies() : Flow<PagingData<FilmDataModel>>
    fun getPopularMovies() : Flow<PagingData<FilmDataModel>>
    fun getOnTheAirTv() : Flow<PagingData<FilmDataModel>>
    fun getAiringTodayTv() : Flow<PagingData<FilmDataModel>>
    fun getPopularTv() : Flow<PagingData<FilmDataModel>>
    fun getTopRatedTv() : Flow<PagingData<FilmDataModel>>

}