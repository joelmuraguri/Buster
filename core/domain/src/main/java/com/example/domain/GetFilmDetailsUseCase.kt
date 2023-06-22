package com.example.domain

import com.example.common.Resource
import com.example.network.model.FilmDTO
import com.example.network.repo.FilmRepo
import kotlinx.coroutines.flow.Flow

class GetFilmDetailsUseCase(
    private val repo: FilmRepo
) {

    suspend operator fun invoke(filmId : Int) : Flow<Resource<FilmDTO>>{
        return repo.getFilmDetails(filmId)
    }
}