package com.joel.network.response.film.tv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvApiResponse(
    val page: Int ?= null,
    @SerialName("results")
    val tvs: List<Tv> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int ?= null,
    @SerialName("total_results")
    val totalResults: Int ?= null
) {

    @Serializable
    data class Tv(
        val adult: Boolean ?= null,
        @SerialName("backdrop_path")
        val backdropPath: String ?= null,
        @SerialName("first_air_date")
        val firstAirDate: String ?= null,
        @SerialName("genre_ids")
        val genreIds: List<Int> ?= null,
        val id: Int ?= null,
        val name: String ?= null,
        @SerialName("origin_country")
        val originCountry: List<String> ?= null,
        @SerialName("original_language")
        val originalLanguage: String ?= null,
        @SerialName("original_name")
        val originalName: String ?= null,
        val overview: String ?= null,
        val popularity: Double ?= null,
        @SerialName("poster_path")
        val posterPath: String ?= null,
        @SerialName("vote_average")
        val voteAverage: Double ?= null,
        @SerialName("vote_count")
        val voteCount: Int ?= null
    )
}