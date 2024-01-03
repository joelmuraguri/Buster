package com.joel.database.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("PopularMovieEntity")
data class PopularMovieEntity(
    @PrimaryKey val filmId : Int,
    val filmName : String,
    val releaseDate : String,
    val rating : Double,
    val overView : String,
    val genres : List<String>,
    val adult : String
)
