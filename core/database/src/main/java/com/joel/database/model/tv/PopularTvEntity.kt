package com.joel.database.model.tv

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("PopularTvEntity")
data class PopularTvEntity(
    @PrimaryKey val filmId : Int,
    val filmName : String,
    val releaseDate : String,
    val rating : Double,
    val overView : String,
    val genres : List<String>,
    val adult : String
)
