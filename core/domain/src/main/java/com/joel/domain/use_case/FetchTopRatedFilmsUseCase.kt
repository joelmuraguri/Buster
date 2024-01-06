package com.joel.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.joel.data.repository.FilmRepository
import com.joel.domain.mappers.toFilm
import com.joel.domain.model.film.Film
import com.joel.domain.model.film.filters.FilmType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchTopRatedFilmsUseCase(
    private val filmRepository: FilmRepository
) {

    operator fun invoke(filmType : FilmType) : Flow<PagingData<Film>>{
        val topRatedFilms = if (filmType == FilmType.MOVIE) filmRepository.getTopRatedMovies() else filmRepository.getTopRatedTv()
        return topRatedFilms.map { pagingData ->
            pagingData.map { filmDataModel ->
                filmDataModel.toFilm()
            }
        }
    }
}