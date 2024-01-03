package com.joel.network.response.film.tv

import kotlinx.serialization.Serializable

@Serializable
data class TvGenreApi(
    val genres: List<Genre> ?= null
) {

    @Serializable
    data class Genre(
        val id: Int ?= null,
        val name: String ?= null
    )
}