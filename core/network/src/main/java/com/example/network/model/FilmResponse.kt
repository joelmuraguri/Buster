package com.example.network.model


data class FilmResponse(
    val page: Int,
    val results: List<Film>,
    val total_pages: Int,
    val total_results: Int
)
