package com.joel.network.response.film.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsApi(
    val adult: Boolean ? = null,
    @SerialName("backdrop_path")
    val backdropPath: String ?= null,
    @SerialName("belongs_to_collection")
    val collection: BelongsToCollection,
    val budget: Int ?= null,
    val genres: List<Genre> ? = null,
    val homepage: String ? = null,
    val id: Int ?= null,
    @SerialName("imdb_id")
    val imdbID: String ?= null,
    @SerialName("original_language")
    val originalLanguage: String? = null,
    @SerialName("original_title")
    val originalTitle: String ?= null,
    val overview: String ?= null,
    val popularity: Double ?= null,
    @SerialName("poster_path")
    val posterPath: String ?= null,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany> ?= null,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry> ?= null,
    @SerialName("release_date")
    val releaseDate: String ?= null,
    val revenue: Int ?= null,
    val runtime: Int ?= null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> ?= null,
    val status: String ?= null,
    val tagline: String ? = null,
    val title: String ?= null,
    val video: Boolean ?= null,
    @SerialName("vote_average")
    val voteAverage: Double ?= null,
    @SerialName("vote_count")
    val voteCount: Int ?= null
) {

    @Serializable
    data class BelongsToCollection(
        @SerialName("backdrop_path")
        val backdropPath: String ?= null,
        val id: Int ?= null,
        val name: String ?= null,
        @SerialName("poster_path")
        val posterPath: String ?= null
    )

    @Serializable
    data class Genre(
        val id: Int ?= null,
        val name: String ?=null
    )

    @Serializable
    data class ProductionCompany(
        val id: Int = 0,
        @SerialName("logo_path")
        val logoPath: String ?= null,
        val name: String = "",
        @SerialName("origin_country")
        val originCountry: String ?= null
    )

    @Serializable
    data class ProductionCountry(
        val iso_3166_1: String ?= null,
        val name: String ?= null
    )


    @Serializable
    data class SpokenLanguage(
        @SerialName("english_name")
        val englishName: String ?= null,
        val iso_639_1: String ?= null,
        val name: String ?= null
    )
}