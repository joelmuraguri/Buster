package com.joel.data.mappers

import com.joel.data.model.SearchDataModel
import com.joel.database.model.search.SearchEntity
import com.joel.network.response.film.search.SearchApi

fun SearchApi.Result.toSearchEntity() : SearchEntity{
    return SearchEntity(
        filmType = mediaType.orEmpty(),
        title = title ?: name ?: originalTitle.orEmpty(),
        postUrl = posterPath.orEmpty(),
        releaseDate = releaseDate ?: firstAirDate.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        filmId = id ?: 0,
        timeStamp = System.currentTimeMillis(),
    )
}

fun SearchEntity.toDatModel() : SearchDataModel {
    return SearchDataModel(
        genres = genres,
        title = title,
        releaseDate = releaseDate,
        postUrl = postUrl,
        filmType = filmType
    )
}