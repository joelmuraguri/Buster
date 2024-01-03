package com.joel.network.response.film.tv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvCastApi(
    val cast: List<Cast> ?= null,
    val crew: List<Crew> ?= null,
    val id: Int ? = null
) {

    @Serializable
    data class Cast(
        val adult: Boolean ?= null,
        val character: String ?= null,
        @SerialName("credit_id")
        val creditId: String ?= null,
        val gender: Int ?= null,
        val id: Int ?= null,
        @SerialName("known_for_department")
        val knownForDepartment: String ?= null,
        val name: String ?= null,
        val order: Int ?= null,
        @SerialName("original_name")
        val originalName: String ?= null,
        val popularity: Double ?= null,
        @SerialName("profile_path")
        val profilePath: String ?= null
    )

    @Serializable
    data class Crew(
        val adult: Boolean ?= null,
        @SerialName("credit_id")
        val creditId: String ?= null,
        val department: String ?= null,
        val gender: Int ?= null,
        val id: Int ?= null,
        val job: String ?= null,
        @SerialName("known_for_department")
        val knownForDepartment: String ?= null,
        val name: String ?= null,
        @SerialName("original_name")
        val originalName: String ?= null,
        val popularity: Double ?= null,
        @SerialName("profile_path")
        val profilePath: String ?= null
    )
}