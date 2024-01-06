package com.joel.network.response.film.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponse(
    val dates: Dates ? = null,
    val page: Int ?= null ,
    @SerialName("results")
    val movies: List<Movies> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int ?= null,
    @SerialName("total_results")
    val totalResults: Int ?= null
) {

    @Serializable
    data class Dates(
        val maximum: String ?= null,
        val minimum: String ?= null
    )

    @Serializable
    data class Movies(
        val adult: Boolean ?= null,
        @SerialName("backdrop_path")
        val backdropPath: String ? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int> ?= null,
        val id: Int ?= null,
        @SerialName("original_language")
        val originalLanguage: String ? = null,
        @SerialName("original_title")
        val originalTitle: String ? = null,
        val overview: String ? = null,
        val popularity: Double ? = null,
        @SerialName("poster_path")
        val posterPath: String ? = null,
        @SerialName("release_date")
        val releaseDate: String ? = null,
        val title: String ? = null,
        val video: Boolean ?= null,
        @SerialName("vote_average")
        val voteAverage: Double ? = null,
        @SerialName("vote_count")
        val voteCount: Int ? = null
    )
}