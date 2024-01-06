package com.joel.data.mappers

import com.joel.data.model.FilmDataModel
import com.joel.database.model.movie.NowPlayingMovieEntity
import com.joel.database.model.movie.PopularMovieEntity
import com.joel.database.model.movie.TopRatedMovieEntity
import com.joel.database.model.movie.TrendingMovieEntity
import com.joel.network.response.film.movie.MovieApiResponse

/**map api to entity*/
fun MovieApiResponse.Movies.toTrendingMovieEntity() : TrendingMovieEntity{
    return TrendingMovieEntity(
        filmId = id ?: 0,
        filmName = title.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun MovieApiResponse.Movies.toNowPlayingMovieEntity() : NowPlayingMovieEntity{
    return NowPlayingMovieEntity(
        filmId = id ?: 0,
        filmName = title.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun MovieApiResponse.Movies.toTopRatedMovieEntity() : TopRatedMovieEntity{
    return TopRatedMovieEntity(
        filmId = id ?: 0,
        filmName = title.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

fun MovieApiResponse.Movies.toPopularMovieEntity() : PopularMovieEntity{
    return PopularMovieEntity(
        filmId = id ?: 0,
        filmName = title.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overView = overview.orEmpty(),
        genres = genreIds?.map { it.toString() } ?: emptyList(),
        adult = adult?.toString() ?: "false",
        postUrl = posterPath.orEmpty()
    )
}

/**map entity to data model*/
fun TrendingMovieEntity.toDatModel() : FilmDataModel{
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun NowPlayingMovieEntity.toDatModel() : FilmDataModel{
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun TopRatedMovieEntity.toDatModel() : FilmDataModel{
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}

fun PopularMovieEntity.toDatModel() : FilmDataModel{
    return FilmDataModel(
        genres = genres,
        title = filmName,
        releaseDate = releaseDate,
        postUrl = postUrl
    )
}