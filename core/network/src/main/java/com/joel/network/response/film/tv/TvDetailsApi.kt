package com.joel.network.response.film.tv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvDetailsApi(
    val adult: Boolean ? = null,
    @SerialName("backdrop_path")
    val backdropPath: String ?= null,
    @SerialName("created_by")
    val createdBy: List<CreatedBy> ?= null,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int> ?= null,
    @SerialName("first_air_date")
    val firstAirDate: String ? = null,
    val genres: List<Genre> ?= null,
    val homepage: String ?= null,
    val id: Int ?= null,
    @SerialName("in_production")
    val inProduction: Boolean ?= null,
    val languages: List<String> ?= null,
    @SerialName("last_air_date")
    val lastAirDate: String ?= null,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir ?= null,
    val name: String ?= null,
    val networks: List<Network> ? = null,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir ?= null,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int ?= null,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int ?= null,
    @SerialName("origin_country")
    val originCountry: List<String> ? = null,
    @SerialName("original_language")
    val originalLanguage: String ?= null,
    @SerialName("original_name")
    val originalName: String ?=null,
    val overview: String ?= null,
    val popularity: Double ?= null,
    @SerialName("poster_path")
    val posterPath: String ?= null,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany> ?= null,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry> ? = null,
    val seasons: List<Season> ?= null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> ? = null,
    val status: String ?= null,
    val tagline: String ?= null,
    val type: String ?= null,
    @SerialName("vote_average")
    val voteAverage: Double ?= null,
    @SerialName("vote_count")
    val voteCount: Int ? = null
) {


    @Serializable
    data class Season(
        @SerialName("air_date")
        val airDate: String ? = null,
        @SerialName("episode_count")
        val episodeCount: Int ?= null,
        val id: Int ?= null,
        val name: String ?= null,
        val overview: String ?= null,
        @SerialName("poster_path")
        val posterPath: String ? = null,
        @SerialName("season_number")
        val seasonNumber: Int,
        @SerialName("vote_average")
        val voteAverage: Double
    )

    @Serializable
    data class SpokenLanguage(
        @SerialName("english_name")
        val englishName: String ?= null,
        val iso_639_1: String ?= null,
        val name: String ?= null
    )

    @Serializable
    data class CreatedBy(
        @SerialName("credit_id")
        val creditId: String ?= null,
        val gender: Int ? = null,
        val id: Int ?= null,
        val name: String ?= null,
        @SerialName("profile_path")
        val profilePath: String ?= null
    )

    @Serializable
    data class Genre(
        val id: Int ? = null,
        val name: String ?= null
    )

    @Serializable
    data class LastEpisodeToAir(
        @SerialName("air_date")
        val airDate: String ?= null,
        @SerialName("episode_number")
        val episodeNumber: Int ?= null,
        @SerialName("episode_type")
        val episodeType: String ?= null,
        val id: Int ? = null,
        val name: String ?= null,
        val overview: String ?= null,
        @SerialName("production_code")
        val productionCode: String ?= null,
        val runtime: Int ?= null,
        @SerialName("season_number")
        val seasonNumber: Int ?= null,
        @SerialName("show_id")
        val showId: Int ?= null,
        @SerialName("still_path")
        val stillPath: String ?= null,
        @SerialName("vote_average")
        val voteAverage: Int ?= null,
        @SerialName("vote_count")
        val voteCount: Int ?= null
    )

    @Serializable
    data class Network(
        val id: Int ?= null,
        @SerialName("logo_path")
        val logoPath: String ?= null,
        val name: String ?= null,
        @SerialName("origin_country")
        val originCountry: String ?= null
    )


    @Serializable
    data class NextEpisodeToAir(
        @SerialName("air_date")
        val airDate: String ?= null,
        @SerialName("episode_number")
        val episodeNumber: Int ?= null,
        @SerialName("episode_type")
        val episodeType: String ?= null,
        val id: Int ?= null,
        val name: String ?= null,
        val overview: String ?= null,
        @SerialName("production_code")
        val productionCode: String ?= null,
        val runtime: Int ?= null, //
        @SerialName("season_number")
        val seasonNumber: Int ?= null,
        @SerialName("show_id")
        val showId: Int ?= null,
        @SerialName("still_path")
        val stillPath: String ?= null, //
        @SerialName("vote_average")
        val voteAverage: Int ?= null,
        @SerialName("vote_count")
        val voteCount: Int ?= null
    )

    @Serializable
    data class ProductionCompany(
        val id: Int ?= null,
        @SerialName("logo_path")
        val logoPath: String ? = null,
        val name: String ?= null,
        @SerialName("origin_country")
        val originCountry: String ?= null
    )

    @Serializable
    data class ProductionCountry(
        val iso_3166_1: String ?= null,
        val name: String ?= null
    )

}