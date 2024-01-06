package com.joel.domain.mappers

import com.joel.data.model.FilmDataModel
import com.joel.domain.model.film.Film

fun FilmDataModel.toFilm() : Film{
    return Film(
        title = title,
        genres = genres,
        imageUrl = postUrl,
        rating = 0.0,
        releaseDate = releaseDate
    )
}