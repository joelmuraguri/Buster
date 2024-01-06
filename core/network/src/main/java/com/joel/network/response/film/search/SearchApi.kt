package com.joel.network.response.film.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchApi(
    val page: Int ? = null,
    val results: List<Result> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int ? = null,
    @SerialName("total_results")
    val totalResults: Int ? = null
) {

    @Serializable
    data class Result(
        val adult: Boolean ? = null,
        @SerialName("backdrop_path")
        val backdropPath: String ? = null,
        @SerialName("first_air_date")
        val firstAirDate: String ? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int> ?= null,
        val id: Int ? = null,
        @SerialName("media_type")
        val mediaType: String ? = null,
        val name: String ? = null,
        @SerialName("origin_country")
        val originCountry: List<String> = emptyList(),
        @SerialName("original_language")
        val originalLanguage: String ? = null,
        @SerialName("original_name")
        val originalName: String ? = null,
        @SerialName("original_title")
        val originalTitle: String ?= null,
        val overview: String ?= null,
        val popularity: Double ?= null,
        @SerialName("poster_path")
        val posterPath: String ?= null,
        @SerialName("release_date")
        val releaseDate: String ?= null,
        val title: String ?=null,
        val video: Boolean ?= null,
        @SerialName("vote_average")
        val voteAverage: Double ?= null,
        @SerialName("vote_count")
        val voteCount: Int ?= null
    )
}