package com.joel.data.mappers

import com.joel.data.model.FilmDataModel
import com.joel.database.model.tv.AiringTodayTvEntity
import com.joel.database.model.tv.OnAirTvEntity
import com.joel.database.model.tv.PopularTvEntity
import com.joel.database.model.tv.TopRatedTvEntity
import com.joel.network.response.film.tv.TvApiResponse

/**map api to entity*/
fun TvApiResponse.Tv.toPopularTvEntity() : PopularTvEntity{
    return PopularTvEntity(
        filmId = id ?: 0,
        filmName = name.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun TvApiResponse.Tv.toAiringTodayEntity() : AiringTodayTvEntity {
    return AiringTodayTvEntity(
        filmId = id ?: 0,
        filmName = name.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun TvApiResponse.Tv.toOnTheAirTvEntity() : OnAirTvEntity {
    return OnAirTvEntity(
        filmId = id ?: 0,
        filmName = name.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun TvApiResponse.Tv.toTopRatedTvEntity() : TopRatedTvEntity {
    return TopRatedTvEntity(
        filmId = id ?: 0,
        filmName = name.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

/**map entity to data model*/
fun AiringTodayTvEntity.toDatModel() : FilmDataModel {
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun OnAirTvEntity.toDatModel() : FilmDataModel {
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun PopularTvEntity.toDatModel() : FilmDataModel {
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun TopRatedTvEntity.toDatModel() : FilmDataModel {
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}