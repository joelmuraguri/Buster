package com.joel.domain.model.film

import java.util.Date

data class Film(
    val title : String,
    val genres : List<String>,
    val imageUrl : String,
    val rating : Double,
    val releaseDate : String
)
