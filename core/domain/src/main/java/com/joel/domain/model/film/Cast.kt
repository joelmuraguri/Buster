package com.joel.domain.model.film

data class Cast(
    val filmId : Int,
    val casts : List<CastInfo>
)

data class CastInfo(
    val originalName : String,
    val imageURL : String,
    val characterName : String
)
