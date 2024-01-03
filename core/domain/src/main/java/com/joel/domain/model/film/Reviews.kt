package com.joel.domain.model.film

data class Reviews(
    val filmId : Int,
    val reviews : List<ReviewInfo>,
    val totalPages: Int,
    val totalResults: Int,
    val page: Int
)

data class ReviewInfo(
    val authorName : String,
    val authorImage : String,
    val content : String,
    val datePublished : String
)
