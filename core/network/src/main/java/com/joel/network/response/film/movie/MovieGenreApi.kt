package com.joel.network.response.film.movie

import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreApi(
    val genres: List<Genre> ? = null
) {

    @Serializable
    data class Genre(
        val id: Int ? = null,
        val name: String ?= null
    )

}