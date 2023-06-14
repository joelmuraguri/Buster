package com.example.network.repo

import com.example.common.Resource
import com.example.network.model.FilmDTO
import kotlinx.coroutines.flow.Flow

interface FilmRepo {

    suspend fun getLatestFilm() : Flow<Resource<FilmDTO>>

    suspend fun getFilmDetails(id : Int) : Flow<Resource<FilmDTO>>

}