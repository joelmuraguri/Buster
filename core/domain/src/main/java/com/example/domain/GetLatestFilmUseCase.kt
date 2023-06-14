package com.example.domain

import com.example.common.Resource
import com.example.network.model.FilmDTO
import com.example.network.repo.FilmRepo
import kotlinx.coroutines.flow.Flow

class GetLatestFilmUseCase(
    private val repo: FilmRepo
) {

    suspend operator fun invoke() : Flow<Resource<FilmDTO>> {
        return repo.getLatestFilm()
    }
}