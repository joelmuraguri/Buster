package com.joel.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("WatchListEntity")
data class WatchListEntity(
    @PrimaryKey val filmId : Int,
    val filmName : String,
    val releaseDate : String,
    val rating : Double,
    val overView : String,
    val genres : List<String>,
    val adult : String
)
