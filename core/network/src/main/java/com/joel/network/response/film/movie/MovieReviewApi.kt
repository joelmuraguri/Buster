package com.joel.network.response.film.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReviewApi(
    @SerialName("id")
    val movieId: Int ?= null,
    val page: Int ? = null,
    @SerialName("results")
    val reviews: List<Reviews> ? = null,
    @SerialName("total_pages")
    val totalPages: Int ?= null,
    @SerialName("total_results")
    val totalResults: Int ?= null
){

    @Serializable
    data class Reviews(
        val author: String ?= null,
        @SerialName("author_details")
        val authorDetails: AuthorDetails ?= null,
        val content: String ?= null,
        @SerialName("created_at")
        val createdAt: String ?= null,
        val id: String ?= null,
        @SerialName("updated_at")
        val updatedAt: String ?= null,
        val url: String ?= null
    )

    @Serializable
    data class AuthorDetails(
        @SerialName("avatar_path")
        val avatarPath: String ?= null,
        val name: String ?= null,
        val rating: Int ?= null,
        val username: String ?= null
    )

}